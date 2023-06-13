package com.nien.demo.coccurent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
@SuppressWarnings("all")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcurrentRecord {

    /**
     * 已启动状态
     */
    static final int STARTED = 1;

    /**
     * 初始状态
     */
    static final int NEW = 0;

    /**
     * 停止状态
     */
    static final int STOP = -1;


    /**
     * 是否是停止状态
     */
    static boolean IsStop(int state) {
        return state < 0;
    }

    /**
     * 是否是开始状态
     */
    static boolean IsStarted(int state) {
        return state > 0;
    }


    /**
     * 用于存放所有任务的数据
     */
    final AtomicReferenceArray<RecordTaskManager> taskArray;


    final AtomicInteger counter = new AtomicInteger(0);

    /**
     * 当前记录仪的状态
     */
    @Getter
    volatile int state;

    static final AtomicIntegerFieldUpdater<ConcurrentRecord> STATE =
            AtomicIntegerFieldUpdater.newUpdater(ConcurrentRecord.class, "state");

    /**
     * 开始时间
     */
    @Getter
    volatile long startTime;

    /**
     * 结束时间
     */
    @Getter
    volatile long endTime;


    @Getter
    final Lock lock = new ReentrantLock();

    @Getter
    final Condition completeCondition = lock.newCondition();

    /**
     * 结束等待条件队列
     */
    final Condition stopCondition = lock.newCondition();


    final boolean lazyInit;

    /**
     * @param bucket   拆分的桶数量
     * @param lazyInit 是否需要lazy 初始化数组
     */
    public ConcurrentRecord(int bucket, boolean lazyInit) {
        this.taskArray = new AtomicReferenceArray<>(bucket);
        this.lazyInit = lazyInit;
        if (!lazyInit)
            initRecordTasks(bucket);
    }

    /**
     * 初始话所有的桶数量
     *
     * @param bucket
     */
    private void initRecordTasks(int bucket) {
        for (int i = 0; i < bucket; i++) {
            taskArray.set(i, new RecordTaskManager(this));
        }
    }


    private void start() {
        for (; ; ) {
            if (getState() > 0 || getState() < 0) {
                return;
            }
            //这里会有延迟机制，但是不会有太大影响
            if (STATE.compareAndSet(this, NEW, STARTED)) {
                this.startTime = System.currentTimeMillis();
            }
        }

    }


    /**
     * 创建一个新的 Task任务
     *
     * @param taskName 任务名称
     */
    public RecordTask newRecord(String taskName) {
        //如果当前的记录器还没有开始则开始
        if (!isStarted()) {
            this.start();
        }

        //如果当前记录器已经是stop 状态则不允许再添加task
        if (isStop()) {
            return null;
        }

        int count = counter.getAndIncrement();
        int index = count % taskArray.length();


        //为了避免在 自旋内部产生多个RecordsTasks 对象，这里放置一个公共对象用于帮助 CAS
        RecordTaskManager publicTasks = RecordTaskManager.EMPTY;

        for (; ; ) {
            RecordTaskManager taskManager = taskArray.get(index);
            if (taskManager != null && taskManager != publicTasks) {
                break;
            }
            if (taskArray.compareAndSet(index, null, publicTasks)) {
                RecordTaskManager manager = new RecordTaskManager(this);
                taskArray.set(index, manager);
            }
        }

        //自旋中的变量与该变量不能设置为同一个值，否则会出问题
        RecordTaskManager taskManager = taskArray.get(index);
        return taskManager.newRecord(this, taskName);
    }


    /**
     * 停止当前任务
     */
    private void stop() {
        for (; ; ) {
            if (getState() < 0) {
                return;
            }
            //这里会有延迟机制，但是不会有太大影响
            if (STATE.compareAndSet(this, STARTED, STOP)) {
                this.endTime = System.currentTimeMillis();
                notifyState();
            }
        }
    }

    /**
     * 不需要等待所有任务都执行完成，就可以打印当前记录仪的信息
     */
    public String prettyNow() {
        StringBuilder builder = new StringBuilder();
        builder.append("All Records :[ ");
        for (int i = 0; i < taskArray.length(); i++) {
            RecordTaskManager taskManager = taskArray.get(i);
            if (taskManager != null) {
                builder.append("\n").append(taskManager.toString());
            }
        }
        builder.append("\n], ExecuteTime is: ").append(getExecuteTime(startTime, endTime)).append("s");
        return builder.toString();
    }

    /**
     * 需要等待当前所有的任务都执行完成后才会执行
     */
    public String prettyCompleteTask() {
        if (!completeTask()) {
            waitForCompleteTask();
        }
        return prettyNow();
    }

    /**
     * 需要等待当前所有的任务都执行完成后才会执行
     */
    public String pretty() {
        if (!isStop()) {
            waitForStop();
        }
        return prettyNow();
    }


    /**
     * 任务是否已经全部完成
     */
    public boolean completeTask() {
        for (int i = 0; i < taskArray.length(); i++) {
            RecordTaskManager taskManager = taskArray.get(i);
            if (taskManager == null) {
                continue;
            }

            if (!taskManager.isComplete()) {
                return false;
            }
        }

        stop();
        return true;
    }


    public boolean isStop() {
        return IsStop(getState());
    }

    /**
     * 判断当前的记录器是否是启动状态
     */
    public boolean isStarted() {
        return IsStarted(getState());
    }


    /**
     * 唤醒所有 阻塞在stop上的线程
     */
    void notifyState() {
        notifyThreads(stopCondition);
    }

    /**
     * 唤醒所有 阻塞在 complete上的线程
     */
    void notifyCompleteTask() {
        notifyThreads(completeCondition);
    }

    /**
     * 唤醒所有的线程，只要任务执行完成，不管是否stop
     */
    private void notifyThreads(Condition condition) {
        try {
            lock.lock();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void waitForStop() {
        waitTask(stopCondition);
    }

    void waitForCompleteTask() {
        waitTask(completeCondition);
    }

    private void waitTask(Condition condition) {
        try {
            lock.lock();
            boolean interrupt = false;
            for (; ; ) {
                try {
                    condition.await();
                    break;
                } catch (InterruptedException ex) {
                    interrupt = true;
                }
            }
            //如果对当前线程执行过interrupt操作，则在被唤醒后对当前线程进行中断
            if (interrupt)
                Thread.currentThread().interrupt();

        } finally {
            lock.unlock();
        }

    }

    public static double getExecuteTime(long startTime, long endTime) {
        return ((double) endTime - (double) startTime) / 1000;
    }

    /**
     * 用于帮助统计所有task的状态
     */
    static class RecordTaskManager {

        static RecordTaskManager EMPTY = new RecordTaskManager();

        /**
         * 保存当前节点中所有的任务
         */
        volatile CopyOnWriteArrayList<RecordTask> recordTasks;

        /**
         * 用于统计当前Bucket中所有的任务是否完成
         */
        final LongAdder taskCounter;

        /**
         * 记录器
         */
        final ConcurrentRecord record;

        /**
         * 初始化一个空的对象用于自旋优化
         */
        private RecordTaskManager() {
            this.recordTasks = null;
            this.taskCounter = null;
            this.record = null;
        }

        public RecordTaskManager(ConcurrentRecord record) {
            this.recordTasks = new CopyOnWriteArrayList<>();
            this.taskCounter = new LongAdder();
            this.record = record;
        }

        public RecordTask newRecord(ConcurrentRecord record, String taskName) {
            RecordTask recordTask = new RecordTask(record, this, taskName);
            recordTasks.add(recordTask);
            taskCounter.increment();
            return recordTask;
        }

        public void complteTask() {
            taskCounter.decrement();

            //首先判断当前Bucket中的任务是否完成，如果没有完成就没有必要对整个record 进行判断
            if (!isComplete())
                return;

            //判断record中的任务是否完成
            if (record.completeTask()) {
                record.notifyCompleteTask();
            }
        }

        public boolean isComplete() {
            return taskCounter.longValue() == 0;
        }

        public void waitTask() {
            record.waitForCompleteTask();
        }

        @Override
        public String toString() {
            //目前已经执行完成的任务数，减少误差
            long count = taskCounter.longValue();

            //非结束状态的将不纳入统计
            String tasks = recordTasks.stream()
                    .filter(recordTask -> recordTask.isStop())
                    .map(RecordTask::toString)
                    .collect(Collectors.joining(", "));
            String s = taskCounter.longValue() + " " + tasks;

            log.info("【TaskManagr:  counter: {}, {} 】", taskCounter.longValue(), tasks);
            return s;
        }
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @RequiredArgsConstructor
    public static class RecordTask {

        /**
         * 执行的开始时间
         */
        long startTime = 0L;

        /**
         * 执行的结束时间
         */
        long endTime = 0L;

        /**
         * 当前task的状态
         */
        volatile int state;

        /**
         * 和当前task绑定的线程
         */
        @Setter
        @Getter
        volatile Thread taskThread;

        /**
         * 任务名称
         */
        final String taskName;

        /**
         * 用于向task的管理者汇报状态
         */
        final RecordTaskManager taskManager;

        final ConcurrentRecord record;

        static final AtomicIntegerFieldUpdater<RecordTask> STATE
                = AtomicIntegerFieldUpdater.newUpdater(RecordTask.class, "state");

        public RecordTask(ConcurrentRecord record, RecordTaskManager taskManager, String taskName) {
            this.taskManager = taskManager;
            this.record = record;
            this.taskName = taskName;
        }

        /**
         * 开始当前任务，允许多线程对同一个RecordTask 进行 start操作，
         * 如果非当前持有Record的线程对Record进行操作则会生成其他线程副本
         */
        public RecordTask start() {

            if (isStop()) {
                throw new UnsupportedOperationException("The current task is used !");
            }

            //如果记录器已经停止了，就不可以再对 RecordTask 做任务操作了, 但是还是可以执行结束操作的
            if (record.isStop()) {
                record.notifyCompleteTask();
                throw new UnsupportedOperationException("Record is stoped");
            }

            for (; ; ) {
                if (isStarted() && getTaskThread() != null) {
                    return taskManager.newRecord(record, Thread.currentThread().getName() + "-" + taskName);
                }

                if (STATE.compareAndSet(this, NEW, STARTED)) {
                    this.startTime = System.currentTimeMillis();
                    setTaskThread(Thread.currentThread());
                    return this;
                }
            }

        }

        /**
         * 停止当前任务
         */
        public void stop() {
            Thread thread = Thread.currentThread();
            if (getTaskThread() != thread) {
                throw new UnsupportedOperationException("UnSupport another thread stop current task");
            }

            if (getState() >= 0) {
                int s = state;
                if (STATE.compareAndSet(this, s, STOP)) {
                    this.endTime = System.currentTimeMillis();
                    taskManager.complteTask();
                }
            }
//
//            //是否还有未完成的任务，如果有则不做任务操作
//            if (record.completeTask())
//                record.notifyThreads();
        }

        public boolean isStop() {
            return IsStop(getState());
        }

        public boolean isStarted() {
            return IsStarted(getState());
        }

        @Override
        public String toString() {
            Double executeTime = getExecuteTime(startTime, endTime);
            String s = taskName + " " + executeTime + " " + taskThread.getName() + " ";
//            log.info("RecordTask (taskName = {}, usedTime = {}s, threadName: {})", taskName, executeTime, taskThread.getName());
            return s;
        }


    }

    public static void main(String[] args) {
        ConcurrentRecord record = new ConcurrentRecord(6, true);

        new Thread(() -> {
            RecordTask recordTask = record.newRecord("1");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();

            recordTask = record.newRecord("1-1");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();


            recordTask = record.newRecord("1-2");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();
        }).start();


        new Thread(() -> {
            RecordTask recordTask = record.newRecord("2");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();

            recordTask = record.newRecord("2-1");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();


            recordTask = record.newRecord("2-2");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();
        }).start();


        new Thread(() -> {
            RecordTask recordTask = record.newRecord("3");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();

            recordTask = record.newRecord("3-1");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();


            recordTask = record.newRecord("3-2");
            recordTask.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordTask.stop();

        }).start();


//        record.stop();
        System.out.println(record.pretty());

    }


}


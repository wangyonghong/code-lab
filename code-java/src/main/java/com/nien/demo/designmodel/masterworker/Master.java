package com.nien.demo.designmodel.masterworker;

import com.nien.demo.util.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Master<T extends Task, R> {
    // 所有worker的集合
    private HashMap<String, Worker<T, R>> workers = new HashMap<>();


    // 任务的集合
    private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();

    //任务处理结果集
    protected Map<String, R> resultMap = new ConcurrentHashMap<>();

    //Master的任务调度线程
    private Thread thread = null;

    //保持最终的和
    private AtomicLong sum = new AtomicLong(0);

    public Master(int workerCount) {
        // 每个worker对象都需要持有queue的引用, 用于领任务与提交结果
        for (int i = 0; i < workerCount; i++) {
            Worker<T, R> worker = new Worker<>();
            workers.put("子节点: " + i, worker);
        }
        thread = new Thread(() -> this.execute());
        thread.start();
    }

    // 提交任务
    public void submit(T task) {
        taskQueue.add(task);
    }


    //结果处理的回调函数
    private void resultCallBack(Object o) {
        Task<R> task = (Task<R>) o;
        String taskName = "Worker:" + task.getWorkerId() + "-" + "Task:" + task.getId();
//        Print.tco(taskName + ":" + task.getResult());
        R result = task.getResult();
        resultMap.put(taskName, result);

        sum.getAndAdd((Integer) result);
    }

    // 启动所有的子任务
    public void execute() {

        for (; ; ) {
            // 从任务队列中获取任务，然后Worker节点轮询,  轮流分配任务
            for (Map.Entry<String, Worker<T, R>> entry : workers.entrySet()) {
                T task = null;
                try {
                    task = this.taskQueue.take();
                    Worker worker = entry.getValue();
                    worker.submit(task, this::resultCallBack);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    // 获取最终的结果
    public void printResult() {
        Print.tco("----------sum is :" + sum.get());
        for (Map.Entry<String, R> entry : resultMap.entrySet()) {
            String taskName = entry.getKey();
            Print.fo(taskName + ":" + entry.getValue());
        }

    }

}

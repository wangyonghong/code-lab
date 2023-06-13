package com.nien.demo.designmodel.masterworker;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Worker<T extends Task, R> {
    // 接收任务的阻塞队列
    private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();
    //worker 的编号
    static AtomicInteger index = new AtomicInteger(1);
    private int workerId;
    //执行任务的线程
    private Thread thread = null;

    public Worker() {
        this.workerId = index.getAndIncrement();
        thread = new Thread(() -> this.run());
        thread.start();
    }

    /**
     * 轮询执行任务
     */
    public void run() {
        // 轮询启动所有的子任务
        for (; ; ) {
            try {
                //从阻塞队列中提取任务
                T task = this.taskQueue.take();
                task.setWorkerId(workerId);
                task.execute();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //接收任务到异步队列
    public void submit(T task, Consumer<R> action) {
        task.resultAction = action;
        try {
            this.taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

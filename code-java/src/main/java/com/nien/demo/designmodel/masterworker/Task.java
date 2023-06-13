package com.nien.demo.designmodel.masterworker;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Data
public class Task<R> {
    static AtomicInteger index = new AtomicInteger(1);
    //任务的回调函数
    public Consumer<Task<R>> resultAction;
    //任务的id
    private int id;

    // worker ID
    private int workerId;

    //计算结果
    R result = null;

    public Task() {
        this.id = index.getAndIncrement();
    }

    public void execute() {
        this.result = this.doExecute();
        //执行回调函数
        resultAction.accept(this);
    }

    //由子类实现
    protected R doExecute() {
        return null;
    }
}

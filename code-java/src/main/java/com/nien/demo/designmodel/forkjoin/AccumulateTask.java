package com.nien.demo.designmodel.forkjoin;

import com.nien.demo.util.Print;

import java.util.concurrent.RecursiveTask;

public class AccumulateTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 10;
    //累加的起始编号
    private int start;
    //累加的结束编号
    private int end;

    public AccumulateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //判断任务的规模： 规模小则可以直接计算
        boolean canCompute = (end - start) <= THRESHOLD;
        //任务已经足够小，则可以直接计算
        if (canCompute) {
            //直接计算并返回结果,Recursive结束
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            Print.tco("执行任务，计算" + start + "到" + end + "的和，结果是：" + sum);

        } else {
            //任务过大，需要切割,Recursive 递归计算
            Print.tco("切割任务：将" + start + "到" + end + "的和一分为二");
            int middle = (start + end) / 2;
            //切割成两个子任务
            AccumulateTask lTask = new AccumulateTask(start, middle);
            AccumulateTask rTask = new AccumulateTask(middle + 1, end);
            //依次调用每个子任务的fork方法执行子任务
            lTask.fork();
            rTask.fork();
            //等待子任务的完成，依次调用每个子任务的join方法合并执行结果
            int leftResult = lTask.join();
            int rightResult = rTask.join();
            //合并子任务执行结果
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
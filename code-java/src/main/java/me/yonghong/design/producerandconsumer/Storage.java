package me.yonghong.design.producerandconsumer;

import java.util.LinkedList;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/17
 */
public class Storage implements AbstractStorage {

    /**
     * 仓库最大容量
     */
    private final int MAX_SIZE = 100;
    /**
     * 仓库存储的载体
     */
    private final LinkedList<Object> list = new LinkedList<>();
    private Integer productNo = 1;

    @Override
    public void consume(int num) {
        synchronized (list) {
            while (num > list.size()) {
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                    + list.size() + "\t暂时不能执行消费任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 消费条件满足，开始消费
            for (int i = 0; i < num; i++) {
                Integer productNo = (Integer)list.removeFirst();
                System.out.println("产品ID：" + productNo);
            }
            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

            // 通知其他线程（如果生产者在此时是阻塞的状态，现在可能被唤醒）
            list.notifyAll();
        }
    }

    @Override
    public void produce(int num) {
        synchronized (list) {
            while (num + list.size() > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                    + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产条件满足，开始消费
            for (int i = 0; i < num; i++) {
                list.addLast(productNo++);
            }
            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

            // 通知其他线程（如果消费者在此时是阻塞的状态，现在可能被唤醒）
            list.notifyAll();
        }
    }
}

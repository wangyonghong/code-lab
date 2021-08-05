package me.yonghong.design.producerandconsumer;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/17
 *
 * https://www.cnblogs.com/moongeek/p/7631447.html
 */
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        // 仓库对象
        AbstractStorage storage = new Storage();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p1.setBatch(3);
        p2.setNum(10);
        p2.setBatch(3);
        p3.setNum(10);
        p3.setBatch(20);
        p4.setNum(10);
        p4.setBatch(5);
        p5.setNum(10);
        p5.setBatch(1);
        p6.setNum(10);
        p6.setBatch(3);
        p7.setNum(80);
        p7.setBatch(5);

        // 设置消费者产品消费数量
        c1.setNum(30);
        c1.setBatch(30);
        c2.setNum(20);
        c2.setBatch(20);
        c3.setNum(15);
        c3.setBatch(15);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}

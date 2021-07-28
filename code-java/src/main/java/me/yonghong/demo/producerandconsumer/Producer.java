package me.yonghong.demo.producerandconsumer;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/17
 */
public class Producer extends Thread {

    /**
     * 每秒生产的数量
     */
    private int num = 0;

    /**
     * 生产批次数
     */
    private int batch = 0;

    /**
     * 所属的仓库
     */
    public AbstractStorage storage;

    public Producer(AbstractStorage storage) {
        this.storage = storage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    /**
     * 调用仓库 Storage 的生产函数
     */
    public void produce(int num) {
        while (batch != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.produce(num);
            batch--;
        }
    }

    @Override
    public void run() {
        produce(num);
    }
}

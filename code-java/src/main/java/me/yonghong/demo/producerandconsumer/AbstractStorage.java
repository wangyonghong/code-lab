package me.yonghong.demo.producerandconsumer;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/17
 *
 * 仓库
 */
public interface AbstractStorage {

    /**
     * 消费产品
     *
     * @param num 产品数量
     */
    void consume(int num);

    /**
     * 生产产品
     *
     * @param num 产品数量
     */
    void produce(int num);
}

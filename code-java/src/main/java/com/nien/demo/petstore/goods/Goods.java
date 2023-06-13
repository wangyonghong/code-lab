package com.nien.demo.petstore.goods;


import com.nien.demo.util.RandomUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class Goods implements IGoods {
    protected float price;
    protected int id;
    protected String goodName;
    protected int amount;
    protected Type goodType;
    private static int goodNo;  //商品对象累加编号


    protected Goods() {
        this.id = ++goodNo;
        this.amount = 1;
        this.price = 0;
        this.goodName = "未知商品";
    }

    /**
     * 生成一个随机类型商品
     *
     * @return 随机类型商品
     */
    public static IGoods produceOne() {
        Type randomType = Type.randType();
        return produceByType(randomType);
    }

    /**
     * 生成一个指定类型商品
     *
     * @param type
     * @return 指定类型商品
     */
    public static IGoods produceByType(Type type) {
        switch (type) {
            case PET:
                return new GoodsPet();
            case FOOD:
                return new GoodsFood();
            case CLOTHES:
                return new GoodsClothes();
        }
        return new Goods();
    }

    @Override
    public String toString() {
        return "商品{" +
                "ID=" + getID() +
                ",名称=" + getName() +
                ",价格=" + getPrice() +
                "}";
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 取得商品ID
     *
     * @return ID
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * 取得商品价格
     *
     * @return 价格
     */
    @Override
    public float getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price
     */
    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * 取得商品名称
     *
     * @return 名称
     */
    @Override
    public String getName() {
        return goodName;
    }

    /**
     * 取得商品数量
     *
     * @return 数量
     */
    @Override
    public int getAmount() {
        return amount;
    }

    /**
     * 取得商品类型
     *
     * @return 类型
     */
    @Override
    public Type getType() {
        return goodType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        return id == goods.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    public int compareTo(IGoods o) {
        if (o == null) throw new NullPointerException("Good object is null");
        return this.id - o.getID();
    }


    private static class GoodsPet extends Goods {
        private final static AtomicInteger PET_NO = new AtomicInteger(0);  //编号

        public GoodsPet() {
            super();
            this.goodType = Type.CLOTHES;
            this.goodName = "宠物-" + PET_NO.incrementAndGet();
            price = RandomUtil.randInRange(1000, 10000);
            amount = RandomUtil.randInMod(5);
        }
    }

    private static class GoodsClothes extends Goods {
        private final static AtomicInteger CLOTHES_NO = new AtomicInteger(0);  //编号

        public GoodsClothes() {
            super();
            this.goodType = Type.CLOTHES;
            this.goodName = "宠物衣服-" + CLOTHES_NO.incrementAndGet();
            price = RandomUtil.randInRange(50, 100);
            amount = RandomUtil.randInMod(5);
        }
    }

    private static class GoodsFood extends Goods {
        private final static AtomicInteger FOOD_NO = new AtomicInteger(0);  //编号

        public GoodsFood() {
            super();
            this.goodType = Type.FOOD;
            this.goodName = "宠物粮食-" + FOOD_NO.incrementAndGet();
            price = RandomUtil.randInRange(50, 100);
            amount = RandomUtil.randInMod(5);
        }
    }
}
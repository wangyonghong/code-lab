package com.nien.demo.petstore.store;


import com.nien.demo.petstore.goods.IGoods;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class CartItem {


    private IGoods goods;//商品
    private int amount;//商品数量

    public CartItem(IGoods goods) {
        this.goods = goods;
    }

    public void addAmount(int i) {
        amount += i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("商品编号：").append(goods.getID())
                .append(",商品名称：").append(goods.getName())
                .append(",单价：").append(goods.getPrice())
                .append(",数量：").append(this.amount)
                .append(",小计：").append(this.getItemMoney());
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return goods != null ? goods.equals(cartItem.goods) : cartItem.goods == null;
    }

    @Override
    public int hashCode() {
        return goods != null ? goods.hashCode() : 0;
    }

    public float getItemMoney() {
        return amount * goods.getPrice();
    }
}



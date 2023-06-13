package com.nien.demo.petstore.store;


import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class ShoppingCart {

    private Map<IGoods, CartItem> cartItems = new HashMap<>();

    public ShoppingCart addGoods(IGoods goods) {
        if (cartItems.containsKey(goods)) {
            CartItem cartItem = cartItems.get(goods);
            cartItem.addAmount(1);

        } else {
            CartItem cartItem = new CartItem(goods);
            cartItem.addAmount(1);
            cartItems.put(goods, cartItem);
        }
        return this;
    }

    public void showCart() {
        Print.hint("查看购物车信息");
        cartItems.entrySet().forEach(item ->
        {
            Print.cfo(item.getValue());
        });

        Print.cfo("foreach方式统计总价");
        float totalMoney = 0;
        for (CartItem item : cartItems.values()
        ) {
            totalMoney += item.getItemMoney();
        }
        Print.cfo("购物车价格合计" + totalMoney);

        Print.cfo("steam方式统计总价");

        OptionalDouble totalMoney2 = cartItems.values().stream()
                .mapToDouble(g -> g.getItemMoney()).reduce(Double::sum);
        Print.cfo("购物车价格合计" + totalMoney2.getAsDouble());
    }

    public Map<IGoods, CartItem> getCartItems() {
        return cartItems;
    }
}



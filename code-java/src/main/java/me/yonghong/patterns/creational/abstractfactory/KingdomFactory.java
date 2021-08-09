package me.yonghong.patterns.creational.abstractfactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();

}

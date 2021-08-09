package me.yonghong.patterns.creational.abstractfactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class OrcKingdomFactory implements KingdomFactory {

    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}

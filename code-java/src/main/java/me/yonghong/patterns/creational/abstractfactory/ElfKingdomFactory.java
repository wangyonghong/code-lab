package me.yonghong.patterns.creational.abstractfactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class ElfKingdomFactory implements KingdomFactory {

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }

}

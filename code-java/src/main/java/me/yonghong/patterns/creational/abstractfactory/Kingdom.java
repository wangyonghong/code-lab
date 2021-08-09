package me.yonghong.patterns.creational.abstractfactory;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@Getter
@Setter
public class Kingdom {

    private King king;
    private Castle castle;
    private Army army;

    public static class FactoryMaker {

        public enum KingdomType {
            ELF,
            ORC
        }

        public static KingdomFactory makeFactory(KingdomType type) {
            switch (type) {
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType not supported.");
            }
        }
    }
}

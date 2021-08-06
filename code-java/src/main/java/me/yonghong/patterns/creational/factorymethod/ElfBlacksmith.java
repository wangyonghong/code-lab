package me.yonghong.patterns.creational.factorymethod;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * 精灵铁匠
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class ElfBlacksmith implements Blacksmith {

    /**
     * 精灵兵工厂
     */
    private static final Map<WeaponType, ElfWeapon> ELF_ARSENAL;

    static {
        ELF_ARSENAL = new EnumMap<>(WeaponType.class);
        Arrays.stream(WeaponType.values()).forEach(type -> ELF_ARSENAL.put(type, new ElfWeapon(type)));
    }

    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return ELF_ARSENAL.get(weaponType);
    }

    @Override
    public String toString() {
        return "The elf blacksmith";
    }
}

package me.yonghong.patterns.creational.factorymethod;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * 兽人铁匠
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class OrcBlacksmith implements Blacksmith {

    /**
     * 兽人兵工厂
     */
    private static final Map<WeaponType, OrcWeapon> ORC_ARSENAL;

    static {
        ORC_ARSENAL = new EnumMap<>(WeaponType.class);
        Arrays.stream(WeaponType.values()).forEach(type -> ORC_ARSENAL.put(type, new OrcWeapon(type)));
    }

    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return ORC_ARSENAL.get(weaponType);
    }

    @Override
    public String toString() {
        return "The orc blacksmith";
    }
}

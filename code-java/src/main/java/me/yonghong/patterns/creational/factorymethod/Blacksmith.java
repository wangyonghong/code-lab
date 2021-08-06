package me.yonghong.patterns.creational.factorymethod;

/**
 * 铁匠
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public interface Blacksmith {

    Weapon manufactureWeapon(WeaponType weaponType);

}

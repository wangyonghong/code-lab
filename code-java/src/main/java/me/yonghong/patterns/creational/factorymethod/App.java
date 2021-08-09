package me.yonghong.patterns.creational.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 工厂方法模式
 *
 * @author yonghongwang#163.com
 * @see java.net.URLStreamHandlerFactory#createURLStreamHandler(String)
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        Blacksmith blacksmith = new OrcBlacksmith();
        Weapon weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        log.info("{} manufactured {}", blacksmith, weapon);
        weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
        log.info("{} manufactured {}", blacksmith, weapon);

        blacksmith = new ElfBlacksmith();
        weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        log.info("{} manufactured {}", blacksmith, weapon);
        weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
        log.info("{} manufactured {}", blacksmith, weapon);
    }
}

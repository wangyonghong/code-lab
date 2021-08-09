package me.yonghong.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * 建造者模式
 *
 * @author yonghongwang#163.com
 * @see java.lang.StringBuilder
 * @see java.lang.StringBuffer
 * @see java.lang.Appendable
 * @see java.nio.ByteBuffer
 * @see java.net.http.HttpClient.Builder
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        var mage = new Hero.Builder(Profession.MAGE, "Riobard")
            .withHairColor(HairColor.BLACK)
            .withWeapon(Weapon.DAGGER)
            .build();
        log.info(mage.toString());

        var warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill")
            .withHairColor(HairColor.BLOND)
            .withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD)
            .build();
        log.info(warrior.toString());

        var thief = new Hero.Builder(Profession.THIEF, "Desmond")
            .withHairType(HairType.BALD)
            .withWeapon(Weapon.BOW)
            .build();
        log.info(thief.toString());
    }
}

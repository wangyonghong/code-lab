package me.yonghong.patterns.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AlchemistShop {
    private final List<Potion> topShelf;
    private final List<Potion> bottomShelf;

    public AlchemistShop() {
        var factory = new PotionFactory();
        topShelf = List.of(
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.HEALING)
        );
        bottomShelf = List.of(
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.HOLY_WATER),
                factory.createPotion(PotionType.HOLY_WATER)
        );
    }

    public final List<Potion> getTopShelf() {
        return List.copyOf(this.topShelf);
    }

    public final List<Potion> getBottomShelf() {
        return List.copyOf(this.bottomShelf);
    }

    public void drinkPotions() {
        log.info("Drinking top shelf potions\n");
        topShelf.forEach(Potion::drink);
        log.info("Drinking bottom shelf potions\n");
        bottomShelf.forEach(Potion::drink);
    }
}

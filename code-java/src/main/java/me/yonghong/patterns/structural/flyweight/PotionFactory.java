package me.yonghong.patterns.structural.flyweight;

import java.util.EnumMap;
import java.util.Map;

public class PotionFactory {

    private final Map<PotionType, Potion> potions;

    public PotionFactory() {
        potions = new EnumMap<>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        var potion = potions.get(type);
        if (potion == null) {
            switch (type) {
                case HEALING -> {
                    potion = new HealingPotion();
                    potions.put(type, potion);
                }
                case HOLY_WATER -> {
                    potion = new HolyWaterPotion();
                    potions.put(type, potion);
                }
                case INVISIBILITY -> {
                    potion = new InvisibilityPotion();
                    potions.put(type, potion);
                }
                case POISON -> {
                    potion = new PoisonousPotion();
                    potions.put(type, potion);
                }
                case STRENGTH -> {
                    potion = new StrengthPotion();
                    potions.put(type, potion);
                }
                default -> {
                }
            }
        }
        return potion;
    }
}

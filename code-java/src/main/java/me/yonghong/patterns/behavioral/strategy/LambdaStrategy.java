package me.yonghong.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaStrategy {

    public enum Strategy implements DragonSlayingStrategy {
        MeleeStrategy(() -> log.info(
                "With your Excalibur you severe the dragon's head!")),
        ProjectileStrategy(() -> log.info(
                "You shoot the dragon with the magical crossbow and it falls dead on the ground!")),
        SpellStrategy(() -> log.info(
                "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!"));

        private final DragonSlayingStrategy dragonSlayingStrategy;

        Strategy(DragonSlayingStrategy dragonSlayingStrategy) {
            this.dragonSlayingStrategy = dragonSlayingStrategy;
        }

        @Override
        public void execute() {
            dragonSlayingStrategy.execute();
        }
    }
}

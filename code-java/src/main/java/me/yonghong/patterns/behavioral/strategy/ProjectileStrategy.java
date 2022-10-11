package me.yonghong.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectileStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        log.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
    }
}

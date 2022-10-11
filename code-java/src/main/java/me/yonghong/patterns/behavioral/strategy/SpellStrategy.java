package me.yonghong.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpellStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        log.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!");
    }
}

package me.yonghong.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeleeStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        log.info("With your Excalibur you sever the dragon's head!");
    }
}

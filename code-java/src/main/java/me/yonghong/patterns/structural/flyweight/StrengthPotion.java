package me.yonghong.patterns.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrengthPotion implements Potion {
    @Override
    public void drink() {
        log.info("You feel strong. (Potion={})", System.identityHashCode(this));
    }
}

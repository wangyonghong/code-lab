package me.yonghong.patterns.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoisonousPotion implements Potion {
    @Override
    public void drink() {
        log.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this));
    }
}

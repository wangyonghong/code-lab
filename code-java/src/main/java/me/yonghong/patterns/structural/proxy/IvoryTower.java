package me.yonghong.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@Slf4j
public class IvoryTower implements WizardTower {

    @Override
    public void enter(Wizard wizard) {
        log.info("{} enters the tower.", wizard);
    }
}

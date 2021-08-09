package me.yonghong.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@Slf4j
public class WizardTowerProxy implements WizardTower {

    private static final int NUM_WIZARDS_ALLOWED = 3;

    private int numWizards;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            log.info("{} is not allowed to enter!", wizard);
        }
    }
}
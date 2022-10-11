package me.yonghong.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @link <a href="https://java-design-patterns.com/patterns/strategy/"></a>
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Green dragon spotted ahead!");
        var dragonSlayer = new DragonSlayer(new MeleeStrategy());
        dragonSlayer.goToBattle();
        log.info("Red dragon emerges.");
        dragonSlayer.changeStrategy(new ProjectileStrategy());
        dragonSlayer.goToBattle();
        log.info("Black dragon lands before you.");
        dragonSlayer.changeStrategy(new SpellStrategy());
        dragonSlayer.goToBattle();

        log.info("Green dragon spotted ahead!");
        dragonSlayer.changeStrategy(LambdaStrategy.Strategy.MeleeStrategy);
        dragonSlayer.goToBattle();
        log.info("Red dragon emerges.");
        dragonSlayer.changeStrategy(LambdaStrategy.Strategy.ProjectileStrategy);
        dragonSlayer.goToBattle();
        log.info("Black dragon lands before you.");
        dragonSlayer.changeStrategy(LambdaStrategy.Strategy.SpellStrategy);
        dragonSlayer.goToBattle();
    }
}

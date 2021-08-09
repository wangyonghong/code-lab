package me.yonghong.patterns.creational.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象工厂
 *
 * @author yonghongwang#163.com
 * @see javax.xml.parsers.DocumentBuilderFactory
 * @see javax.xml.transform.TransformerFactory
 * @see javax.xml.xpath.XPathFactory
 * @since 2021/8/6
 */
@Slf4j
public class App implements Runnable {

    private final Kingdom kingdom = new Kingdom();

    public Kingdom getKingdom() {
        return kingdom;
    }

    public static void main(String[] args) {
        var app = new App();
        app.run();
    }

    @Override
    public void run() {
        log.info("elf kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());

        log.info("orc kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());
    }

    public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
        final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
        kingdom.setKing(kingdomFactory.createKing());
        kingdom.setCastle(kingdomFactory.createCastle());
        kingdom.setArmy(kingdomFactory.createArmy());
    }
}

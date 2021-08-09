package me.yonghong.patterns.creational.abstractfactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class OrcArmy implements Army {

    static final String DESCRIPTION = "This is the orc army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

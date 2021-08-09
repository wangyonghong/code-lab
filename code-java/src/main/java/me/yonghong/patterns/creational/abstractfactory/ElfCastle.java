package me.yonghong.patterns.creational.abstractfactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class ElfCastle implements Castle {

    static final String DESCRIPTION = "This is the elven castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

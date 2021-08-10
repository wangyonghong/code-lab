package me.yonghong.patterns.structural.dynamicproxy;

/**
 * 明星，可能是影歌双栖
 *
 * @author yonghongwang#163.com
 * @since 2021/8/10
 */
public class Star implements IMovieStar, ISingerStar {

    private String mName;

    public Star(String name) {
        mName = name;
    }

    @Override
    public void movieShow(int money) {
        System.out.println(mName + " 出演了部片酬 " + money + " 元的电影");
    }

    @Override
    public void tvShow(int money) {
        System.out.println(mName + " 出演了部片酬 " + money + " 元的电视剧");
    }

    @Override
    public void sing(int money) {
        System.out.println(mName + " 唱了一首演出费 " + money + " 元的歌");
    }
}
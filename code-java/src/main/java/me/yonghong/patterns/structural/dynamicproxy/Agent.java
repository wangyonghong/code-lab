package me.yonghong.patterns.structural.dynamicproxy;

/**
 * 经纪人，代理某个明星，有活动、广告、电影都是先找经纪人沟通
 *
 * @author yonghongwang#163.com
 * @since 2021/8/10
 */
public class Agent implements IMovieStar {

    /**
     * 代理的明星
     */
    Star mTarget;

    public Agent(Star target) {
        mTarget = target;
    }

    @Override
    public void movieShow(int money) {
        if (money < 30000000) {
            System.out.println(money + "块钱？！你雇 HuangZiTao 演电影去吧！");
            return;
        }
        mTarget.movieShow(money);
    }

    @Override
    public void tvShow(int money) {
        if (money < 30000000) {
            System.out.println(money + "块钱？！你雇 HuangZiTao 演电视剧去吧！");
            return;
        }
        mTarget.tvShow(money);
    }
}

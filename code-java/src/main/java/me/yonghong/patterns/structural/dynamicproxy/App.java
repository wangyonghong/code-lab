package me.yonghong.patterns.structural.dynamicproxy;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class App {

    public static void main(String[] args) {
        // 静态代理
        Star huangBo = new Star("HuangBo");
        IMovieStar movieStar = new Agent(huangBo);
        // 网上查到，2016年黄渤的片酬达到了 3000W ，我得敲多少年代码额呜呜
        movieStar.movieShow(1000000000);
        movieStar.tvShow(5);

        // 动态代理
        ProxyHandler proxyHandler = new ProxyHandler(huangBo);
        movieStar = (IMovieStar) proxyHandler.getProxy();
        movieStar.movieShow(1000000000);
        movieStar.tvShow(100);

        //黄渤早年其实是个歌手！唱歌不得志只好去演戏，成为影帝后人们才关注他的歌声，真是个“看脸、看名”的世界
        ISingerStar singerStar = (ISingerStar) proxyHandler.getProxy();
        singerStar.sing(1000000000);
        singerStar.sing(1024);
    }
}

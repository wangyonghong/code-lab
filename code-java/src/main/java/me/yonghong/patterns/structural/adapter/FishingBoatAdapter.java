package me.yonghong.patterns.structural.adapter;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public class FishingBoatAdapter implements RowingBoat {

    private final FishingBoat boat = new FishingBoat();

    @Override
    public final void row() {
        boat.sail();
    }
}

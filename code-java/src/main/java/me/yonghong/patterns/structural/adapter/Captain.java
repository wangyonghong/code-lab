package me.yonghong.patterns.structural.adapter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Captain {

    private RowingBoat rowingBoat;

    void row() {
        rowingBoat.row();
    }

}

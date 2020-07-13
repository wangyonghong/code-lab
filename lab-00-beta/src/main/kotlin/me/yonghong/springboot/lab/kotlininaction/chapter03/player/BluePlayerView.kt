package me.yonghong.springboot.lab.kotlininaction.chapter03.player

import javax.swing.JOptionPane

class BluePlayerView : PlayerView {

    override fun showView() {
        JOptionPane.showConfirmDialog(null, "显示蓝色播放器", "播放器", JOptionPane.DEFAULT_OPTION)
    }

    override fun getPlayButton() {
        println("显示蓝色button")
    }
}
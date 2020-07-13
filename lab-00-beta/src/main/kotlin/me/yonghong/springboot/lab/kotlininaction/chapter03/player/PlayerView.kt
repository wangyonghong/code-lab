package me.yonghong.springboot.lab.kotlininaction.chapter03.player

import javax.swing.JOptionPane

interface PlayerView {
    fun showView()
    fun getPlayButton()
}

class GreenPlayView : PlayerView {

    override fun showView() {
        JOptionPane.showConfirmDialog(null, "显示绿色播放器", "播放器", JOptionPane.DEFAULT_OPTION)
    }

    override fun getPlayButton() {
        println("显示绿色button")
    }
}

class MediaPlayerView(playerView: PlayerView) : PlayerView by playerView
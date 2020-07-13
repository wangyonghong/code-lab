package me.yonghong.springboot.lab.kotlininaction.chapter03.player

class PlayerUI private constructor() {
    companion object {
        fun get(): PlayerUI {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = PlayerUI()
    }

    fun showPlayer(user: User) {
        MediaPlayerView(getPlayerView(user.playerViewType)).showView()
    }
}
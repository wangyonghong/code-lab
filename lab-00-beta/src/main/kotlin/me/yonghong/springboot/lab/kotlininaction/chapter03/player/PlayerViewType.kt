package me.yonghong.springboot.lab.kotlininaction.chapter03.player

// 密闭类的拓展性
sealed class PlayerViewType {
    object GREEN : PlayerViewType()
    object BLUE : PlayerViewType()
    class VIP(var title: String? = null, var message: String? = null) : PlayerViewType()
}

fun getPlayerView(type: PlayerViewType) = when (type) {
    PlayerViewType.GREEN -> GreenPlayView()
    PlayerViewType.BLUE -> BluePlayerView()
    is PlayerViewType.VIP -> VIPPlayer(type.title, type.message)
}

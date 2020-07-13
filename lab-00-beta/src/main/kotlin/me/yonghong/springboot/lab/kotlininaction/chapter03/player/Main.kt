package me.yonghong.springboot.lab.kotlininaction.chapter03.player

// 音乐播放器，有系统内置的两种颜色的皮肤
// 每个用户可以选择自己的播放器皮肤颜色
// 当不同的用户登录以后，显示不同的播放器皮肤
// 同时需要注意皮肤颜色的可拓展性

fun main(args: Array<String>) {
    val user = User(1, "Li Hua", PlayerViewType.VIP("VIP播放器", "VIP的消息"))
    PlayerUI.get().showPlayer(user)

}
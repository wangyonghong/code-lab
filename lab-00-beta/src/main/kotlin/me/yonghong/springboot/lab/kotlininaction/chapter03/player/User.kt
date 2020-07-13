package me.yonghong.springboot.lab.kotlininaction.chapter03.player

data class User(var id: Int,
                var name: String,
                var playerViewType: PlayerViewType = PlayerViewType.BLUE)
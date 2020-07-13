package me.yonghong.springboot.lab.kotlininaction.chapter03

// 密闭类
sealed class SuperCommand {
    object A : SuperCommand()
    object B : SuperCommand()
    object C : SuperCommand()
    object D : SuperCommand()
    class E(var pace: Int) : SuperCommand()
}
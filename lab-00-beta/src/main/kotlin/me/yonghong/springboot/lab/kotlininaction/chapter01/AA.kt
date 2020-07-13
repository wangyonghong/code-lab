package me.yonghong.springboot.lab.kotlininaction.chapter01

class AA : AInterface {
    // kotlin 没有 封装类
    override fun print(num: Int) {
        println("kotlin int: $num")
    }
}
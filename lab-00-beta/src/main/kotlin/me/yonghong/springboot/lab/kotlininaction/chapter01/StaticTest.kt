package me.yonghong.springboot.lab.kotlininaction.chapter01

object StaticTest {
    @JvmStatic
    fun sayMessage(msg: String?) {
        println("$msg")
    }
}

fun main(args: Array<String>) {
    StaticTest.sayMessage("hello kotlin!")
    StaticTest.sayMessage(null)
}
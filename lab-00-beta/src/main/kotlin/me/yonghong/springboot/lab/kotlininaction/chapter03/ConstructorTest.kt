package me.yonghong.springboot.lab.kotlininaction.chapter03

open class View(a: Int, b: Int) {

    init {
        println("View constructor")
        var a: Int = a
        var b: Int = b
    }
}

// 继承父类构造函数
open class MainActivity(a: Int, b: Int, c: Int) : View(a, b) {

    init {
        println("MainActivity constructor")
        var c: Int = c
    }

    // 继承主构造函数
    constructor(a: Int, b: Int) : this(a, b, 0) {
        println("MainActivity constructor 2")
    }
}

fun main(args: Array<String>) {
    MainActivity(1, 2, 3)
    MainActivity(1, 2)
}
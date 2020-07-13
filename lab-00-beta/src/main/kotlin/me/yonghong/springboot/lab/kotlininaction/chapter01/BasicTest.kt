package me.yonghong.springboot.lab.kotlininaction.chapter01

var age: Int = 20

// 可以自动推断类型
var age2 = 20
var name: String = "Kotlin"

// 可以为空
// String 和 String? 是两种不同的类型。
var name2: String? = null

fun main(args: Array<String>) {
    printLen(name)

    name2 = name
    // 不能直接赋值，如果确定 name2 不为空，可以加 !! 强转，如果为空会报空指针
    name = name2!!
}

// 入参 String str
// 返回值 String
fun printLen(str: String): String {
    // kotlin 的模板语法
    println("这个字符串是: $str, 长度为: ${str.length}")
    return str
}
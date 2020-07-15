package me.yonghong.springboot.lab.kotlininaction.chapter04

import java.io.File

fun `1234`() {
    println("test1234")
}

fun ` `() {
    println("test 1 space")
}

fun `  `() {
    println("test 2 spaces")
}

// 反引号可以让一些非法字符变得合法
fun main(args: Array<String>) {
    `1234`()
    ` `()
    `  `()

    val a = "string"
    val b = "string"
    val c = java.lang.String("string")
    val d = java.lang.String("string")
    val e = StringBuilder("string").toString()
    val f = String("string".toByteArray())

    // kotlin   | Java
    // a == b   | a.equals(b)
    // a === b  | a == b
    println(a == b)
    println(a === b)
    println(c == d)
    println(c === d)

    println(a == e)
    println(a === e)
    println(a == f)
    println(a === f)

    val aFile: File = A("")

}

/**
 * [kotlin.collections.ArrayList]
 * [kotlin.collections.LinkedHashMap]
 * [kotlin.collections.HashMap]
 * [kotlin.collections.LinkedHashSet]
 * [kotlin.collections.HashSet]
 */
public typealias A = File
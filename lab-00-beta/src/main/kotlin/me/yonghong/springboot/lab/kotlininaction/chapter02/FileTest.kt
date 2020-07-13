package me.yonghong.springboot.lab.kotlininaction.chapter02

import java.io.File

fun main(args: Array<String>) {
    val file = File("build.gradle")
    // readText 拓展函数
    println(file.readText())
}
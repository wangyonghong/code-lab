package me.yonghong.springboot.lab.kotlininaction.chapter04

fun main(args: Array<String>) {

    for (i in 1..10) {
        print("$i ")
    }
    println("")

    for (i in 1 until 10) {
        print("$i ")
    }
    println("")

    for (i in 10 downTo 1) {
        print("$i ")
    }
    println("")

    for (i in 1..10 step 2) {
        print("$i ")
    }
    println("")

    repeat(10) {
        print("$it ")
    }
    println("")

    val list = arrayListOf<String>("a", "b", "c", "d")
    for (str in list) {
        print("$str ")
    }
    println("")

    for ((index, value) in list.withIndex()) {
        print("$index,$value  ")
    }
}
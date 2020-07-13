package me.yonghong.springboot.lab.kotlininaction.chapter01

fun main(args: Array<String>) {
    format("")
}

fun format(str: String) {
    var fmt1 = A.format(str)
    println(fmt1)
    // 空指针
    // println(fmt1.length)
    // 不能为空
    // var fmt2: String = A.format(str)
    var fmt3: String? = A.format(str)
    // 不会报空指针，输出 null
    println(fmt3?.length)
}
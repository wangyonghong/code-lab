package me.yonghong.springboot.lab.kotlininaction.chapter02

import kotlin.jvm.functions.FunctionN

fun main(args: Array<String>) {

    val t1 = Thread({ -> Unit })
    // 如果 lambda 没有参数，可以省略 ->
    val t2 = Thread({})
    // 如果 lambda 是函数的最后一个参数，可以将大括号放在小括号外面
    val t3 = Thread() {}
    // 如果函数只有一个参数且这个参数是 lambda，则可以省略小括号
    val t4 = Thread {}

    echo("hello")

    fun23(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)

    // lambda 作为最后一个参数可以放在小括号外面
    // 函数是一等公民
    onlyif(true) {
        println("打印日志")
    }

    val runnable = Runnable {
        println("Runnable::run")
    }
    val function: () -> Unit
    function = runnable::run
    onlyif(true, function)

    // Kotlin 的 Lambda 是匿名对象

}

// 闭包声明
val echo = { name: String -> println(name) }

/**
 * [FunctionN] 支持23个以上的参数
 */
@SinceKotlin("1.3")
val fun23 = { a: Int, b: Int, c: Int, d: Int, e: Int,
              f: Int, g: Int, h: Int, i: Int, j: Int,
              k: Int, l: Int, m: Int, n: Int, o: Int,
              p: Int, q: Int, r: Int, s: Int, t: Int,
              u: Int, v: Int, w: Int ->
    println("fun 23")
}

// inline 修饰高阶函数，其他地方不推荐，会加重编译器负担
// 编译器会拆解方法的调用为语句的调用，减少对象的创建
inline fun onlyif(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) block()
}
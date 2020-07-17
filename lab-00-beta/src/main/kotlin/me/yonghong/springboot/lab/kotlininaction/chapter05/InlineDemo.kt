package me.yonghong.springboot.lab.kotlininaction.chapter05

/**
 * 在 Kotlin中，内部的Lambda是不允许中断外部函数执行的
 * inline 的 lambda 可以中断外部函数调用
 * crossinline 不允许 inline 的 Lambda 中断外部函数执行
 * noinline 拒绝内联
 */
fun main(args: Array<String>) {

    test1 {
        println("hello test1")
        return@test1
    }

    test3 {
        println("hello test3")
        return@test3
    }

    test4({
        println("hello test4 1")
        return@test4
    }, {
        println("hello test4 2")
        return
    })

    test2 {
        println("hello test2")
        return
    }

    // 匿名函数没有 inline
    val test5 = fun() {
        println("hello test5")
        return
    }
    test5()

    // 内部函数也不能中断外部函数
    fun test6() {
        println("hello test5")
        return
    }
    test6()

    println("hello end")
}

fun test1(l: () -> Unit) {
    l.invoke()
}

inline fun test2(l: () -> Unit) {
    l.invoke()
}

inline fun test3(crossinline l: () -> Unit) {
    l.invoke()
}

// m 是 crossinline 不可以中断外部函数
// n 可以中断外部函数
inline fun test4(crossinline m: () -> Unit, n: () -> Unit) {
    m.invoke()
    n.invoke()
}

// 返回函数，如果中断，返回值就不是 函数了
inline fun test6(m: () -> Unit, noinline n: () -> Unit): () -> Unit {
    m.invoke()
    n.invoke()
    return n
}
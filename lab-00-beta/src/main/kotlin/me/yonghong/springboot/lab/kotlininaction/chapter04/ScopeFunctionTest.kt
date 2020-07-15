package me.yonghong.springboot.lab.kotlininaction.chapter04

// 作用域函数
fun main(args: Array<String>) {
    val user = User(19, "Li Hua")

    // let与run都会返回闭包的执行结果，区别在于let有闭包参数，而run没有闭包参数
    val letResult1 = user.let { user -> "let::${user.javaClass}" }
    // lambda只有一个参数可以省略不写，用it替代
    val letResult2 = user.let { "let::${it.javaClass}" }
    println(letResult1)
    println(letResult2)
    val runResult = user.run { "run::${this.javaClass}" }
    println(runResult)

    // also与apply都不反回闭包执行的结果，区别在于also有闭包参数，而apply没有
    // 可以连续调用，链式操作
    user.also {
        println("also::${it.javaClass}")
    }.apply {
        println("apply::${this.javaClass}")
    }

    // takeIf 的闭包返回一个判断结果，为false时，takeIf函数会返回空
    // takeUnless 与 takeIf 刚好相反，闭包返回判断结果，为true时，takeIf函数会返回空

    user.takeIf { it.name.isNotEmpty() }?.also { println("姓名为${it.name}") } ?: println("姓名为空")
    user.takeUnless { it.name.isEmpty() }?.also { println("姓名为${it.name}") } ?: println("姓名为空")

    // 重复执行当前闭包
    repeat(3) {
        println("repeat")
        // 循环次数
        println(it)
    }

    // with 比较特殊，不是拓展方法形式存在的，是一个顶级函数
    with(user) {
        this.name = "with ${this.name}"
        println(this.name)
    }
}
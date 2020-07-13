package me.yonghong.springboot.lab.kotlininaction.chapter02

fun main(args: Array<String>) {
    print("Kotlin!")
    print()
    function()
}

fun print(name: String = "kotlin") {
    println(name)
}

// 函数的嵌套，不推荐使用，除了 递归 不希望被外部访问的函数
fun function() {
    val str = "hello"
    fun say(count: Int = 10) {
        println("$str $count")
        if (count > 0) {
            say(count - 1)
        }
    }
    say()
    say(5)
}

fun echo(name: String) = println(name)
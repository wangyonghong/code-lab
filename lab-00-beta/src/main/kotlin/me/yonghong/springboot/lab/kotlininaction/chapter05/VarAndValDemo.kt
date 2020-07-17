package me.yonghong.springboot.lab.kotlininaction.chapter05

fun main(args: Array<String>) {
    val hello = Hello()
    println(hello.str1)
    println(hello.str2)
    println(hello.str3)
}

// val 不能有 setter
class Hello {
    var str1: String? = null
        get() {
            return field + "hello"
        }
        set(value) {
            field = value
        }

    var str2: String? = null
        get() = field + "hello"
        set(value) {
            field = value + "hi"
        }

    val str3: String? = null
        get() = field + "hello kotlin"
}
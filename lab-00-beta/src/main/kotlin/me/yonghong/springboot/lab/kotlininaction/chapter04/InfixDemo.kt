package me.yonghong.springboot.lab.kotlininaction.chapter04

sealed class CompareResult {
    object LESS : CompareResult() {
        override fun toString(): String {
            return "小于"
        }
    }

    object MORE : CompareResult() {
        override fun toString(): String {
            return "大于"
        }
    }

    object EQUAL : CompareResult() {
        override fun toString(): String {
            return "等于"
        }
    }
}

// 中缀表达式
// 函数名前有个点，点前有个类型
// Int 是 vs 函数的接收者类型，只有 Int 类型才能调用这个函数
infix fun Int.vs(num: Int): CompareResult =
        if (this - num < 0) {
            CompareResult.LESS
        } else if (this - num > 0) {
            CompareResult.MORE
        } else {
            CompareResult.EQUAL
        }

infix fun Double.vs(num: Double): CompareResult =
        if (this - num < 0) {
            CompareResult.LESS
        } else if (this - num > 0) {
            CompareResult.MORE
        } else {
            CompareResult.EQUAL
        }


infix fun Long.vs(num: Long): CompareResult =
        if (this - num < 0) {
            CompareResult.LESS
        } else if (this - num > 0) {
            CompareResult.MORE
        } else {
            CompareResult.EQUAL
        }

// 注意：一个函数只有用于两个角色类似的对象时才将其声明为中缀函数
// 推荐示例：and to zip
// 反例：add

// 如果一个方法会改动其接收者，那不不要声明为中缀形式
fun main(args: Array<String>) {
    println(5 vs 6)
    println(5.0 vs 6.0)
    println(5L vs 6L)
    println(5.vs(6))
}

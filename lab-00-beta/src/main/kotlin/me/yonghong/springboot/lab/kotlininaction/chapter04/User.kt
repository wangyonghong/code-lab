package me.yonghong.springboot.lab.kotlininaction.chapter04

class User(var age: Int, var name: String) {
    // operator 将一个函数标记为重载一个操作符或者实现一个约定
    operator fun component1() = age
    operator fun component2() = name
    operator fun component3() = name + "3"

}


// 解构 拆解
fun main(args: Array<String>) {
    val user = User(18, "Li Hua")
//    var (age, name) = user
    var (age, name, nickname) = user

    println("$age, $name, $nickname")

    val map = mapOf<String, String>("key" to "key", "value" to "value")
    for ((k, v) in map) {
        println("$k --- $v")
    }
}

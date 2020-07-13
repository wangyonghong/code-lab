package me.yonghong.springboot.lab.kotlininaction.chapter03

// public final
// getter()
// setter()
// toString()
// hashCode()
// equals()
// copy()
data class User(var id: Long, var name: String) {

}

fun main(args: Array<String>) {
    val user = User(1, "Li Hua")
    println(user.toString())
}
package me.yonghong.kt.core.ch08

import kotlin.reflect.*

data class User(val name: String, val age: Int)

fun toMap(a: User): Map<String, Any> {
    return hashMapOf("name" to a.name, "age" to a.age)
}

object Mapper {
    //获取A的所有属性
    fun <A : Any> toMap(a: A): Map<String, Any?> {
        return a::class.members.associate { m ->
            val p = m as KProperty<*>
            p.name to p.call(a)
        }
    }
}

fun main(args: Array<String>) {
    val u = User("foo", 11)
    println(toMap(u))
    println(Mapper.toMap(u))
}

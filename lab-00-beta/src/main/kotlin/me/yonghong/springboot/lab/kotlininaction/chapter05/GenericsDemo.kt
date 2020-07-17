package me.yonghong.springboot.lab.kotlininaction.chapter05

import com.google.gson.Gson

// Kotlin 的真泛型
fun main(args: Array<String>) {
    val test = Test<A>()
    test.add(A())
//    不可以
//    test.add(B())
    test.add(C())

    val a = View.invoke<Presenter>().presenter
    val b = View<Presenter>().presenter
    println(a)
    println(b)
}

// 限制多个类型
class Test<T> where T : Callback, T : Runnable {

    fun add(t: T) {
        t.run()
        t.callback()
    }
}

open class A : Callback, Runnable {
    override fun callback() {
        println("callback")
    }

    override fun run() {
        println("run")
    }
}

class B : Callback {
    override fun callback() {
        println("callback")
    }
}

class C : Callback, A() {
    override fun callback() {
        println("callback")
    }
}

interface Callback {
    fun callback()
}


// reified 真泛型
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}

class View<T>(private val clazz: Class<T>) {
    val presenter: T by lazy { clazz.getDeclaredConstructor().newInstance() }

    companion object {
        inline operator fun <reified T> invoke() = View(T::class.java)
    }
}

class Presenter {
    override fun toString(): String {
        return "Presenter"
    }
}
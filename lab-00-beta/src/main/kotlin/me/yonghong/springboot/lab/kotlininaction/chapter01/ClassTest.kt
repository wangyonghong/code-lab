package me.yonghong.springboot.lab.kotlininaction.chapter01

import kotlin.reflect.KClass

fun main(args: Array<String>) {
    // 类的传递
    testClass(JavaMain::class.java)
    testClass(KotlinMain::class)

    // in 关键词的处理
    println(JavaMain.`in`)
    println(KotlinMain.`in`)
}

fun testClass(clazz: Class<JavaMain>) {
    println(clazz.simpleName)
}

fun testClass(clazz: KClass<KotlinMain>) {
    println(clazz.simpleName)
}
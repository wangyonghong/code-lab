package me.yonghong.springboot.lab.kotlininaction.chapter06

import kotlinx.coroutines.*
import java.net.URL

fun main(args: Array<String>) = runBlocking {

    val job = launch {
        repeat(10) { i ->
            println("挂起中 $i ...")
            delay(500L)
        }
    }

    println(getHtml())
}

suspend fun getHtml(): String {
    return withContext(Dispatchers.Default) { URL("https://www.baidu.com").readText() }
}

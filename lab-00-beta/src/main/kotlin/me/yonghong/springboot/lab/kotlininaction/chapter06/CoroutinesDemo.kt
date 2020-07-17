package me.yonghong.springboot.lab.kotlininaction.chapter06

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 通过提升CPU利用率减少线程切换，进而提升程序运行效率
 *
 * 可控制：协程能做到可被控制的发起子任务
 * 轻量级：协程非常小，占用资源比线程还少
 * 语法糖：使多任务或多线程切换不再使用回调语法
 */
// runBlocking 用于执行协程任务，通常只用于启动最外层协程
fun main(args: Array<String>) = runBlocking {

    // 用于执行协程任务
    // launch: Job
    val job = launch {
        repeat(1000) { i ->
            println("挂起中 $i ...")
            delay(500L)
        }
    }

    // 用于执行协程任务，并得到返回结果
    // async/await
    val job2 = async {
        delay(500L)
        println("job2")
        return@async "hello"
    }
    println(job2.await())

    delay(1500L)
    println("main:: 主线程等待中")

    job.cancel()
    job.join()

    println("main::即将完成退出")
}
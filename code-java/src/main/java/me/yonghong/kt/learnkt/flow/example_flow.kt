package me.yonghong.kt.learnkt.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun simple(): Flow<Int> = flow {
    for (i in 1..20) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(1000) { // Timeout after 250ms
        simple().collect { value -> println(value) }
    }
    println("Done")
}
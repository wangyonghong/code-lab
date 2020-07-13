package me.yonghong.springboot.lab.kotlininaction

import java.io.IOException

class ExceptionTest {

    companion object {

        @Throws(IOException::class)
        fun test1() {
            throw IOException()
        }

        @Throws
        fun test2() {
            throw IOException()
        }

        fun test3() {
            throw IOException()
        }
    }
}
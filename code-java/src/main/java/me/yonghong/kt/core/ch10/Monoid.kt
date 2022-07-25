package me.yonghong.kt.core.ch10

interface Monoid<A> {
    fun zero(): A
    fun A.append(b: A): A
}

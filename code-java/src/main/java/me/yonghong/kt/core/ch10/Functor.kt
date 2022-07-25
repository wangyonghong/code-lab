package me.yonghong.kt.core.ch10

interface Functor<F> {
    abstract fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
}

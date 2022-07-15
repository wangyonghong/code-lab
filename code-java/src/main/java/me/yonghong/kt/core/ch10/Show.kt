package me.yonghong.kt.core.ch10

interface Show<F> {
  fun <A> Kind<F, A>.show(): String
}

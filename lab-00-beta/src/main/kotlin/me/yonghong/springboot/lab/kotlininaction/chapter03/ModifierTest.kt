package me.yonghong.springboot.lab.kotlininaction.chapter03

// open class = public
open class ModifierTest {
    public fun fun1() {}
    protected fun fun2() {}
    private fun fun3() {}

    // 一个模块中的类可以访问
    internal fun fun4() {}
}
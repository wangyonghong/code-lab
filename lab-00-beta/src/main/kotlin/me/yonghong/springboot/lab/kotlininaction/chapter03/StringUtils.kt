package me.yonghong.springboot.lab.kotlininaction.chapter03

// 静态方法的使用方法
class StringUtils {
    // 伴生对象
    companion object {
        fun isEmpty(str: String): Boolean {
            return "" == str
        }
    }
}


// 使用伴生对象的单例模式
class Single private constructor() {
    companion object {
        fun get(): Single {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = Single()
    }
}

object StringUtils2 {
    @JvmStatic
    fun isEmpty2(str: String): Boolean {
        return "" == str
    }
}

/**
 * Java 中如何调用
 * [StaticFunTest]
 */
fun main(args: Array<String>) {
    StringUtils.isEmpty("")
    StringUtils2.isEmpty2("")

    // 获取单例对象
    Single.get()
}
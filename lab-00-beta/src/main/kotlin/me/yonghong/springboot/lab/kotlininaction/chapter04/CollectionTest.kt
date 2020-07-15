package me.yonghong.springboot.lab.kotlininaction.chapter04

// 集合拓展方法
fun main(args: Array<String>) {
    val list = arrayListOf<Char>('a', 'b', 'c', 'd')
    val a = list.map { it - 'a' }
            .filter { it > 0 }
            .find { it > 1 }

    val b = list.map { it - 'a' }
            .filter { it > 0 }
            .findLast { it > 1 }

    println("$a $b")

    /**
     * [RxJavaTest]
     */
    val abc = arrayOf("4", "0", "7", "i", "f", "w", "0", "9")
    val index = arrayOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)
    index.filter { it < abc.size }
            .map { abc[it] }
            .reduce { s1, s2 -> "$s1$s2" }
            .also { println("密码是$it") }

    myOperation()
}

fun myOperation() {
    val list: List<Int> = listOf(1, 2, 3, 4, 5)
    list.convert {
        it + 1
    }.forEach {
        print("$it ")
    }
}

inline fun <T, E> Iterable<T>.convert(action: (T) -> E): Iterable<E> {
    val list: MutableList<E> = mutableListOf()
    for (item in this) list.add(action(item))
    return list
}
package me.yonghong.learnkt

/*
 * Interoperability Example
 */
object MyKotlinFile {

    @JvmStatic
    fun addNumbers(a: Int, b: Int): Int {
        return a + b
    }
}

fun main(args: Array<String>) {
    var area = MyJavaFile.getArea(10, 5)
    println("Printing area from Kotlin file: $area")
}



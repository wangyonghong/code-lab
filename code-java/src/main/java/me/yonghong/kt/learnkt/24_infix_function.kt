/*
*   INFIX FUNCTIONS
* 中缀函数
* */
fun main(args: Array<String>) {

    val x: Int = 6
    val y: Int = 10

    val greaterVal = x findGreaterValue y   // x.findGreaterValue(y)

    println(greaterVal)

    println(3 plus 5)

    println("12334" beginsWith "123")
    println("12e334" beginsWith "123")
}

infix fun Int.findGreaterValue(other: Int): Int {       // INFIX and Extension Func

    if (this > other)
        return this
    else
        return other
}

infix fun Int.plus(other: Int): Int {
    return this + other
}

infix fun String.beginsWith(prefix: String) = startsWith(prefix)

/*
*       1. All INFIX Functions are Extension functions
*            But all Extension functions are not INFIX
*       2. INFIX Functions just have ONE PARAMETER
* */

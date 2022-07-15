import me.yonghong.kt.learnkt.Student

/*
*   Extension Functions: EXAMPLE ONE
* */
fun main(args: Array<String>) {

    var student = Student()
    println("Pass status: " + student.hasPassed(57))

    println("Scholarship Status: " + student.isScholar(57))
}

fun Student.isScholar(marks: Int): Boolean {
    return marks > 95
}
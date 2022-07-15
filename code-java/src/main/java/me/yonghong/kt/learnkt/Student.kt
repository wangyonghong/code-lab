package me.yonghong.kt.learnkt

class Student constructor(var name: String?) {

    constructor() : this(null)

    var id: Int = -1

    init {
        println("Student has got a name as $name and id is $id")
    }

    constructor(n: String, id: Int) : this(n) {
        // The body of the secondary constructor is called after init block
        this.id = id
    }

    fun hasPassed(marks: Int): Boolean {
        return marks > 40
    }
}

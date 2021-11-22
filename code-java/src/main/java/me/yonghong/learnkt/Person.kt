package me.yonghong.learnkt

class Person constructor(var name: String?, var age: Int) {
    constructor(name: String) : this(name, 0)
    constructor() : this(null, 0)

    fun display() {
        print("The name of the person is ${name}")
    }
}
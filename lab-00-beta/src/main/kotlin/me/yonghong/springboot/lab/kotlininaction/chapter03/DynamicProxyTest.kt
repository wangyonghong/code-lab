package me.yonghong.springboot.lab.kotlininaction.chapter03

// 类的动态代理


interface Animal {
    fun bark()
}

class Dog : Animal {
    override fun bark() {
        println("汪汪汪！")
    }
}

class Cat : Animal {
    override fun bark() {
        println("喵喵喵！")
    }
}


// by 关键字
class Zoo(animal: Animal) : Animal by animal {
//    override fun bark() {
//        println("zoo")
//    }
}

fun main(args: Array<String>) {
    Zoo(Dog()).bark()
    Zoo(Cat()).bark()
}
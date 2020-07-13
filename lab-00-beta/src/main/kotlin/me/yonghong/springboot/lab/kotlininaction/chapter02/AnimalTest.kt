package me.yonghong.springboot.lab.kotlininaction.chapter02

open class Animal
class Dog : Animal()

fun Animal.name() = "animal"
fun Dog.name() = "dog"

fun Animal.printName(dog: Dog) {
    println(dog.name())
}

fun Animal.printName(animal: Animal) {
    println(animal.name())
}

fun main(args: Array<String>) {
    println(Animal().name())
    Animal().printName(Dog())
}
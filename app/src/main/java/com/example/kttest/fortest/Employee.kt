package com.example.kttest.fortest

open class Employee(
    name: String, age: Int, salary: Float

) {
}

data class Programmer(var name: String, var age: Int, var salary: Float): Employee(name,age,salary) {
    // code of programmer
}

open class Bird {
    open var color = "黑色"
    open fun fly() {
        println("Bird is flying...")
    }
}
interface Duck {
    fun fly() {
        println("Duck is flying...")
    }
}

class Parrot: Bird(), Duck {
    override var color = "绿色"
    override fun fly() {
        super<Bird>.fly()
        super<Duck>.fly()
        println("Parrot is flying...")

    }
}

fun main(args: Array<String>) {
    val p = Parrot()
    p.fly()
    println(p.color)

    val user = User("zhanglei", 18)
    println(user)

    val programmer = Programmer("zhaolei", 19, 3000f)
    println(programmer)

    for ( x in 1 .. 10 step  2){
        println(x)
    }
}




data class User(var name : String, var age : Int)







package com.example.kttest.fortest

import android.graphics.ColorSpace

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
    val copy = user.copy(name = "lisi")
    println(copy)

    val programmer = Programmer("zhaolei", 19, 3000f)
    println(programmer)

    for ( x in 1 .. 10 step  2){
        println(x)
    }

    println("red is ${Color.RED.rgb}")

//    var a : String? = "hello"
    var a : String? = null
    var b = a?.length ?: 100
    println("b is $b")

}




data class User(var name : String, var age : Int)

enum class NetState{
    CONNECTED,CONNECTING,DISCONNECT
}

enum class Color(val rgb: Int){
    RED(0xFF000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}






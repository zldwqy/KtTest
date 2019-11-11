package com.example.kttest

class Product{


    constructor(id: Int,price: Float,name: String) {
        this.id = id
        this.name = name
        this.price = price
    }
    var id : Int = 0
    var price : Float = 0.0f
    var name : String = ""
    var num = 0
    override fun toString(): String {
        return "Product(id=$id, price=$price, name='$name', num=$num)"
    }


}
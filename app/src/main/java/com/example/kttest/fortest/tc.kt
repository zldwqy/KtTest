package com.example.kttest.fortest


fun main(){
    val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")

    val mutableMap = mutableMapOf<String, String>()
    map.forEach{
//        key,value -> println("$key to $value")
//        a,b -> println("$a to $b")
    }
    mutableMap.put("a","b")
    mutableMap.put("c","d")
    mutableMap.put("e","f")

//    mutableMap.forEach{
//        println("${it.key} to ${it.value}")
//    }

    val hashMap = HashMap<String,String>()

    hashMap.put("a","b")
    hashMap.put("c","d")
    hashMap.put("e","f")

    hashMap.forEach {
        println("${it.key} to ${it.value}")
    }




}
package com.example.kttest.fortest


    fun main (args: Array<String>) {
        val a = 30
        println("the value is $a")
        var num = 4
        val b = if (num > 0 && num <5){
            5
        }else if (num >5 && num < 10 ){
            10
        }else{
            20
        }

        println("the second value is $b")

        val d = when(num){
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            else -> "other"
        }
        println("the d value is $d")


        when(num){
            1 -> println("one")
            2 -> println("two")
            3 -> println("three")
            4 -> println("four")
            else -> println("other")
        }

        when(num){
            1,2,3,4->{
                println("this is first msg")
                print("hhhe")
            }
            5,6,7,8->{
                println("this is second msg")
                print("hhhe")
            }
            9,10,11,12->{
                println("this is third msg")
                print("hhhe")
            }
        }

        println()
        for (i in 10 downTo 1){
            println("i $i")
        }


        val marks = arrayOf(3, 4, 5, 6, 7)
        for (mark in marks.indices) {
            println("mark[$mark]"+ marks[mark])
        }


        loop@ for (i in 1..3) {
            for (j in 1..3) {
                println("i = $i and j = $j")
                if (i == 2)
                    break@loop
            }
        }

        println()
       name@ for (i in 1..3) {
            for (j in 1..3) {
                println("i = $i and j = $j")
                if (i == 2) {
                    continue@name
                }
                println("this is below if")
            }
        }

        var result = recursiveSum(10000)
        println(result)  //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/kotlin/kotlin-recursion-function.html#article-start

        sumbb(b = "ccc")


        val text = buildString { "abcd" }
        println("$text is String ,it's length is ${text.length}")

        val textLine = """
            this is a long text 
            multi line
            hhhhhh
        """.trimIndent()

        println("print multi line String $textLine")

        val text2 = buildString{"abcd"}

        println("text1 and text2 is equal ${text == text2}")
        println("text1 and text2 is 引用 equal ${text === text2}")

        println(" text1 ${text.hashCode()} text2 ${text2.hashCode()}")

        val str : String? = null
        val str1 : String? = "云烟成雨"
        var len : Int = str?.length ?: -1
        var len1 : Int = str1?.length ?: -1
        println("str length $len  str1 length $len1")

        var len2 : Int = str?.length ?: return

        val listOf = listOf(1, 2, 3, "aliyun", "tencent")

        val mutableListOf = mutableListOf(1, 3, 5, "ali", "tencent")
        mutableListOf.add("hujun")

    }
//fun recursiveSum(n: Long): Long {
//    return if (n <= 1) {
//        n
//    } else {
//        n + recursiveSum(n - 1)
//    }
//}

tailrec fun recursiveSum(n: Long, semiresult: Long = 0) : Long {
    return if (n <= 0) {
        semiresult
    } else {
        recursiveSum((n - 1), n + semiresult)
    }

}


fun sumbb (a : Int = 5,b : String = "===") : Int{
    println("a == $a b = $b")
    var c = a ?: return -1

    return c
}




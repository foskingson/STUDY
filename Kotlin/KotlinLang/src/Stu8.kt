fun max(a: Int, b: Int): Int{
    return if (a>b){
        a
    }else{
        b
    }
}

fun min(a: Int, b: Int): Int=
    if(a<b){
        a
    }else{
        b
    }

fun  repeat(
    str: String,
    num: Int=3,
    useNewLine: Boolean = true
){
    for (i in 1..num){
        if (useNewLine){
            println(str)
        }else{
            println(str)
        }
    }
}

fun printName(name: String, gender: String){
    println(name)
    println(gender)
}

fun printAll(vararg strings: String){
    for (str in strings){
        println(str)
    }
}
fun main() {
    val arr = arrayOf("A","B","C")
    printAll(*arr)
}
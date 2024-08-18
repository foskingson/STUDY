fun main() {
//    val person = Person("공부중")
//    startsWithA(person.name)
}

fun startsWithA(str: String): Boolean{
    return str.startsWith("A")
}
fun startsWithA1(str: String?): Boolean{
    return str?.startsWith("A") ?: throw IllegalArgumentException("null이 들어왔습니다.")  // safe call과 elvis 연산자 활용
}


fun startsWithA2(str: String?): Boolean?{
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean{
    return str?.startsWith("A") ?: false
}

fun startsWith(str: String?):Boolean{
    return str!!.startsWith("A")
}
data class Person3(
    val name: String,
    val age: Int
)

fun main() {
    val person = Person3("ㅇㅇㅇ",20)
    val (name,age) = person

    abc@ for (i in 1..100){
        for (j in 1..100){
            if (j==2){
                break@abc
            }
        }
    }
}
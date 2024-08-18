fun main() {
    println("""
        ABC
        123
        1231
        44444
    """.trimIndent())
}

fun printAgeIfPerson(obj: Any?){
    val person: Person? = obj as? Person
    println(person?.name)
}
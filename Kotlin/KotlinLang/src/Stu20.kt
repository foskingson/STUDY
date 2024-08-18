fun main() {

}

fun printPerson(person: Person){
    person?.let {   // let은 확장함수로 람다를 받아 람다 결과를 반환한다. scope function의 한 종류
        println(it.name)
        println(it.age)
    }
}
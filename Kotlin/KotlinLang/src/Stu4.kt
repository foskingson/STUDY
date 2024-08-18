import javax.print.attribute.standard.MediaSize.Other

fun main() {
    val money1 = Stu4_2(2000L)
    val money2 = Stu4_2(1000L)
    val money3 = Stu4_2(2000L)

    if (money1 > money2){
        println("money1이 더 금액이 큽니다.")
    }

    //println(money1===money3)
    println(money1+money2)
}

//data class Money(
//    val amount: Long
//){
//    operator fun plus(other: Money): Money {
//        return Money(this.amount+other.amount)
//    }
//}

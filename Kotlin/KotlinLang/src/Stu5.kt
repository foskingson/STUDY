fun validateScore(score: Int){
    if(score<0){
        throw IllegalArgumentException("${score}는 0보다 작을 수 없다.")
    }
}


fun getPassOrFail(score: Int): String{
    if (score>=50){
        return "P"
    }else{
        return "F"
    }
}

fun getGrage(score: Int): String{
    return when(score/10){
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun main() {
    val num: Int? = -3
    if (num in -1..1) {
        println("어디서 많이 본 숫자")
    }else {
        println("1,0,-1이 아니다.")
    }
}

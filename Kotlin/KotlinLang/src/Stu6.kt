fun main() {
    val nums = listOf(1,2,3)
    for (num in nums){
        println(num)
    }

    for (i in 1..3){
        print("${i} ")
    }

    println()

    for (i in 3 downTo 1){  // 3부터 1까지 내려간다는 의미
        print("${i} ")
    }
    println()

    for (i in 1..5 step 2){
        print("${i} ")
    }
}
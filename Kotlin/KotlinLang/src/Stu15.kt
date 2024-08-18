fun main() {
//    var array = arrayOf(100, 200)
//    for (a in array.indices) {
//        println("${a} : ${array[a]}")
//    }
//
//    println()
//    array = array.plus(300)
//
//    for ((idx,value) in array.withIndex()) {
//        println("${idx} : ${value}")
//    }

    val nums = listOf(100,200)  // 불변리스트
    val emptyList = emptyList<Int>()

    val nums2 = mutableListOf(100,200)
    nums2.add(300)

    val oldMap = mutableMapOf<Int,String>()
    oldMap[1]="Monday"
    oldMap[2]="Tuesday"

    val map = mapOf(1 to "Monday", 2 to "Tuesday")

    for(key in oldMap.keys){
        println(key)
        println(oldMap[key])
    }

    for ((key,value) in oldMap.entries){
        println(key)
        println(value)
    }
}
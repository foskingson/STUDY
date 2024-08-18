import javax.sql.rowset.Predicate

fun main() {
    val fruits= listOf(
        Fruit("사과",1000),
        Fruit("사과",1200),
        Fruit("사과",1300),
        Fruit("사과",1500),
        Fruit("바나나",2000),
        Fruit("바나나",3000),
        Fruit("수박",4000)
    )

    // 람다를 만드는 방법1
    val isApple = fun(fruit: Fruit): Boolean{
        return fruit.name=="사과"
    }

    // 람다를 만드는 방법2
    val isApple2 = { fruit: Fruit -> fruit.name == "사과" }

    // 람다 호출
    isApple2(fruits[0])

    filterFruits(fruits,isApple)
}

class Fruit(val name: String, val price: Int){

}

private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean): List<Fruit>{
    val results = mutableListOf<Fruit>()
    for(fruit in fruits){
        if (filter(fruit)){
            results.add(fruit)
        }
    }
    return results
}
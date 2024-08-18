data class PersonDto(val name: String,val age: Int) { // 앞에 data class를 붙여주면 알아서 equals, hashCode, toString을 만들어줌

}

enum class Country(private val code: String){
    KOREA("KO"), AMERICA("US");
}

fun handleCountry(country: Country) {
    when(country){
        Country.KOREA -> TODO()
        Country.AMERICA -> TODO()
    }
}
sealed class Car(val name: String,val price: Long)

class Avante : Car("아반떼",1000L)
class Sonata : Car("소나타",2000L)
class Grandeur : Car("그렌저",3000L)

fun handleCar(car: Car){
    when(car){
        is Avante -> TODO()
        is Grandeur -> TODO()
        is Sonata -> TODO()
    }
}
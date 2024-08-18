open class Base(open val num: Int = 100){
    init{
        println("Base")
        println(num)    // 하위 클래스의 오버라이드하고 있는 프로퍼티에 접근하면 안됨
    }
}

class Derived(override val num: Int) : Base(num) {
    init {
        println("Derived")
    }
}

fun main(){
    val der = Derived(300)
    println(der.num)
}



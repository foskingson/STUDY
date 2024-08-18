

class Person2 private constructor(var name: String, var age: Int){
    companion object{
        const val MIN_AGE=1 // const를 붙이지 않으면 런타임 시에 변수가 할당되지만 붙여주면 컴파일 시에 변수가 할당된다.
        fun newBaby(name: String): Person2{
            return Person2(name, MIN_AGE)
        }
    }
}

object Singleton{
    var a: Int = 0
}


interface Movable{
    fun move()
    fun fly()
}

private fun moveSomething(movable: Movable){
    movable.move()
    movable.fly()
}

fun main() {
    moveSomething(object : Movable {
        override fun move() {
            println("move")
        }

        override fun fly() {
            println("fly")
        }
    })
}
abstract class Animal(protected val species: String,protected open val legCount: Int){
    abstract fun move()
}

// 상속받을땐 :를 한칸 띄고 사용
class Cat(species: String) : Animal(species,4){
    override fun move() {
        println("고양이가 걸어가")
    }
}

class Penguin(species: String) : Animal(species,2){
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 걸어가")
    }

    override val legCount: Int  // 이런식으로 프로퍼티를 상속받아서 사용하려면 위에 나온 것처럼 open이라는 키워드를 붙여놔야 한다.
        get() = super.legCount + this.wingCount
}


fun main() {
    val ani: Animal = Cat("고양")
    ani.move()
}


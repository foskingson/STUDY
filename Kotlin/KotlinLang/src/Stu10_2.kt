interface Swimable{
    fun act(){
        println("어푸 어푸")
    }
}

interface Flyable{
    fun act(){
        println("파닥 파닥")
    }

    fun fly()   // 추상 메소드
}

// 인터페이스를 상속받을 땐 다음과 같이 간단하게 가능
class Penguin2(species: String) : Animal(species,2), Swimable, Flyable{
    val wingCount = 2

    override val legCount: Int
        get() = super.legCount + wingCount

    override fun move() {
        println("펭귄이 움직인다.")
    }

    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }

    override fun fly() {
    }
}

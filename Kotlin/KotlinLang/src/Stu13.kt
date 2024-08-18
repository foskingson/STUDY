class House(private val address: String, private val livingRoom: LivingRoom){
    class LivingRoom(private val area: Double, private val house: House){
        val address: String
            get() = house.address
    }
}
class House2(private val address: String, private val livingRoom: LivingRoom){
    inner class LivingRoom(private val area: Double){
        val address: String
            get()=this@House2.address
    }
}
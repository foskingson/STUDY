public class Person(name: String,var age: Int){    // 코틀린은 왼쪽과 같이 간단하게 생성자를 만들 수 있음
    // 코틀린에서는 필드만 만들면 getter,setter는 자동 생성

    var name = name
//        get() = field[0].uppercase()+ field.substring(1)   // field는 name대신 작성하여 자기자신을 가르킬때 생기는 무한루프를 방지해준다.
        set(value){
            field=value.uppercase()
        }

    init {  // 클래스가 초기화되는 시점 한번 호출되는 블록
        if (age<=0){
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }
    constructor(name: String): this(name,1){
        println("첫번 째 부 생성자")
    }

    val isAdult: Boolean
        get(){
            return this.age>=20
        }
    
}

fun main(){     // 다음과 같이 . 필드를 통해 getter와 setter를 바로 호출
    val person = Person("jo min")
    person.name="name"
    println(person.name)
    person.age = 30
    println(person.age)
    println(person.isAdult)
}
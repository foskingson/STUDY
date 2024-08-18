# 코틀린 문법
> 코틀린(kotlin)은 IntelliJ, PyCharm 등 다양한 IDE를 선보인 것으로 유명한 JetBrains 사에서 오픈소스 그룹을 만들어 개발한 프로그래밍 언어이다. 코틀린은 JVM 기반 언어라는 특성을 가지고 있어 Java를 대체하여 사용할 수 있다.

- 특징
```
1. 표현력과 간결함(expressive and concise): 매우 간결한 문법을 제공한다.

2. 안전한 코드(safer code): 코틀린에서는 변수 선언시 널 허용과 불허용을 구분해서 선언할 수 있다.

3. 상호 운용성(interoperable): Java와 100% 호환된다.

4. 구조화 동시성(structed concurrency): 코루틴(coroutines) 기법을 이용하면 비동기 프로그래밍을 간소화할 수 있다.
```


#### 참고로 인텔리제이에서 `도구 => 코틀린 => 코틀린 바이트 코드 표시 => 디컴파일` 을 통해 자바코드로 바꿔서 확인할 수 도 있다.

<br>
<br>

## 변수
> 기본적으로 코틀린의 변수는 `val`과 `var`를 통해 선언된다. 


- val : 불변성 변수, 즉 상수이다. (Java에서는 `final`으로 선언한다.)
    - 하지만 자바와 마찬가지로 컬렉션 상수일 경우 원소의 추가 혹은 삭제가 가능하다.

- var : 가변성 변수, 즉 도중에 변할 수 있는 값이다.


<br>

### 변수 다루는 법
```
- 모든 변수는 우선 val로 만들고 꼭 필요한 경우에만 var로 변경하는 것이 좋다.

- 모든 변수는 var / val을 붙여 주어야 한다.

- var : 변경 가능하다 / val : 변경 불가능하다 (read-only)

- 타입을 명시적으로 작성하지 않아도, 타입이 추론된다.

- Primitive Type과 Reference Type을 구분하지 않아도 된다.

- Null이 들어갈 수 있는 변수는 타입 뒤에 ? 를 붙여주어야 한다. ` var num: Long? = null `

- 아예 다른 타입으로 간주된다.

- 객체를 인스턴스화 할 때 new를 붙이지 않아야 한다 ` var person = Person("ㅇㅇㅇ") `
```

<br>
<br>

## NULL
> 변수에 null이 담겨있을 경우 프로그램이 실행될 때 널포인트 예외(NullPointerException)가 터질 수 있기 때문에 따로 처리해줄 필요가 있다.

- `null`이 들어갈 수 있는 변수에는 반드시 ?를 붙여준다.


### Safe Call ?.
> null이 아니면 실행하고 null이면 실행하지 않는다.

``` kotlin
val str: String?="ABC"
println(str?.length)    // ?. 를 통해 safe call
```

### Elvis 연산자 ?:
> 앞의 연산 결과가 null이면 뒤의 값을 사용

``` kotlin
val str: String?="ABC"
str?.length ?: 0        // str?.length의 결과가 null일 경우 ?: 뒤의 0을 사용
```

### 아무리 생각해도 null이 될 수 없는 경우
> !!를 통해 명시해준다.

``` kotlin
return str!!.startsWith("A")
```


### 플랫폼 타입
> 자바에서 `@Nullable` 혹은 `@NotNull`와 같은 애노테이션을 통해 코틀린에서 null 관련 정보를 가져올 수 있다.

- 만약에 @Nullable을 붙인다면 코틀린에서는 ?.를 사용해야 하고 @NotNull일경우는 그냥 .를 사용해서 객체를 사용하면 된다.


<br>
<br>

## 타입
> 기본적인 Int와 Double같은 것들은 그대로 코틀린에도 있고 코틀린만의 특이한 타입들이 존재한다.

- 기본적으로 코틀린은 선언된 기본값을 보고 타입을 추론한다.
- 자바와 다르게 기본 타입간의 변환은 명시적으로 이루어져야 한다.
    - 자바는 더 큰 타입에 작은 타입의 변수를 담을 수 있지만 코틀린은 불가능하다.
    - 따라서 아래와 같이 `to변환타입()`을 잘 활용해야 한다.
    ``` kotlin
    val num1 =3
    val num2: Long = num1.toLong()
    ```

### 타입 캐스팅
1. 명시적 캐스팅 (as 연산자)
    - as 연산자를 사용하여 객체를 명시적으로 다른 타입으로 캐스팅할 수 있다.
    - 만약 캐스팅이 불가능하면 ClassCastException이 발생한다
    ``` kotlin
    val obj: Any = "Hello, Kotlin!"
    val str: String = obj as String // 명시적 캐스팅
    ```

2. 자동 캐스팅
    - Kotlin에서는 is 연산자를 사용하여 타입 검사를 수행하면, 조건문 블록 내에서 자동으로 타입이 캐스팅된다.
    - 이로 인해 obj is Person 블록 내에서는 obj가 Person 타입으로 자동으로 캐스팅되어, 별도의 as 연산자를 사용하지 않고도 Person 클래스의 멤버에 접근할 수 있다.
    - `!is`를 통해 반대 경우도 처리 가능하다.
    ``` kotlin
    if (obj is Person) {    // obj !is Person 을 통해 아닌 경우도 가능
    println(obj.name) // obj는 자동으로 Person 타입으로 캐스팅됨
    }
    ```

3. 주의사항
    - 타입캐스팅을 할때 null인 경우를 잘 생각해서 사용해야 한다.
    - as로 사용할 경우
        - value가 Type이면 Type으로 타입캐스팅
        - 아니라면 예외 발생
    - as? 를 사용할 경우
        - value가 Type이면 Type으로 타입캐스팅
        - value가 null이면 null 반환
        - value가 Type이 아니라면 null 반환

    ```kotlin
    fun printAgeIfPerson(obj: Any?){    // null이 들어올 경우를 대비
        val person: Person? = obj as? Person
        println(person?.name)
    }   
    ```

### 코틀린의 특이한 타입 3가지

1. Any
    - Java의 Object와 같은 역할 (모든 객체의 최상위 타입)
    - Any 자체로는 null을 포함할 수 없어 null을 포함하려면 Any?를 사용
    - Any에 equals / hashCode / toString 존재

2. Unit
    - Java의 void와 동일한 역할
    - 함수형 프로그래밍에서 Unit은 단 하나의 인스턴스만 갖는 타입을 의미, 즉 코틀린의 Unit은 실제 존재하는 타입이라는 것을 표현
    - void와 다르게 Unit은 그 자체로 타입 인자 사용이 가능하다. void는 단지 반환타입이 없다는 키워드이다.
    ``` kotlin
    // Unit을 반환하는 함수
    fun printMessage(): Unit {
        println("Hello, World!")
        // 명시적으로 Unit을 반환하지 않아도, 이 함수는 Unit을 반환
    }

    fun main() {
        val result: Unit = printMessage()
        println(result) // 결과는 kotlin.Unit
    }
    ```

3. Nothing
    - Nothing은 함수가 정상적으로 끝나지 않았다는 사실을 표현하는 역할
    - 무조건 예외를 반환하는 함수나 무한 루프 함수 등에서 사용
    ``` kotlin
    fun fail(message: String): Nothing{
        throw IllegalArgumentException(message)
    }
    ```

### String interpolation / String indexing
> 코틀린에서 문자열을 다룰때 `${변수}`를 사용하면 값이 들어간다. 또한 여러 줄에 걸친 문자열을 작성할때 `""" """`를 사용하면 쉽게 사용할 수 있다.

``` kotlin
val person = Person("ooo",100)
val log= "사람의 이름은 ${person.name}이고 나이는 ${person.age}세 입니다."

val temp="""
    ABC
    123
    456
    """.trimIndent()    // trimIndent()를 통해 각줄의 왼쪽 공백을 제거 
```


<br>
<br>

## 연산자
> 대부분 자바와 유사하다.

1. 자바와 다르게 객체를 비교할때 비교 연산자를 사용하면 자동으로 compareTo를 호출해준다.
``` kotlin
val money1 = Stu4_2(2000L)
val money2 = Stu4_2(1000L)

if (money1 > money2){
    println("money1이 더 금액이 큽니다.")
}

/* 자바의 경우 compareTo() 메서드를 직접 호출해줘야 한다.
if(money1.compareTo(money2) > 0){
    println("money1이 더 금액이 큽니다.")
}
*/
```


### 동등성 / 동일성

1. 동등성 : 두 객체의 값이 같은가?
    - 자바에서 `==`를 사용
    - 코틀린에서도 `==`를 사용

2. 동일성 : 두 객체가 동일한 객체인가? 즉 주소가 같은가?
    - 자바에서 `equals()`를 사용
    - 코틀린에서는 `===`를 사용

### 코틀린에 있는 특이한 연산자

1. in / !in
    - 컬렉션이나 범위에 포함되어 있다. / 포함되어 있지 않다.
    - `println(1 in numbers)`

2. a..b 
    - a부터 b까지의 범위 객체를 생성한다.


### 연산자 오버로딩
> 코틀린에서는 객체마다 연산자를 직접 정의할 수 있다.

``` kotlin
data class Money(val amount: Long) {
    // '+' 연산자 오버로딩
    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }
}

fun main() {
    val money1 = Money(2000L)
    val money2 = Money(1000L)
    
    // '+' 연산자 사용, 내부적으로 plus 메서드 호출
    println(money1 + money2)  // 결과: Money(amount=3000)
}


/* 자바의 경우
public class Money {
    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    // 메서드 정의
    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    @Override
    public String toString() {
        return "Money(amount=" + amount + ")";
    }

    public static void main(String[] args) {
        Money money1 = new Money(2000L);
        Money money2 = new Money(1000L);
        
        // 메서드 호출로 더하기 수행
        System.out.println(money1.plus(money2));  // 결과: Money(amount=3000)
    }
}
*/
```

<br>
<br>

## 조건문
> 일반적인 if문은 자바와 차이가 없다.

### Expression / Statement
> 자바에서 if/else는 Statement 이지만, 코틀린에서는 Expression이다. 

``` kotlin
// 따라서 코틀린에서는 하나의 값으로 취급하여 한번에 리턴하거나 대입해줄수 있다.
return if (score>0){
    "P"
}else{
    "F"
}
```
> 따라서 자바의 3항연산자가 코틀린에 없다.

1. Statement : 프로그램의 문장, 하나의 값으로 도출되지 않는다.
    - Statement에 Expression가 포함되는 개념이다.
2. Expression : 하나의 값으로 도출되는 문장

### 간단한 TIP

- 어떠한 값이 특정 범위에서 포함되있는지 확인할 때 다음과 같이 더 편리하게 작성할 수 있다.
    - `if(0 <= score && score <= 100)` => `if(score in 0..100)`

### switch와 when
> 자바에서 switch로 쓰던 것을 when으로 대체하여 사용할 수 있다.

- when 같은 경우 Enum Class 혹은 Sealed Class와 사용할 경우 더 진가를 발휘한다. 뒤쪽 기타 클래스 부분 확인

``` kotlin
return when(score/10){
    in 90..99 -> "A"    // 특정 값만 case를 치는 것이 아닌 범위로 지정해줄 수 있다.
    is String -> "?"    // 타입을 확인해주는 것도 가능
    8 -> "B"
    7 -> "C"
    else -> "D"
}
```

<br>
<br>

## 반복문
> 반복문도 대부분 자바와 비슷하지만 살짝씩 다른 부분들이 있다.

1. for-each
``` kotlin
val nums = listOf(1,2,3)
for (num in nums){
    println(num)
}
```

2. for문
``` kotlin
for (i in 1..3){
    print("${i} ")
}

for (i in 3 downTo 1){  // 3부터 1까지 내려간다는 의미
    print("${i} ")
}

for (i in 1..5 step 2){ // 2씩 올라갈 경우
    print("${i} ")
}
```

3. while문
    - while문은 사실상 자바와 동일하고 i를 늘리면서 조건까지 동작시킬 경우 i를 변수인 var로 작성해야 한다는 것만 주의하자.

<br>
<br>

## 예외 처리


1. try-catch-finally
```kotlin
fun parseIntOrThrow(str: String): Int{
    try {
        return str.toInt()
    }catch (e: NumberFormatException){
        throw  IllegalArgumentException("숫자가 아님")
    }
}
```

2. Checked Exception과 Unchecked Exception
    - 자바와 다르게 코틀린은 Checked Exception에서 throws를 통해 메서드에 예외를 명시하지 않아도 된다.
```
1. 컴파일 오류 : 컴파일 오류는 코드를 컴파일할 때 발생하는 오류이다. 주로 문법 오류나 타입 오류와 관련이 있다. 이러한 오류는 소스 코드를 실행하기 전에 발견되며, 컴파일러가 오류 메시지를 통해 발견된 문제를 알려준다. => 체크 예외
    - 체크 예외는 예외를 잡아서 처리(try/catch)하거나 던지거나(throws) 둘중 하나를 필수로 해야한다.
    - 기본적으로 언체크 예외를 사용하되 체크 예외는 비즈니스 로직상 의도적으로 던지는 예외에만 사용한다. 예를 들면 계좌 이체 실패 예외, 결제시 포인트 부족 예외, 로그인 ID, PW 불일치 예외가 있다.

2. 런타임 오류 : 런타임 오류는 프로그램이 실행되는 동안 발생하는 오류이다. 이는 주로 프로그램이 실행 중에 예기치 않은 조건에 직면할 때 발생한다. 예를 들어, 0으로 나누기, 배열 범위를 벗어난 접근, null 포인터 참조 등이 있다. 이러한 오류는 컴파일러가 감지하지 못하는 경우가 많으며, 프로그램 실행 중에 발생한다. 이러한 오류는 프로그램 실행 중에 예외(Exception)로 발생하며, 이에 대한 적절한 예외 처리 로직이 필요하다. => 언체크 예외
    - 언체크 예외는 예외를 던지는 throws를 생략할 수 있다. 또한 예외를 잡지 않아도 된다. 이 경우 자동으로 예외를 던진다. 

=> 요즘은 대부분 런타임 예외를 사용하는 추세이다. 
```

3. try with resources
    - 자바에서는 try with resources 기능을 사용해 자원의 사용이 끝나면 알아서 닫아줄 수 있다.
    - 코틀린에서는 use를 사용해 비슷한 기능으로 사용할 수 있다.
```kotlin
fun readFile(path: String){
    BufferedReader(FileReader(path)).use{
        reader -> println(reader.readLine())
    }
}
```

<br>
<br>

## 함수
- 기본적인 함수 선언
```kotlin
fun max(a: Int, b: Int): Int{
    return if (a>b){
        a
    }else{
        b
    }
}

fun min(a: Int, b: Int): Int = if(a<b) a else b  // 함수가 하나의 결과 값이라면 {} block 대신 = 사용 가능
```

<br>

### default parameter
    - 자바에서는 디폴트 파라미터라는 값이 없어 오버로딩으로 불편하게 메서드를 여러개 만들어야하지만 코틀린은 그럴 필요가 없다.
``` kotlin
fun  repeat(
    str: String,
    num: Int=3, // 다음과 같이 매개변수를 안 넘겼을때 기본값을 지정해줄 수 있다.
    useNewLine: Boolean = true
){
    for (i in 1..num){
        if (useNewLine){
            println(str)
        }else{
            println(str)
        }
    }
}
```

<br>

### named argument
> 빌더를 만들지 않고 빌더의 장점을 갖게 된다. 

- 주의할 점으로 코틀린에서 자바 함수를 가져다 사용할 때에는 named argument 기능을 사용할 수 없다.

``` kotlin
printName(name="ddd", gender = "male")  // 호출하는 쪽에서 매개변수의 이름을 작성하면서 더 명확한 확인이 가능
```

<br>

### 가변인자
> 자바에서는 `String...` 와 같이 가변인자를 표현해줄 수 있지만 코틀린에서는 `vararg`를 사용해 가변인자를 표현해 줄 수 있다.
``` kotlin
fun printAll(vararg strings: String){
    for (str in strings){
        println(str)
    }
}
fun main() {
    val arr = arrayOf("A","B","C")
    printAll(*arr)  // 코틀린에서 가변인자에 매개변수를 주기 위해서는 반드시 앞에 *를 붙여줘야 한다.
}
```
<br>
<br>


## 클래스

### 생성자 및 getter/setter
> 코틀린은 val,var를 통해 필드를 생성하면 기본적으로 getter/setter는 자동으로 생성되고 생성자는 다음과 같이 만들어줄 수 있다.
``` kotlin
public class Person(val name: String,var age: Int){    // 코틀린은 왼쪽과 같이 간단하게 생성자를 만들 수 있음
    // 코틀린에서는 필드만 만들면 getter,setter는 자동 생성
}

fun main(){     // 다음과 같이 . 필드를 통해 getter와 setter를 바로 호출
    val person = Person("ooo",100)
    println(person.name)
    person.age = 10
}
```

<br>

### 검증 로직
> 코틀린은 `init` 블록을 이용하여 간단하게 검증로직을 만들 수 있다.  `init`은 클래스가 초기화되는 시점에 한번 호출되는 블록이다.
``` kotlin
init {  // 클래스가 초기화되는 시점 한번 호출되는 블록
    if (age<=0){
        throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
    }
}
```

<br>

### 추가적인 두번째 혹은 세번째 생성자 만들기
> `constructor()` 키워드를 아래와 같이 이용해 여러개의 생성자를 만들 수 있다. 다만 이러한 부 생성자를 만들 때는 주 생성자가 반드시 존재해야 한다.

- `하지만 default parameter를 사용하는 것이 부 생성자를 만드는 것 보다 권장되는 방식이다.`

``` kotlin
public class Person(val name: String,var age: Int){    // 코틀린은 왼쪽과 같이 간단하게 생성자를 만들 수 있음
    // 코틀린에서는 필드만 만들면 getter,setter는 자동 생성

    init {  // 클래스가 초기화되는 시점 한번 호출되는 블록
        if (age<=0){
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }
    
    constructor(name: String): this(name,1){
        println("첫번 째 부 생성자")
    }
}
```

<br>

### 커스텀 getter/setter
> 코틀린에서는 함수 대신 프로퍼티처럼 만들 수 있다. 


- 객체의 속성이라면 custom getter, 아니라면 함수를 이용한다.
    - 쉽게 말해 객체안에 필드값을 이용하는거면 custom getter, 그게 아니라 다른곳에서 인자를 받아 사용한다면 함수를 사용한다.
``` kotlin
val isAdult: Boolean
    get(){
        return this.age>=20
    }

val isAdult: Boolean
    get() = return this.age>=20
```

<br>

- 커스텀 getter/setter 사용 예시
    1. getter로 가져올 때 대문자로 바꿔서 가져오게 하기
    ``` kotlin
    val name = name
        get() = field.uppercase()   // field는 name대신 작성하여 자기자신을 가르킬때 생기는 무한루프를 방지해준다.
    ```
    2. setter를 할때 무조건 대문자로 바꿔서 넣어주는 코드
    ``` kotlin
    var name = name
        set(value){
            field=value.uppercase()
        }
    ```



<br>
<br>


## 상속

1. 추상 클래스 
    - 코틀린도 추상클래스를 인스턴스화할 수는 없다.
``` kotlin
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
```

<br>

2. 인터페이스
``` kotlin
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
```

<br>

3. 주의할 점 
    - 기본적으로 클래스는 final이 적용되어 아무도 오버라이드를 할 수 없게 되어 있다.
    - 추상 클래스나 인터페이스가 아닌 `클래스와 클래스` 끼리의 상속일 경우 `open`으로 클래스를 열어놔야 상속이 가능하다.
    - 상위 클래스를 설계할 때 생성자 또는 초기화 블록에 사용되는 프로퍼티에는 open을 피해야 한다.
        - 하위 클래스의 값이 초기화 되기전에 상위 클래스에서 값이 사용되려 할 수 있기 때문이다.

``` kotlin
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



```

<br>
<br>


## 접근 제어

1. 자바와 코틀린의 가시성 제어 
    - 자바 : publc(모든 곳), protected(같은 패키지 혹은 하위 클래스), default(같은 패키지), private(선언된 클래스)
    - 코틀린 : public(모든 곳), protected(선언된 클래스 혹은 하위 클래스), internal(같은 모듈), private(선언된 클래스)
        - 모듈 : 한번에 컴파일 되는 코틀린 코드

2. 주의할 점
    - 생성자에 접근제어를 붙이려면 constructor를 써줘야 한다.
    - 코틀린에서 A.kt 라는 파일안에 a라는 함수를 만들었다면 자바에서 Akt.a() 라고 해서 사용할 수 있다.
    - kotlin의 internal를 자바에서 사용할 땐 바이트 코드 상 public이 된다.
    - 자바는 같은 패키지의 코틀린 protected 멤버에 접근할 수 있다.

3. 프로퍼티에 접근제어를 달 때
``` kotlin
internal val name: String   // getter/setter에 모두 접근제어를 부여

var price = 10
    private set // setter에만 접근제어 부여
```

<br>
<br>

## object 키워드
> 자바의 `static`을 코틀린의 `companion object` 키워드를 이용해서 비슷한 형태를 만들 수 있다.

- companion object 또한 인스턴스가 여러개이더라도 값이 공유된다.
- `const`는 변수가 컴파일 시에 할당되게 해주고 기본타입과 String에 붙여준다. 진짜 상수에만 적용한다.
- `companion object`도 객체이므로 이름을 붙여줄 수 있다. 또한 인터페이스를 상속받아 구현할 수도 있다.
- 자바에서 아래 함수를 이용할 땐 `Person2.Companion.newBaby("ABC");` 와 같이 사용할 수 있다.
    - 만약 코틀린 쪽에 `@JvmStatic`을 적어놓으면 `Companion`없이 접근가능하다.
```kotlin
class Person2 private constructor(var name: String, var age: Int){
    companion object : Log{   // 결국 companion object도 객체이므로 이름을 붙여줄 수 있다. 안 붙여줘도 동작함
        const val MIN_AGE=1 // const를 붙이지 않으면 런타임 시에 변수가 할당되지만 붙여주면 컴파일 시에 변수가 할당된다.

        @JvmStatic  // 자바에서는 Person2.newBaby("ABC"); 로 접근할 수 있게 해줌
        fun newBaby(name: String): Person2{
            return Person2(name, MIN_AGE)
        }

        override fun log(){
            println("ㅇㅇ")
        }
    }
}

interface Log{
    fun log()
}
```

<br>

### 싱글톤
> 하나의 인스턴스만을 갖는 클래스

- `object Singleton` 와 같이 간단하게 앞에 `object`만  `class` 대신 붙여주면 싱글톤이 완성된다.
``` kotlin
fun main() {
    println(Singleton.a)    // 자바의 static 처럼 사용
}

object Singleton{
    var a: Int = 0
}
```


<br>

### 익명 클래스
> 클래스를 이름 없이 한번만 사용할 때 쓰는 클래스

``` kotlin
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
```

<br>
<br>

## 중첩 클래스

- 종류
    1. static을 사용한 중첩 클래스
    2. static을 사용하지 않은 중첩 클래스
        - 내부 클래스
        - 지역 클래스
        - 익명 클래스

- 기본적으로 코틀린은 바깥 클래스와 연결이 없는 중첩 클래스를 만들어 메모리 누수를 없게 한다. (권장)
    - 자바의 static을 이용한 중첩 클래스와 동일하다. (권장되는 방식)
``` kotlin
class House(private val address: String, private val livingRoom: LivingRoom){
    class LivingRoom(private val area: Double, private val house: House){
        val address: String
            get() = house.address
    }
}
```

- 만약에 바깥 클래스와 연결이 있는 중첩 클래스를 만들려면 `inner`를 `class` 앞에 붙여서 사용한다. (권장하지 않음)
    - 바깥 클래스를 참조할 때는 `@`를 사용한다.
``` kotlin
class House2(private val address: String, private val livingRoom: LivingRoom){
    inner class LivingRoom(private val area: Double){
        val address: String
            get()=this@House2.address   // 바깥 클래스를 참조할 때는 @를 사용한다.
    }
}
```

<br>
<br>

## 기타 다양한 클래스

1. Data Class : 계층간의 데이터 전달을 위한 DTO(Data Transfer Object)
    - 클래스 앞에 `data`를 붙여주면 알아서 equals, hashCode, toString을 만들어줌
    - 데이터(필드)
    - 생성자와 getter
    - equals, hashCode
    - toString
``` kotlin
data class PersonDto(val name: String,val age: Int) { // 앞에 data를 붙여주면 알아서 equals, hashCode, toString을 만들어줌
    
}
```

<br>

2. Enum Class : 추가적인 클래스를 상속받을 수 없다. 인터페이스는 구현할 수 있으며, 각 코드가 싱글톤이다.
``` kotlin
enum class Country(private val code: String){
    KOREA("KO"), AMERICA("US");
}

fun handleCountry(country: Country) {
    when(country){
        Country.KOREA -> TODO()
        Country.AMERICA -> TODO()
    }
}
```

<br>

3. Sealed Class, Sealed Interface : 상속이 가능하도록 추상클래스를 만들려는데 외부에서는 이 클래스를 상속받지 않았으면 좋겠을 경우 사용한다.
    - 컴파일 타임 때 하위 클래스의 타입을 모두 기억한다. 즉, 런타임 때 클래스 타입이 추가 될 수 없다.
    - 하위 클래스는 같은 패키지에 있어야 한다.
    - 하위 클래스는 멀티 인스턴스가 가능하다.
``` kotlin
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
```

<br>
<br>

## 컬렉션

1. 배열 : 잘 사용하지는 않음, 배열보다 리스트를 사용하는 편이다.
``` kotlin
var array = arrayOf(100, 200)
for (a in array.indices) {
    println("${a} : ${array[a]}")
}

array = array.plus(300)

for ((idx,value) in array.withIndex()) {
    println("${idx} : ${value}")
}
```

<br>

2. 컬렉션 : 컬렉션을 만들어줄 때 불변인지, 가변인지를 먼저 설정해야 한다.
    1. 불변 컬렉션 (Immutable Collection)
        - 불변 컬렉션은 요소를 추가하거나 삭제할 수 없다.
        - Kotlin에서 listOf(), setOf(), mapOf() 등의 함수로 생성된 컬렉션이 불변 컬렉션이다.
        ``` kotlin
        val nums = listOf(100,200)  // 불변리스트
        val emptyList = emptyList<Int>()

        val map = mapOf(1 to "Monday", 2 to "Tuesday")  // 불변 맵

        println(nums[0])
        ```

    2. 가변 컬렉션 (Mutable Collection)
        - 가변 컬렉션은 요소를 추가, 삭제, 수정할 수 있다.
        - Kotlin에서는 mutableListOf(), mutableSetOf(), mutableMapOf() 등을 사용하여 가변 컬렉션을 생성할 수 있다.
        - 예를 들어, val mutableList = mutableListOf(1, 2, 3)는 가변 리스트를 생성하며, 여기에 요소를 추가하거나 삭제할 수 있다.
        ``` kotlin
        val nums2 = mutableListOf(100,200)
        nums2.add(300)

        val oldMap = mutableMapOf<Int,String>()
        oldMap[1]="Monday"
        oldMap[2]="Tuesday"

        for(key in oldMap.keys){
            println(key)
            println(oldMap[key])
        }

        for ((key,value) in oldMap.entries){
            println(key)
            println(value)
        }
        ```

    3. Java의 불변 컬렉션 (Collections.unmodifiableList 등):
        - Kotlin은 Java와 상호 운용이 가능하기 때문에 Java의 Collections.unmodifiableList()와 같은 메서드를 사용할 수 있다.
        - Collections.unmodifiableList()는 Java에서 사용되는 방식으로, 원래 가변 리스트에 대해 불변 보장을 추가한다. 하지만 이 방식은 Kotlin의 불변 컬렉션과는 다르다.
        - Collections.unmodifiableList()로 생성된 리스트는 리스트 자체가 불변이지만, 원래의 리스트가 가변적일 경우 그 리스트의 요소는 변경될 수 있다. 즉, 컬렉션의 내용이 바뀔 수 있다.

<br>

3. 컬렉션의 null 가능성, 자바와 함께 사용하기
    1. `List<Int?>` : 리스트에 null이 들어갈 수 있지만 리스트는 절대 null이 아님
    2. `List<Int>?` : 리스트에 null이 들어갈 수 없지만 리스트는 null일 수 있음
    3. `List<Int?>?` : 리스트에 null이 들어갈 수도 있고 리스트가 null일 수도 있음

- 코틀린의 컬렉션이 자바에서 호출되면 컬렉션의 내용이 변할 수 있음을 감안해야 한다.
    - 자바는 읽기(불변) 전용 컬렉션과 변경(가변) 가능 컬렉션을 구분하지 않는다.
    - 자바는 nullable 타입과 non-nullable 타입을 구분하지 않는다.

- 코틀린에서 자바 컬렉션을 가져다 사용할 때 플랫폼 타입을 신경써야 한다.
    - 자바의 List<Integer>가 코틀린으로 넘어갈 때 List<Int?> 인지 List<Int>? 인지 신경써야 한다.

<br>
<br>


## 코틀린에서 다양한 함수를 다루는 방법

1. 확장함수 : 함수가 마치 클래스 안에 있는 멤버 함수인 것처럼 사용된다. 쉽게 말해 기존 클래스에 새로운 함수를 추가하는 느낌
    - 하지만 실제로 클래스의 멤버 함수로 추가되는 것은 아니다.
    - 멤버함수와 확장함수의 이름이 같다면 멤버함수가 우선적으로 호출된다.
    - 오버라이드된 함수와 확장함수의 이름이 같다면 설정한 타입의 함수로 호출된다.
``` kotlin
fun String.lastChar(): Char{    // String 클래스를 확장함
    return this[this.length-1]  // this를 이용해 실제 클래스 안의 값에 접근
}

fun main() {
    val str = "ABC"
    println(str.lastChar())
}
```
<br>

2. infix 함수(중위 함수) : `변수.함수이름` 대신 `변수 함수이름 argument`를 사용하는 함수이다. 예를들어 downTo, step가 있다.
``` kotlin
infix fun Int.add(other: Int): Int{
    return this + other
}

fun main(){
    3 add 4
}
```
<br>

3. inline 함수 : 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우이다.
    - 함수를 파라미터로 전달할 때에 오버헤드를 줄일 수 있다.
``` kotlin
inline fun Int.add(other: Int): Int{
    return this + other
}
```

<br>

4. 지역함수 : 깊이가 깊어지고 코드가 깔끔하지 않다.

<br>
<br>


## 람다
> 코틀린에서의 람다(Lambda)는 익명 함수(anonymous function)의 일종으로, 이름 없이 즉석에서 정의된 함수라고 볼 수 있다. 람다는 일종의 함수 객체로, 함수 자체를 변수처럼 전달하거나 반환할 수 있게 해준다.

- 람다식의 마지막 인자를 넘길때 `{} 중괄호`를 이용한다.

``` kotlin
// 람다를 만드는 방법1
val isApple = fun(fruit: Fruit): Boolean{
    return fruit.name=="사과"
}

// 람다를 만드는 방법2
val isApple2 = { fruit: Fruit -> fruit.name == "사과" }

// 람다 호출
isApple2(fruits[0])

// 아래 함수 호출
filterFruits(fruits,isApple)

// 이렇게 해도 중괄호에 들어간 것들이 마지막 인자로 함수로 넘어가게 됨
filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과" }

// 위와 동일한 기능 it을 활용
filterFruits(fruits) { it.name == "사과" }

// 활용한 방식
private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean): List<Fruit>{
    val results = mutableListOf<Fruit>()
    for(fruit in fruits){
        if (filter(fruit)){
            results.add(fruit)
        }
    }
    return results
}
```

### Closure
> 자바는 final인 변수만 람다에 사용할 수 있지만 코틀린은 그런 제약이 없다. 코틀린에서는 람다가 시작하는 지점에 참조하는 변수들을 모두 포획하여 그 정보를 가지고 있다. 이러한 데이터 구조를 Closure라고 부른다.

<br>
<br>

## 컬렉션을 함수로 다루는 방법
> 코틀린에도 자바의 스트림과 같은 다양한 컬렉션을 함수로 처리하는 방법이 있다.

1. 필터와 맵
``` kotlin
val apples = fruits.filter { fruit -> fruit.name == "사과"} // 사과만 필요할 때

val apples = fruits.filterIndexed{ idx, fruit ->        // 인덱스도 필요할 때
    println(idx)
    fruit.name=="사과"
}

val applePrices = fruits.filter { fruit -> fruit.name == "사과"}
    .map{ fruit -> fruit.currentPrice } // 사과의 가격들이 필요할 때
```
<br>

2. 다양한 컬렉션 처리 기능
    - all : 조건을 모두 만족하면 true 아니면 false
    - none : 조건을 모두 불만족하면 true 아니면 false
    - any : 조건을 하나라도 만족하면 true 아니면 false
    - count : 개수를 센다.
    - sortedBy : 오름차순 정렬한다.
    - distinctBy : 변형된 값을 기준으로 중복을 제거한다.
    - first : 첫번째 값을 가져온다. 무조건 null이 아니여야 함
    - firstOrNull : 첫번째 값 또는 null을 가져온다.
<br>


3. 리스트를 맵으로는 만들기
``` kotlin
// 과일 이름이 키이고 해당하는 과일들의 리스트가 들어있는 맵
val map: Map<String, List<Fruit>> = fruits.groupBy{ fruit -> fruit.name }

// 과일 이름이 키이고 해당하는 과일의 id가 들어있는 맵
val map: Map<String, Fruit> = fruits.associateBy{ fruit -> fruit.id }

// 과일 이름이 키이고 출고가가 들어가 있는 맵
val map: Map<String,List<Long>> = fruits
    .groupBy({fruit -> fruit.name},{fruit -> fruit.factoryPrice})
```

4. 중첩된 컬렉션 처리
    - `flatMap`을 사용하게 되면 List List가 단일 List로 바뀌게 된다.

<br>

``` kotlin
fun main() {
    val listOfLists = listOf(
        listOf(1, 2, 3),
        listOf(4, 5),
        listOf(6, 7, 8, 9)
    )

    val flattenedList = listOfLists.flatMap { it }

    println(flattenedList)  // [1, 2, 3, 4, 5, 6, 7, 8, 9]
}
```

<br>
<br>

## 기타

1. Type Alias와 as import
    - Type Alias : 긴 이름의 클래스 혹은 함수 타입이 있을 때 축약하거나 더 좋은 이름을 쓸 수 있다.
    - as import : 다른 패키지의 같은 이름 함수를 동시에 가져오고 싶을 때 사용할 수 있다. 어떤 클래스나 함수를 임포트 할 때 이름을 바꾸는 기능
``` kotlin
// 같은 이름의 함수가 두 개의 다른 패키지에 있을 때, 별칭을 사용하여 충돌 방지
import com.kotlinLang.Study1.printHello as printHello1
import com.otherPackage.Study1.printHello as printHello2

fun main() {
    printHello1() // com.kotlinLang.Study1의 printHello 호출
    printHello2() // com.otherPackage.Study1의 printHello 호출
}

// 다음과 같이 별칭 생성 가능
typealias FruitFilter = (Fruit) -> Boolean
```

<br>

2. 구조 분해와 componentN 함수
    - 구조분해는 객체나 데이터를 여러 개의 변수로 한 번에 나누어 할당하는 방법이다. 구조분해를 사용할 때, 내부적으로 componentN 함수가 호출된다.
    - `data class`는 기본적으로 `componentN`이라는 함수도 자동으로 만들어준다.
    - 직접 `componentN` 함수를 만들려면 앞에 `operator` 라는 키워드를 붙여줘야 한다.

``` kotlin
data class Person3(
    val name: String,
    val age: Int
)

fun main() {
    val person = Person3("ㅇㅇㅇ",20)
    
    
    val (name,age) = person // 자동으로 componentN 함수가 호출하여 아래 두줄과 똑같은 기능으로 사용된다.
    // val name = person.component1()
    // val age = person.component2()

    println("Person3(name=${name}, age=${age})")
}
```

<br>

3. Jump와 Label
    - 특정 expression에 `라벨이름@` 을 붙여서 하나의 라벨로 간주하고 break, continue, return 등을 사용하는 기능

``` kotlin
abc@ for (i in 1..100){ // 라벨을 명시해 바깥의 코드를 break하게 만들어줌
    for (j in 1..100){
        if (j==2){
            break@abc
        }
    }
}
```

<br>

4. TakeIf와 TakeUnless
    - 코틀린에서는 method chaning을 위한 특이한 함수를 제공한다.
    - takeIf는 주어진 조건이 true일 때 객체 자신을 반환하고, 조건이 false일 경우 null을 반환한다.
    - takeUnless는 takeIf와 반대로 동작합니다. 주어진 조건이 false일 때 객체 자신을 반환하고, 조건이 true일 경우 null을 반환한다.

``` kotlin
fun a() {
    val number = 10

    // 조건이 true인 경우, number를 반환하고, 그렇지 않으면 null을 반환
    val result = number.takeIf { it > 5 }
    println(result) // 출력: 10

    // 조건이 false인 경우, null을 반환
    val result2 = number.takeIf { it > 20 }
    println(result2) // 출력: null
}

fun b() {
    val number = 10

    // 조건이 false인 경우, number를 반환하고, 그렇지 않으면 null을 반환
    val result = number.takeUnless { it > 20 }
    println(result) // 출력: 10

    // 조건이 true인 경우, null을 반환
    val result2 = number.takeUnless { it > 5 }
    println(result2) // 출력: null
}

fun c() {   // 이 함수들은 메소드 체이닝에서 조건을 기반으로 체인을 중단하거나 계속할지 결정할 때 특히 유용하다.
    val input = "Kotlin"

    // 길이가 5 이상인 경우에만 대문자로 변환하고, 그렇지 않으면 null을 반환
    val result = input.takeIf { it.length >= 5 }?.uppercase()
    println(result) // 출력: KOTLIN

    // 길이가 5 이상이 아닌 경우에만 대문자로 변환하고, 그렇지 않으면 null을 반환
    val result2 = input.takeUnless { it.length >= 5 }?.uppercase()
    println(result2) // 출력: null
}
```
<br>
<br>

## scope function
> 일시적인 영역을 형성하는 함수로 람다를 사용해 일시적인 영역을 만들고 코드를 더 간결하게 만들거나, method chaning 에 활용하는 함수를 scope function이라고 한다. 확장 함수로 구현되어 있다.

``` kotlin
person?.let {   // let은 확장함수로 람다를 받아 람다 결과를 반환한다. scope function의 한 종류
    println(it.name)
    println(it.age)
}
```

### scope function의 종류
> 필요할 때 찾아서 사용한다. 다만 가독성을 오히려 해치는 경우가 있기 때문에 적절한 사용이 필요하다.

- `this`는 생략이 가능한 대신 다른 이름을 붙일 수 없다. `it`은 생략이 불가능한 대신 다른 이름을 붙일 수 있다.   
    - let, also은 모두 it을 사용하고, run과 apply는 this를 사용한다. (with도 this)
``` kotlin
val result = "Kotlin".let {
    println(it)  // it refers to "Kotlin"
    it.length
}

val result = "Kotlin".run {
    println(this)  // this refers to "Kotlin"
    this.length    // or simply length
}
```

1. let : 하나 이상의 함수를 call chain의 결과로 호출 할 때 사용한다. `.`으로 연결해서 여러개의 함수를 사용할 때
    - `::`는 함수를 참조할 때 사용한다.

2. run : 객체 초기화와 반환값의 계산을 동시에 해야 할 때 사용한다.

3. also : 객체 그 자체가 반환되고 객체를 수정하는 로직이 call chain 중간에 필요할 때 사용한다.

4. apply : 객체 그 자체가 반환된다. 객체를 설정할 때 객체 수정 로직이 call chain 중간에 필요할 때 사용한다.

5. with : 특정 객체를 다른 객체로 변환해야하는데 모듈간의 의존성에 의해 정적팩토리 혹은 toClass 함수를 만들기 어려울 때 사용한다.


<br>
<br>

## 인텔리제이 단축키 모음

1. Alt + Enter : 코드에 문제가 있을 때, 오류를 자동으로 수정하거나 관련된 제안을 제공한다.

2. Alt + Insert : 생성자뿐만 아니라 게터와 세터, toString(), equals() 및 hashCode() 등의 다양한 코드 생성 옵션을 사용할 수 있다.

3. Shift + Enter : 커서가 있는 위치에서 현재 줄을 분리하고, 그 아래에 새로운 줄을 추가하여 커서를 이동한다.

4. Alt + J : 똑같은 단어를 여러 개 선택하여 동시에 변경 가능하다.


5. 인텔리제이 신규 표시 끄기 : file -> settings -> Editor -> Inlay Hints -> Usages 체크를 해제

6. Ctrl + O : 오버라이딩 단축키
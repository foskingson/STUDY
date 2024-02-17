# Java
- 자바는 객체지향 프로그래밍 언어로 플랫폼 독립성과 풍부한 표준 라이브러리, 멀티스레딩 등의 특징을 가지고 있다. 
    - 플랫폼 독립성 : Java 프로그램은 일반적으로 바이트코드로 컴파일되며 호환 가능한 JVM이 있는 모든 장치에서 실행될 수 있다.
    - 멀티 스레딩 : 자바는 멀티 스레딩 기능이 내장되어 개발자가 여러작업을 동시에 수행하는 프로그램을 작성할 수 있다.
- 자바는 대중적으로 가장 많이 쓰여지는 언어로 다양한 라이브러리,프레임워크와 개발자 커뮤니티가 존재한다.

<br>
<br>

## 객체 지향 프로그래밍
- 객체 지향 프로그래밍은 프로그램을 여러개의 독립된 단위인 객체들의 모임으로 파악하고자 하는 것이다. 각각의 객체는 메시지를 주고받고 데이터를 처리할 수 있다.
### 특징 
1. 다형성(*중요*) : 다형성은 역할(인터페이스)과 구현(특정 기능을 제공하는 구체적인 클래스, 구현체)으로 나눈다. 역할과 구현으로 구분하여 프로그램의 변경이 유연하고 용이해진다.
    - 예를 들면 운전자는 차의 종류가 바뀌어도 자동차들이 자동차의 역할만 공통되고 운전자는 운전자의 역할만 알고 있으면 운전이 가능하다. 차가 바뀌어도 운전자에게 영향을 주지 않는다.
    - 이론적으로 들어가면 한 타입의 참조 변수를 통해 여러 타입의 객체를 참조할 수 있도록 하는 것 이다.
2. 추상화 : 불필요한 것을 제거하고 객체들의 공통적이고 본질적인 속성을 모아 틀(인터페이스)을 만들어놓은 것이다.  
3. 캡슐화 : 데이터(속성)와 데이터에 대해 작동하는 메서드(함수)를 단일 단위 또는 클래스로 묶어 데이터를 외부로부터 보호하는것이다. 예로는 접근제어자와 getter/setter가 있다.
4. 상속 : 상속은 새 클래스가 기존 클래스의 속성과 동작(메서드)을 상속받을 수 있도록 하는 메커니즘이다. 코드의 재사용성을 촉진한다.
### 5가지 원칙
1. SRP 단일 책임 원칙 
    - 한 클래스는 하나의 책임만 가져야한다.
    - 기준은 변경으로 변경이 있을때 다른 곳에 영향이 적으면 단일 책임 원칙을 잘 따른 것이다.
2. OCP 개방-폐쇠 원칙 (*중요*)
    - 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
    - 다형성을 활용하여 인터페이스를 통해 새로운 클래스(구현체)를 만들어 기능을 확장할 수 있다.
    - 구현체를 변경할때 클라이언트 코드를 변경해야되는 오류를 별도의 설정자를 통해 해결할 수 있다.
3. LSP 리스코프 치환 원칙
    - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위타입의 인스턴스로 바꿀 수 있어야 한다.
    - 하위 클래스인 구현체는 인터페이스의 규약(메서드명 및 데이터 속성 등)을 지켜야 한다.
4. ISP 인터페이스 분리 원칙
    - 특정 클라이언트를 위한 인터페이스 여러개가 범용 인터페이스 하나보다 낫다.
    - 인터페이스를 적절한 기능에 맞게 분리하여 인터페이스가 명확하고 복잡하지 않게 만든다.
    - 예를 들면 자동차 인터페이스는 운전 인터페이스와 정비 인터페이스로 분리하고 사용자 클라이언트를 운전자 클라이언트와 정비사 클라이언트로 분리할 수 있다. 이러한 분리를 통해 하나의 인터페이스가 변하더라도 서로 간의 영향을 주지 않는다.
5. DIP 의존관계 역전 원칙 (*중요*)
    - 개발자는 추상화에 의존해야하고 구체화에 의존하면 안된다. 의존성 주입은 이 원칙을 따르는 방법중 하나다.
    - 구현체에 의존하지 말고 인터페이스에 의존하라는 뜻이다. 클라이언트가 인터페이스에 의존해야 구현체를 유연하게 변경할 수 있다.
    - `MoneyRepository moneyRepository = new MysqlMoneyRespository();` MoneyRepository 인터페이스에 의존하고 있지만 뒤에 new MysqlMoneyRespository();를 통해 구현체에도 의존하므로 DIP에 위반한다.
<br>
<br>

## 자바의 실행 환경
- Byte Code: 자바 컴파일러가 자바 소스 프로그램을 컴파일한 기계어로 JVM에서 실행되는 Binary Code이다. 
Byte Code는 CPU에 의하여 실행되는 것이 아니라, JVM에서 interpreter 방식으로 해석 및 실행이 된다.
- JDK: JDK(Java Development Kit)는 자바 응용프로그램 개발에 필요한 자바 컴파일러 및 기타 도구, JRE(Java Runtime Environment),
 클래스 라이브러리의 소스 및 자바 응용프로그램 샘플 소스 등을 포함한 개발 도구이다
- JVM: JVM(Java Virtual Machine)은 서로 다른 플랫폼에서 동일한 자바 실행 환경을 제공한다.
JVM은 각 플랫폼에 맞게 포팅되어야 하므로 플랫폼 종속적이다.
<br>
<br>


## 자바의 동작 원리
- 컴퓨터 => Java Virtual Machine => 자바소스코드(.java) =[컴파일]=> 애플리케이션, 프로그램
<br>
<br>

## 실습환경 준비
- jdk(java development kit) 설치 https://www.oracle.com/kr/java/technologies/downloads/#jdk21-linux 
    - 환경변수 설정까지 하기
- 비주얼 스튜디오 코드 세팅 https://offbyone.tistory.com/437
<br>
<br>

## 변수 네이밍 규칙
- 저장할 값에 어울리는 이름
- 밑줄,문자,숫자 사용가능 / 공백 불가능
- 밑줄 또는 문자로 시작가능 / 숫자 불가능
- 한 단어 또는 2개이상 단어의 연속
- 소문자로 시작하고 뒤에 연속으로 나오는 단어의 시작글자는 대문자로 작성
- 예약어 사용 불가 (ex : int, public, static,....)

<br>
<br>

## 상수 final
- 변수와 달리 상수는 지정하면 변경할 수 없다. 코드의 견고성,유지관리 용이성을 향상시킬 수 있다.
``` java
final int constantValue = 42;
// constantValue=1; // 오류, 상수는 변경할 수 없다
```

<br>
<br>

## 형변환
- 정수형에서 실수형, 실수형에서 문자열 등으로 형변환이 가능하다.
``` java
// 정수에서 실수로
int score = 93;
System.out.println(score); // 93
System.out.println((float) score); // 93.0
System.out.println((double) score); // 93.0
// 실수에서 정수로
float score_f = 93.3F;
double score_d = 98.8;
System.out.println((int) score_f); // 93
System.out.println((int) score_d); // 98

// 변수에 형변환된 데이터 집어넣기
double convertedScoreDouble = score; // 191 -> 191.0
// int -> long -> float -> double (자동 형변환) // int보다 double이 큰 범위 이기때문에 형변환된 데이터를 집어도 자동으로 형변환된다.

// 숫자를 문자열로
String s1 = String.valueOf(93);
s1 = Integer.toString(93);
System.out.println(s1); // 93

String s2 = String.valueOf(98.8);
s2 = Double.toString(98.8);
System.out.println(s2); // 98.8

// 문자열을 숫자로
int i = Integer.parseInt("93");
System.out.println(i); // 93
double d = Double.parseDouble("98.8");
System.out.println(d); // 98.8

```



## 입출력
### 입력 
- Scanner : 콘솔, 파일 또는 문자열과 같은 다양한 소스에서 입력을 읽는 데 사용한다.
    1. `import java.util.Scanner;` 스캐너의 클래스를 가져오기
    2. `Scanner scanner = new Scanner(System.in);` 스캐너의 인스턴스를 생성한다. 생성자는 적절한 입력스트림을 전달한다. 여기서 System.in는 일반적으로 콘솔 입력용 키보드인 표준 입력 스트림이다.
        - 한글이 깨질 경우 `Scanner scanner = new Scanner(System.in, "MS949");` 혹은 `Scanner scanner = new Scanner(System.in, "UTF-8");` 을 사용한다.
    3. `String name = scanner.nextLine();` 입력을 받는다.
        - nextLine(): 입력 라인을 문자열로 읽는다.
        - nextInt(): 다음 토큰을 정수로 읽는다.
        - nextDouble(): 다음 토큰을 double로 읽는다.
    4. `scanner.close();` 입력 작업을 완료하고 시스템 리소스를 확보하기위해 scanner개체를 닫는다. 
<br>

- JOptionPane.showInputDialog : Swing 라이브러리의 일부이며 그래픽 사용자 인터페이스(GUI)에서 입력 대화 상자를 만드는 데 사용한다. 일반적으로 팝업 대화 상자를 통해 사용자에게 입력을 요청하려는 데스크톱 애플리케이션에서 사용된다.
    1. `import javax.swing.JOptionPane;` JOptionPane 클래스 가져오기
    2. `String name = JOptionPane.showInputDialog("Enter your name:");` 입력 대화상자로 입력을 받는다.
    ``` java 
    // 다양한 데이터 타입 입력방법
    String name = JOptionPane.showInputDialog("Enter your name:");
    int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age:"));
    double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height (in meters):"));
    ```
    3. 만약에 입력 대화를 취소했을 경우 종료처리를 한다.
    ``` java
    if (name != null) {
        // 입력을 받았을 경우
        JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
    } else {
        // 취소한 경우
        JOptionPane.showMessageDialog(null, "Input canceled.");
    }
    ```
<br>

### 출력
- System.out.print : 출력을 콘솔에 띄워주는 자바에 기본적으로 내장되어 있는 메서드이다.
```  java
    System.out.print("Hello, World!");
    System.out.println("Hello, World!");            // println을 사용하면 출력후 개행문자를 추가해 다음 출력은 줄바꿈이 일어난다.
    System.out.println("The answer is: " + number); // + 를 통해 변수와 함께 사용할 수 있다.
    System.out.printf("The answer is: %d",number);  // printf를 통해 형식을 지정하여 사용 할수도 있다.
```

<br>
<br>

## 참조
1. 기본 변수 : 기본 변수는 int, double, boolean 등과 같은 간단하고 미리 정의된 데이터 유형을 보유한다.
    - 이러한 변수는 자신이 나타내는 실제 값을 직접 저장한다.
    - 스택 메모리에 저장된다.
    - 원시 변수는 객체에 대한 참조를 보유하지 않습니다. 그들은 그 자체로 가치를 갖고 있는다.
    - Java의 기본 유형의 예로는 int, double, boolean, char 등이 있다.
<br>

2. 참조 변수: 참조 변수는 힙 메모리의 객체에 대한 참조(메모리 주소)를 보유합니다.
    - 실제 객체 데이터를 직접 저장하지 않습니다. 대신 힙에 있는 객체의 위치를 ​​가리키는 메모리 주소를 저장한다.
    - 참조 변수는 객체에 접근하고 조작하는 데 사용된다.
    - 스택 메모리에 저장되지만 참조하는 객체는 힙 메모리에 저장된다.
    - 참조변수는 문자열, 클래스, 배열, 인터페이스에 사용된다.
    - 참조변수는 똑같은 객체를 참조하는 변수가 여러개일 때 하나의 참조변수의 내용이 바뀌면 다른 참조변수의 내용도 바뀐다. (서로 참조하는 주소가 같기 때문이다.)

<br>
<br>

## ==과 equals의 차이점
- == 라는 비교연산자는 각각 참조하는곳이 같은지 확인하는 연산자이다.
- equals라는 메서드는 내용(값)이 같은지 비교할 수 있다.
- 정수나 문자와 같은 원시데이터를 비교할때는 ==(동등비교연산자)를 사용하고 그게 아닌 배열과 같은 데이터를 비교할때는 equals를 사용한다
    - 다만 예외로 new를 사용하지 않는 문자열은 동등비교연산자를 사용한다.
- equalsIgnoreCase()를 사용하면 대소문자 구분없이 문자열이 같은지 확인가능하다.
<br>
<br>

## ArrayList (배열리스트)
- 배열과 리스트의 특징을 가지고 있는 자료구조
- 배열은 생성시 고정된 크기를 갖지만 배열리스트는 동적이며 프로그램 실행중에 크기가 변할 수 있다.
- 배열리스트는 배열보다 요소의 추가 및 제거의 다양한 조작에 있어서 유리하다.
- 배열은 초기화시 메모리에 할당되어 빠르지만 배열리스트는 데이터변경시 메모리를 재할당하여 느리다. 따라서 크기가 고정된 간단한 작업이라면 배열이 더 좋다.
<br>
<br>

## Do While
- while문과 비슷한 반복문이지만 do-while문은 조건문이 참이든 거짓이든 적어도 한번은 내부코드를 실행해준다.
``` java
i=1

do {
    System.out.println(i);
} while(i==2);  // 조건이 맞지 않지만 한번은 실행한다.
```

<br>
<br>

## 메서드 (Method)
- 자바에서 메서드는 특정 작업을 수행하는 코드 블록이며 함수라고도 불린다. 클래스 또는 인터페이스 내에 정의된다.
- 기능을 그룹화하여 동일한 기능을 여러위치에서 편리하게 사용할 수 있게 해준다.
### 자주 사용되는 메서드

- String 클래스 / 아래 나오는 a에는 "012345"가 들어있다고 가정 (String a="012345")
    1. length() : 문자열의 길이를 반환합니다. `a.length()` a의 문자열 길이 6 반환. 문자열이 아닌 배열일 경우 length()대신 length를 사용한다.
    2. charAt(int index): 지정된 인덱스에 있는 문자를 반환합니다.  `a.charAt(1)` 1번 인덱스에 들어있는 1 반환
    3. substring(int BeginIndex): 지정된 인덱스에서 시작하는 하위 문자열을 반환합니다. `a.substring(2)`  0~1까지를 제외한  "2345" 반환
    4. indexOf(String str): 지정된 하위 문자열이 처음 나타나는 인덱스를 반환합니다. `a.indexOf('2')` '2'가 처음으로 등장하는 인덱스 2 반환 
    5. contains() : 안에 있는 문자열이 들어있는 문자열이면 true아니면 false반환한다. `a.contains("23")` 들어있는 문자열임으로 true 반환
    6. replace() : 안에 있는 문자열을 변환해준다. `a.replace("123","321");` => a는 "032145"로 변환됨
    
- Arrays 클래스 / `import java.util.Arrays;` / 아래 나오는 b에는 [1,2,3,4,5]가 들어있다고 가정 (int[] b = [1,2,3,4,5])
    1. toString(array): 배열의 문자열 표현을 반환합니다.  `Arrays.toString(b)` [1,2,3,4,5] => "12345"
    2. sort(array): 배열의 요소를 오름차순으로 정렬합니다.
    3. copyOf(originalArray, newLength): 지정된 배열을 복사하고, 자르거나 0으로 채워 지정된 길이를 얻습니다. `int[] arr=Arrays.copyOf(b, 7);` b의 요소 5개 불러오고 나머지는 0으로 채워서 7길이의 배열 arr 생성

- ArrayList 클래스 / `import java.util.ArrayList;` / 아래 나오는 i에는 [1,2,3,4,5]가 있다고 가정 / (ArrayList<Integer> i = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));)
    1. add(element): ArrayList에 요소를 추가합니다. `i.add(10)` 10을 추가해 [1,2,3,4,5,10]이 된다. `i.add(0,0)` 를 하면 0번째 인덱스에 0을 추가 [0,1,2,3,4,5] 가 된다.
    2. get(index): 지정된 인덱스에 있는 요소를 검색합니다. `i.get(0)` 0번째 인덱스 값 1 반환
    3. size(): ArrayList의 요소 수를 반환합니다. `i.size()` 크기 5 반환

- Math 클래스  
    1. abs(x): x의 절대값을 반환합니다. `Math.abs(-10)` => 10 반환
    2. sqrt(x): x의 제곱근을 반환합니다.
    3. max(a, b): 두 값 중 더 큰 값을 반환합니다.
    4. random(): 0.0(포함)과 1.0(제외) 사이의 임의의 double 값을 반환합니다. `Math.random() * 10 + 1` 1~10의 난수 반환

- Scanner 클래스 / 위의 입출력 참고

- File 클래스 / import java.io.File; / `File f= new File("C:\f.txt")` File의 인스턴스 생성했다고 가정
    1. exists(): 파일이나 디렉터리가 존재하는지 확인합니다. `f.exists()` 존재하면 true 아니면 false
    2. isDirectory(): 디렉토리인지 확인합니다. `f.isDirectory()` txt파일이므로 false
    3. list(): 디렉터리에 있는 파일 이름의 배열을 반환합니다.

- System 클래스
    1. out.println(): 텍스트 한 줄을 표준 출력으로 인쇄합니다.
    2. currentTimeMillis(): 현재 시간을 밀리초 단위로 반환합니다.
    3. exit(int status): 현재 실행 중인 JVM(Java Virtual Machine)을 해당 상태로 종료합니다.
<br>
<br>

## 클래스 
- 자바에서 클래스는 객체를 생성하기 위한 틀 또는 템플릿이다. 
- 객체는 클래스의 인스턴스이며 데이터와 메서드를 캡슐화한다. 
- 연관된 메서드끼리 묶어놔 코드를 정리정돈 해준다.
``` java
    class test{
    public static void hi(){
        System.out.println("하이");
        }
    }

    public class classAPP {
        public static void main(String[] args) {
            test.hi();
        }
    }
```
### new 
- 클래스 인스턴스(객체) = new 클래스();
- 클래스 타입의 인스턴스를 생성할 때 사용하는 코드 
- new연산자를 이용하면 데이터의 값은 Heap영역에 저장되고 stack영역에는 참조값이 저장된다
### static 
- static(정적) 메서드는 클래스가 메모리에 올라갈때 static메모리 영역에 자동으로 생성되어 인스턴스를 생성하지 않고 사용할 수 있다.
- 정적변수는 인스턴스의 수에 상관없이 복사본은 하나지만 인스턴스는 인스턴스의 숫자만큼 복사본이 있기 때문에 정적변수가 메모리 절약에 유리하다.
- static이라는 키워드가 있다면 클래스 소속(클래스 변수)이고 아니라면 클래스의 인스턴스 소속이다.
- 정적 변수는 클래스와 직접적으로 연결되어 있어 하나의 인스턴스를 통해 값을 변경하면 모든 인스턴스에 영향을 준다
- 인스턴스변수는 각 인스턴스에 자체 복사본이 있어서 한 인스턴스를 통해 변경해도 다른 인스턴스에 영향을 주지않는다
``` java
class MyClass {
    public static int staticNum=10; // 정적변수
    public int instNum=10;          // 인스턴스 변수
}
public class a{
    public static void main(String[] args) {
        MyClass instance1 = new MyClass();
        MyClass instance2 = new MyClass();

        // MyClass.instNum=20; // 오류, 인스턴스변수이기 때문에 클래스를 통해 접근 불가능
        MyClass.staticNum=20; // 정상, 정적 변수는 클래스를 통해 접근 가능

        instance1.staticNum= 20;  // Myclass 내부의 staticNum과 instance2의 staticNum까지 변경된다.
        instance1.instNum=20;     // Myclass나 instance2의 instNum은 변경되지 않는다.
       
    }
}

```

### 생성자
- 생성자(Constructor)는 객체가 생성될때 호출되어 객체를 초기화하는데 사용되는 함수이다.
    - 생성자의 특징
    1. 클래스와 동일한 이름 : 생성자는 자신이 속한 클래스와 동일한 이름을 갖으며 반환유형이 없으며 'void'또한 없다
    2. 초기화 : 객체의 상태를 초기화하는 역할로 기본값을 설정하고 매개변수를 사용하여 변수를 초기화하고 필요한 설정을 수행할 수 있다.
    3. 암시적 호출: new 키워드를 사용하여 객체를 생성하면 생성자가 자동으로 호출된다. 생성자는 클래스에 최소 1개는 있어야 하며, 생성자 코드가 없을 경우 컴파일러가 기본생성자를 자동으로 생성한다.
    4. 오버로딩 : 다른 메서드처럼 생성자도 오버로드될 수 있다. 하나의 클래스가 서로 다른 매개변수 목록을 가진 여러 생성자를 가질 수 있다.
``` java
public class Car {
    // 인스턴스 변수
    String make;  
    String model; 
    int year;      

    // 매개변수를 가진 생성자
    public Car(String make, String model, int year) {
        this.make = make;   // this를 사용하여 현재 클래스의 인스턴스 변수를 참조한다. 매개변수와 인스턴스변수의 이름이 동일할때 구분하기 위해 사용된다.
        this.model = model;
        this.year = year;
    }

    // 기본 값으로 초기화된 생성자
    public Car() {
        this.make = "알 수 없음";
        this.model = "알 수 없음";
        this.year = 0;
    }

    public static void main(String[] args) {
        // 생성자를 사용하여 객체 생성
        Car myCar = new Car("Toyota", "Camry", 2022);   // 매개변수를 가진 생성자가 자동으로 호출됨
        Car defaultCar = new Car();                     // 기본 값으로 초기화된 생성자가 자동으로 호출됨
    }
}
```

<br>

### 열거형
- 열거는 상수 컬렉션을 정의하는데 사용되는 특수 데이터 유형이다. 일반적으로 요일, 월, 색상 등 고정된 값 집합을 나타내는데 사용된다.
``` java
public class Main {
    // // 열거형 Day를 정의
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        // 열거형 상수를 사용
        Day today = Day.MONDAY;

        switch (today) {
            case MONDAY:
                System.out.println("Today is Monday");
                break;
            case TUESDAY:
                System.out.println("Today is Tuesday");
                break;
            default:
                System.out.println("It's not a weekday");
                break;
        }
    }
}
```

<br>
<br>

## 패키지
- 서로 관련 있는 클래스들을 한 곳에 묶어 놓은 것이다. 폴더 안에 클래스 파일들이 있다면 그 폴더가 패키지가 된다.
- 같은 패키지(폴더) 안에서는 클래스끼리 서로 사용할 수 있지만 다른 패키지에서 사용하려 하면 import를 통해 가져와서 사용해야 한다.

<br>
<br>

## 접근 제어자 (access level modifier)
- 클래스 또는 메소드의 접근 권한을 제어하는 데 사용되는 키워드를 나타낸다
- 접근 제어는 클래스의 내부 세부 정보를 캡슐화하고 모듈식 설계를 촉진하는 데 도움을 준다.
1. public : public으로 선언된 멤버는 다른 클래스에서 액세스할 수 있다.
2. protected : protected로 선언된 멤버는 동일한 패키지 내에서 또는 다른 패키지의 하위 클래스에서 액세스할 수 있다.
3. 기본값 : 동일한 패키지 내에서만 접근 가능하다 (ex: int a;)
    - 기본 액세스 수준은 동일한 패키지 내에서만 액세스를 허용하므로 종종 "package-private"이라고도 합니다.
4. private : private으로 선언된 멤버는 동일한 클래스 내에서만 접근 가능하다.

<br>
<br>

## 상속
- a클래스가 있을때 b클래스가 a클래스를 상속받아 a클래스의 기능을 사용할 수 있게 해준다. (코드의 재사용성)
- 확장의 의미로 상속을 사용할땐 extends를 사용하고 인터페이스를 통해 구현체를 만들땐 implements를 사용한다.
- 상속을 통해 기존 클래스의 수정 없이 유지보수를 할 수 있기 때문에 확장성이 좋다.
- 부모 클래스가 생성자함수를 가지고 있다면 자식 클래스도 생성자함수를 작성해야한다.
- 자식클래스에서 super를 통해 부모클래스의 변수나 메서드를 참조할수 있다.
    + `super.x` 는 부모클래스의 x라는 변수를 참조한다.
    + `super(x)` 는 부모클래스의 생성자를 호출한다.
+ `class 서브클래스 extends 부모클래스` 와 같이 사용할 수 있다.
- 오버라이딩(Overriding) : 부모-자식 상속 관계에 있는 클래스에서 상위 클래스의 메서드를 하위 클래스에서 재정의하는 것을 말한다.
``` java
// 슈퍼(부모)클래스
class Animal {
    // 모든 동물의 공통 속성
    String name;

    // Animal 클래스의 생성자
    Animal(String name) {
        this.name = name;
    }

    // 동물 소리를 내는 메서드
    void makeSound() {
        System.out.println("어떤 일반적인 동물 소리");
    }
}

// 슈퍼클래스 Animal을 상속하는 서브(자식)클래스
class Dog extends Animal {
    // 강아지에게 특유의 속성
    String breed;

    // Dog 클래스의 생성자
    Dog(String name, String breed) {
        // 슈퍼클래스 (Animal)의 생성자 호출
        super(name);    // super를 통해 부모클래스를 참조할 수 있다.
        this.breed = breed;
    }

    // 강아지용 makeSound 메서드 오버라이딩
    @Override // 메소드를 오버라이드(재정의)할 때 사용
    void makeSound() {
        System.out.println("멍멍!");
    }

    // 강아지에게 특유의 메서드
    void fetch() {
        System.out.println("강아지가 공을 가져옵니다");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        // 슈퍼클래스 (Animal)의 인스턴스 생성
        Animal genericAnimal = new Animal("일반 동물");
        genericAnimal.makeSound(); // Animal의 makeSound 메서드 호출

        // 서브클래스 (Dog)의 인스턴스 생성
        Dog myDog = new Dog("버디", "래브라도");
        myDog.makeSound(); // Dog의 오버라이딩된 makeSound 메서드 호출
        myDog.fetch(); // Dog의 fetch 메서드 호출

        // 서브클래스 인스턴스를 통해 슈퍼클래스의 속성에 접근
        System.out.println("강아지의 이름: " + myDog.name);
        System.out.println("강아지의 종: " + myDog.breed);
    }
}

```

<br>
<br>

## 오버로딩과 오버라이딩 
- 오버로딩 : 메서드 오버로딩을 사용하면 클래스에 이름은 같지만 매개변수(유형, 번호 또는 순서)가 다른 여러 메소드가 있을 수 있다. (상속과 관련 없음)
``` java
class Calculator {  // 오버로딩의 예
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

- 오버라아딩 : 메소드 오버라이딩을 사용하면 하위 클래스가 상위 클래스에서 이미 제공한 메소드의 특정 구현을 수정하여 제공할 수 있다. (상속에 관련됨)
``` java 
class Animal {  // 오버라이딩의 예
    void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }
}
```


<br>
<br>


## 기타
- vscode 기준 foreach라고 치면 for(String string : args) 자동완성이 생긴다.






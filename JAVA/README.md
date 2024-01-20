# Java
- 자바는 객체지향 프로그래밍 언어로 플랫폼 독립성과 풍부한 표준 라이브러리, 멀티스레딩 등의 특징을 가지고 있다. 
    - 플랫폼 독립성 : Java 프로그램은 일반적으로 바이트코드로 컴파일되며 호환 가능한 JVM이 있는 모든 장치에서 실행될 수 있다.
    - 멀티 스레딩 : 자바는 멀티 스레딩 기능이 내장되어 개발자가 여러작업을 동시에 수행하는 프로그램을 작성할 수 있다.
- 자바는 대중적으로 가장 많이 쓰여지는 언어로 다양한 라이브러리,프레임워크와 개발자 커뮤니티가 존재한다.

<br>
<br>

## 자바의 실행 환경
- Byte Code: 자바 컴파일러가 자바 소스 프로그램을 컴파일한 기계어로 JVM에서 실행되는 Binary Code이다. 
Byte Code는 CPU에 의하여 실행되는 것이 아니라, JVM에서 interpreter 방식으로 해석 및 실행이 된다.
JDK: JDK(Java Development Kit)는 자바 응용프로그램 개발에 필요한 자바 컴파일러 및 기타 도구, JRE(Java Runtime Environment),
 클래스 라이브러리의 소스 및 자바 응용프로그램 샘플 소스 등을 포함한 개발 도구이다
JVM: JVM(Java Virtual Machine)은 서로 다른 플랫폼에서 동일한 자바 실행 환경을 제공한다.
JVM은 각 플랫폼에 맞게 포팅되어야 하므로 플랫폼 종속적이다.
<br>


## 자바의 동작 원리
- 컴퓨터 => Java Virtual Machine => 자바소스코드(.java) =[컴파일]=> 애플리케이션, 프로그램
<br>

## 실습환경 준비
- jdk(java development kit) 설치 https://www.oracle.com/kr/java/technologies/downloads/#jdk21-linux 
    - 환경변수 설정까지 하기
- 비주얼 스튜디오 코드 세팅 https://offbyone.tistory.com/437
<br>

## 입출력
### 입력 
- Scanner : 콘솔, 파일 또는 문자열과 같은 다양한 소스에서 입력을 읽는 데 사용한다.
    1. `import java.util.Scanner;` 스캐너의 클래스를 가져오기
    2. `Scanner scanner = new Scanner(System.in);` 스캐너의 인스턴스를 생성한다. 생성자는 적절한 입력스트림을 전달한다. 여기서 System.in는 일반적으로 콘솔 입력용 키보드인 표준 입력 스트림이다.
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

## ==과 equals의 차이점
- == 라는 비교연산자는 각각 참조하는곳이 같은지 확인하는 연산자이다.
- equals라는 메서드는 내용(값)이 같은지 비교할 수 있다.
- 정수나 문자와 같은 원시데이터를 비교할때는 ==(동등비교연산자)를 사용하고 그게 아닌 배열과 같은 데이터를 비교할때는 equals를 사용한다
    - 다만 예외로  new를 사용하지 않는 문자열은 동등비교연산자를 사용한다
<br> 

## ArrayList (배열리스트)
- 배열과 리스트의 특징을 가지고 있는 자료구조
- 배열은 생성시 고정된 크기를 갖지만 배열리스트는 동적이며 프로그램 실행중에 크기가 변할 수 있다.
- 배열리스트는 배열보다 요소의 추가 및 제거의 다양한 조작에 있어서 유리하다.
- 배열은 초기화시 메모리에 할당되어 빠르지만 배열리스트는 데이터변경시 메모리를 재할당하여 느리다. 따라서 크기가 고정된 간단한 작업이라면 배열이 더 좋다.
<br> 


### 메서드 (Method)
- 자바에서 메서드는 특정 작업을 수행하는 코드 블록이며 함수라고도 불린다. 클래스 또는 인터페이스 내에 정의된다.
- 기능을 그룹화하여 동일한 기능을 여러위치에서 편리하게 사용할 수 있게 해준다.
### 자주 사용되는 메서드

- String 클래스 / 아래 나오는 a에는 "012345"가 들어있다고 가정 (String a="012345")
    1. length() : 문자열의 길이를 반환합니다. `a.length()` a의 문자열 길이 6 반환. 문자열이 아닌 배열일 경우 length()대신 length를 사용한다.
    2. charAt(int index): 지정된 인덱스에 있는 문자를 반환합니다.  `a.charAt(1)` 1번 인덱스에 들어있는 1 반환
    3. substring(int BeginIndex): 지정된 인덱스에서 시작하는 하위 문자열을 반환합니다. `a.substring(2)`  0~1까지를 제외한  "2345" 반환
    4. indexOf(String str): 지정된 하위 문자열이 처음 나타나는 인덱스를 반환합니다. `a.indexOf('2')` '2'가 처음으로 등장하는 인덱스 2 반환 
    
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

### new 
- 클래스 인스턴스(객체) = new 클래스();
- 클래스 타입의 인스턴스를 생성할 때 사용하는 코드


### Scanner
-
nextLine() : 문자열을 입력받는 메서드
nextInt() : 정수형 데이터를 입력받는 메서드
netxFloat() : 실수형 데이터를 입력받는 메서드



### contains();
- 안에 있는 문자열이 들어있는 문자열이면 true아니면 false반환


### bool




static이 있다->class
static이 없다->instance
'클래스를 통해' 새로운 instance(f1)를 만들경우 class는 링크되어있는 채로 복사가 된다.
반면 instance는 링크되지 않은 독립적이어서 복제된 f1의 instance를 바꿔도 다른 영역에 영향을 못준다
class는 앞서 말했듯이 링크 되어있기 때문에 f1의 class 영역을 변경할경우 원본 class 에도 영향을 주고 이로인해 다른 instance(f2)의 class 정보도 바뀌게 된다.

`static`은 클래스의 소속인데 이 클래스가 인스턴스의 변수에 접근하려면 어떤 인스턴스의 변수인지 알 수 없음
- `static`이 아닌 변수에 접근하는 메소드는 `static`이면 안 됨

`static`으로 선언된 변수들은 인스턴스를 만들 때마다 컴퓨터의 메모리를 사용하지 않아도 되기 때문에 자원을 절약할 수 있음

public -> 다른클래스에서 접근가능한 메소드
private -> 같은클래스에서만 접근가능한 메소드.
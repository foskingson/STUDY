# Java
- 자바는 객체지향 프로그래밍 언어로 플랫폼 독립성과 풍부한 표준 라이브러리, 멀티스레딩 등의 특징을 가지고 있다. 
    - 플랫폼 독립성 : Java 프로그램은 일반적으로 바이트코드로 컴파일되며 호환 가능한 JVM이 있는 모든 장치에서 실행될 수 있다.
    - 멀티 스레딩 : 자바는 멀티 스레딩 기능이 내장되어 개발자가 여러작업을 동시에 수행하는 프로그램을 작성할 수 있다.
- 자바는 대중적으로 가장 많이 쓰여지는 언어로 다양한 라이브러리,프레임워크와 개발자 커뮤니티가 존재한다.
<br>
<br>


### 자바의 동작 원리
- 컴퓨터 => Java Virtual Machine => 자바소스코드(.java) =[컴파일]=> 애플리케이션, 프로그램
<br>

### 실습환경 준비
- jdk(java development kit) 설치 https://www.oracle.com/kr/java/technologies/downloads/#jdk21-linux 
    - 환경변수 설정까지 하기
- 비주얼 스튜디오 코드 세팅 https://offbyone.tistory.com/437
<br>

## 입출력
#### 입력 
- Scanner : 콘솔, 파일 또는 문자열과 같은 다양한 소스에서 입력을 읽는 데 사용한다.
    1. `import java.util.Scanner;` 스캐너의 클래스를 가져온다
    2. `Scanner scanner = new Scanner(System.in);` 스캐너의 인스턴스를 생성한다. 생성자는 적절한 입력스트림을 전달한다. 여기서 System.in는 일반적으로 콘솔 입력용 키보드인 표준 입력 스트림이다.
    3. `String name = scanner.nextLine();` 입력을 받는다.
        - nextLine(): 입력 라인을 문자열로 읽는다.
        - nextInt(): 다음 토큰을 정수로 읽는다.
        - nextDouble(): 다음 토큰을 double로 읽는다.
    4. `scanner.close();` 입력 작업을 완료하고 시스템 리소스를 확보하기위해 scanner개체를 닫는다.


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
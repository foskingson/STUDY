# Spring 
- 자바기반의 애플리케이션 프레임워크로 애플리케이션 개발을 도와주는 다양한 기능을 제공한다.
<br>
<br>

## 특징
- 스프링의 특징을 처음 접할때 이론적으로 접하면 이해하기가 어렵다. hello-spring의 회원관리코드를 보면서 접하면 이해하기가 좀 더 편하다.
1. 제어반전(IOC),의존성주입(DI) : IOC에서는 개발자가 아닌 스프링이 애플리케이션 구성 요소의 수명 주기와 종속성을 관리하고 객체내에서 인스턴스를 생성해서 메서드를 사용하는 것이 아닌
생성자를 통해 외부로 부터 받아와 메서드를 사용하는걸 DI라고 한다.
    - 예를 들어 수많은 클래스들이 b라는 클래스를 사용하다가 c라는 클래스를 사용한다면 new라는 인스턴스 생성을 통해 코드를 작성했다면 b를 쓰는 클래스를 전부 변경해야 하지만
    스프링을 사용한다면 설정클래스파일에 b랑c중에서 사용할 클래스를 설정해두면 그걸 생성자가 인자로 받아와 메서드를 호출한다.
``` java
public class MyService {
    // DI의 예
    private final MyRepository repository;

    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```

2. 관점 지향 프로그래밍(AOP) : AOP는 주요 핵심 로직과 공통 기능(로깅,보안 등)을 분리하여 애플리케이션에서 중복되는 부가 기능을 모듈화하여 재사용하도록 지원하는 것이다.
``` java
// 주요 비즈니스 로직 클래스
public class BusinessLogic {
    public void performTask() {
        System.out.println("주요 작업 수행 중...");  // 주요 작업 수행 중
    }
}

// 실행 시간 로깅을 위한 aspect
// aspect : 공통 관심사를 모듈화해놓은 것
public aspect LoggingAspect {
    private long startTime;  // 시작 시간 저장을 위한 변수

    before() : execution(* BusinessLogic.performTask()) {   // BusinessLogic.performTask 메서드 실행전에 실행됨
        startTime = System.currentTimeMillis();
        System.out.println("시작 시간: " + startTime);  // 시작 시간 출력
    }

    after() : execution(* BusinessLogic.performTask()) {    // BusinessLogic.performTask 메서드 실행후에 실행됨
        long endTime = System.currentTimeMillis();
        System.out.println("종료 시간: " + endTime);  // 종료 시간 출력

        long executionTime = endTime - startTime;
        System.out.println("실행 시간: " + executionTime + " 밀리초");  // 실행 시간 출력
    }
}

```

3. 모델-뷰-컨트롤러(MVC): 애플리케이션의 개발 영역을 MVC(Model, View, Controller)로 구분하여 각 역할에 맞게 코드를 작성하는 개발 방식이다
    - 모델 : 애플리케이션의 데이터,로직,규칙을 관리하는 역할을 담당한다. 모델의 변경 사항은 컨트롤러에 통보될 수 있으며 그에 따라 뷰가 업데이트 될 수 있다.
    - 뷰 : 애플리케이션의 사용자인터페이스(UI)를 담당한다. 모델이 변경되면 업데이트되어 사용자에게 최신 데이터가 표시된다.
    - 컨트롤러 : 사용자의 입력을 처리하고 그에 따라 모델과 뷰를 업데이트하는 중개자 역할이다. 
``` java
// Model (모델)
public class StudentModel {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// View (뷰)
public class StudentView {
    public void printStudentDetails(String studentName) {
        System.out.println("학생 정보: " + studentName);
    }
}

// Controller (컨트롤러)
public class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void updateView() {
        view.printStudentDetails(model.getName());
    }
}

```

4. PSA(Portable Service Abstraction): 환경의 변화와 관계없이 일관된 방식의 기술로의 접근 환경을 제공하는 추상화 구조이다. 
    - 인터페이스와 구현체로 나뉘어져 양식과 같은 추상적인 인터페이스를 설계해 그걸통해 실제 기능이 담긴 구현체를 만든다. (인터페이스 기반 프로그래밍)
    - 인터페이스를 잘 설계해놓는다면 인터페이스만 보고도 어떤 코드인지 이해할 수 있다.
    - 구현체는 인터페이스에 의존하는 구성요소에 영향을 주지 않기 때문에 구현체를 교체할 때 인터페이스를 변경할 필요가 없다. 유지관리가 쉬워진다.

5. 스프링부트(Spring Boot): 웹프로그램을 간단하게 만들게 도와주는 자바의 웹 프레임워크이다. 스프링의 장점을 가져오고 문제점을 보완하였다.
    - 스프링을 통해 개발하려면 복잡한 설정파일을 만들어줘야 하는데 스프링부트를 사용하면 이런 설정파일을 만들어준다. https://start.spring.io/
    - 스프링부트는 애플리케이션을 독립형 JAR로 실행할 수 있는 임베디드서버(Tomcat,Jetty 등)가 포함되어 있어 외부 서버 설정 없이 배포가 가능하다.
    - 스프링부트는 모니터링, 로깅, 보안 등의 기능을 내장하고 있어서 애플리케이션의 운영과 관리가 편리하다.
<br>
<br>

## 다형성
- 다양한 유형의 객체를 공통 유형의 객체로 처리할 수 있는 객체지향 프로그래밍의 기본 개념이다.
- 다형성과 PSA는 서로다른 개념이지만 인터페이스와 구현체를 통해 이점을 가질 수 있다.  
- 다형성으로 인해 소프트웨어 설계의 유연성, 모듈성, 확장성이 더 좋아진다.
- 다형성은 주로 인터페이스를 통해 달성된다. 여러 클래스가 하나의 인터페이스를 통해 상호 교환적으로 처리된다.
``` java
// 코드에 보이는 것처럼 Shape라는 하나의 인터페이스을 통해 Circle과 Square 라는 구현체를 만들 수 있다.
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

```




<br>
<br>

## 윈도우 cmd창에서 스프링 실행하기 
1. 프로젝트폴더로 이동 gradlew build를 쳐 빌드파일을 생성한다.    / 오류가 날 경우 gradlew clean build를 통해 빌드파일을 지우고 다시 생성할수도 있다..
2. 빌드후 생성된 폴더로 이동 cd build/libs
3. jar파일을 실행하면 완료 java -jar hello-spirng-0.0.1-SNAPSHOT.jar / 서버에 배포할때는 jar파일만 서버에 넣어주고 실행시키면 된다.
4. 종료는 Ctrl+c

<br>
<br>


## 정적컨텐츠 처리 단계
1. URL 형식: localhost에 연결할 때 프로토콜 및 포트 번호가 포함된 전체 URL을 사용해야 합니다. 올바른 형식은 http://localhost:port/hi.html입니다. 여기서 port는 Spring 애플리케이션이 실행 중인 포트 번호입니다.
2. 컨트롤러 확인: 요청이 이루어지면 Spring은 요청된 URL과 일치하는 매핑이 있는지 등록된 컨트롤러를 확인합니다. /hi.html에 대한 매핑이 있는 컨트롤러가 있는 경우 요청을 처리하고 관련 컨트롤러 메서드가 실행됩니다.
3. 정적 리소스 확인: 일치하는 컨트롤러 매핑이 없으면 Spring은 정적 리소스 위치를 확인합니다. 기본적으로 Spring Boot 애플리케이션에서 정적 리소스는 /src/main/resources/static 디렉터리에서 찾습니다.
<br>
<br>


## 도메인 주도 설계 (DDD: Domain Driven Design)
- 집단 즉 팀이 서로 협의하여 정한 규칙에 맞게 개발하는 방식이다.
- 팀간의 공통언어 유비쿼터스 언어를 공유해 협업의 복잡도를 줄일 수 있다.
- 유비쿼터스 언어의 예로는 의료분야에서 환자,처방,치료와 같은 의료기반의 공통언어로 합의할수 있다.
<br>
<br>

## 테스트 주도 설계 (TDD: Test-Driven Development)
- 먼저 테스트 코드를 작성한 후 비즈니스 로직을 구현하는 방식이다.
<br>
<br>

## @ 어노테이션(Annotation) 
- 어노테이션 코드를 메서드나 클래스 호출전에 작성하여 클래스나 메서드에 특수한 기능을 추가해줄 수 있다.
- @GetMapping("/hi") : HTTP GET 요청을 처리하는 메소드를 매핑하는데 사용된다. localhost:8080/hi라는 경로로 요청이 들어오면 아래의 메서드를 수행한다.
- @RequestParam("name") String name : HTTP 요청 파라미터를 메서드의 매개변수로 바인딩할때 사용된다. localhost:8080/name=ad 와 같이 주소창에서 값을 넘겨주면 해당 값을 매개변수로 활용한다.
- @ResponseBody : HTTP의 Body에 문자내용을 직접반환한다. 아래 메서드 반환값으로 "hello"를 넣어주면 웹에 hello라고 표시된다.
- @PostMapping({"/members/new"}) : HTTP Post 요청을 처리하는 메소드를 매핑하는데 사용된다.
- @Autowired : 다른 클래스에서 bean에 등록된 객체를 주입받을 수 있다.
- @Component : spring이 클래스를 spring bean에 자동으로 등록해준다.
    - @Controller : 해당 클래스가 컨트롤러 역할을 한다고 명시한다. 해당 어노테이션 안에 @Component가 들어있기 때문에 spring bean에도 자동으로 등록해준다.
    - @Service : 해당 클래스가 서비스 역할을 한다고 명시한다. 해당 어노테이션 안에 @Component가 들어있기 때문에 spring bean에도 자동으로 등록해준다.
    - @Repository : 해당 클래스가 리포지토리 역할을 한다고 명시한다. 해당 어노테이션 안에 @Component가 들어있기 때문에 spring bean에도 자동으로 등록해준다.
- @Configuration : 해당 클래스가 설정파일 역할을 한다고 명시하고 안에서 Bean을 수동으로 등록해줄 수 있다.
- @Bean : @Component가 자동으로 spring bean에 등록해준다면 @Bean은 수동으로 spring Bean에 등록해 외부 라이브러리를 사용할 수 있게 해준다.
- @Transactional : 메서드가 실행되는 동안 트랜잭션을 시작하고, 메서드가 정상적으로 완료되면 트랜잭션을 커밋하고, 만약 예외가 발생하면 롤백할 수 있다. 데이터베이스나 다른 리소스와의 일관성을 유지하기 위해 사용된다.
- @SpringBootTest : 스프링 부트 테스트에 필요한 의존성을 제공해준다.
    - @Test : JUnit에서 테스트 할 대상을 표시한다.
    - @BeforeEach : 테스트 메서드 실행 전에 실행된다.
    - @AfterEach : 테스트 메서드 실행 후에 실행된다.
- @Entity : Java 클래스를 관계형 데이터베이스의 테이블을 나타내는 JPA 엔터티로 표시하는 데 사용된다.
    - @Id : 엔티티의 기본 키 속성을 지정하는데 사용된다.
    - @GeneratedValue :기본 키 값이 자동으로 생성되는 방식을 지정한다.
``` java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // id를 기본 키값으로 지정하고 'GenerationType.IDENTITY' 방식으로 키가 자동으로 생성된다.

    private String name;
    private double price;
    // Getters/setters, 다른 메서드들....
}
```  









member.id라고만 해도 Thymeleaf를 통해 member.getId()랑 같은효과를 얻을수 있다

## 기타
- compileOnly 'org.springframework.boot:spring-boot-devtools' 라이브러리를 추가하고 설정에 들어가 Hot Code Replace 검색하여 auto로 변경하고 디버그로 실행하면 변경점이 재실행하지 않아도 반영된다.
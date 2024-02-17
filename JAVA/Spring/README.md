# Spring 
- 자바기반의 애플리케이션 프레임워크로 애플리케이션 개발을 도와주는 다양한 기능을 제공한다.
- 옛날 많이 사용되던 자바 기술인 EJB(Enterprise JavaBeans)는 복잡하고 무거운 등 문제가 많았다. 그래서 개발자들은 좀 더 쉬운 방법을 찾게 되었고 대안으로 스프링이 떠올랐다.
    - EJB 엔티티빈 기술(데이터베이스와의 상호 작용을 관리하는 기술)의 대체로 JPA(표준 인터페이스)의 하이버네이트(구현체)가 나왔다.
<br>
<br>

## 특징
- 스프링의 특징을 처음 접할때 이론적으로 접하면 이해하기가 어렵다. order폴더안의 각 예제에 맞는 테스트코드를 보면서 접하면 이해하기가 좀 더 편하다.
1. 제어반전(IOC),의존성주입(DI) : AppConfig(설정파일)의 등장으로 구현체는 자신의 기능을 수행하는 역할만 담당하고 프로그램의 제어흐름은 AppConfig가 가져간다. IOC에서는 개발자가 아닌 스프링이 스스로 필요한 구현체를 관리한다. 구현체 내에서 인스턴스를 생성해서 메서드를 사용하는 것이 아닌 생성자를 통해 외부로 부터 받아와 메서드를 사용해 의존성을 주입해주는 걸 DI라고 한다.
    - 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라고 한다.
    - AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IOC컨테이너 혹은 DI컨테이너라고 한다.
    - DI와 DI컨테이너 기술을 통해 객체지향 프로그래밍의 OCP와 DIP가 가능해진다.
    - 클라이언트 코드의 변경없이 기능을 확장하는게 가능해진다.
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
    - 스프링부트는 어디까지나 스프링 기술을 편리하게 할 수 있게 도와주는 기술이므로 스프링에 대해 잘 이해하고 사용해야 한다.
    - 실행할 수 있는 애플리케이션을 간단하게 만들어준다. https://start.spring.io/를 통해 프로젝트도 쉽게 생성할 수 있다.
    - 애플리케이션을 독립형 JAR로 실행할 수 있는 웹서버(Tomcat,Jetty 등)가 내장되어 있어 외부 서버 설정 없이 배포가 가능하다.
    - 모니터링, 로깅, 보안 등의 기능을 내장하고 있어서 애플리케이션의 운영과 관리가 편리하다.
    - 외부 라이브러리의 버전을 신경을 쓰지 않아도 자동으로 구성해준다. 호환성↑
    - 스프링부트의 기본 설정에 많은 기능들이 포함되어 있어 설정이 복잡하지 않다.
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

## 스프링 컨테이너
-  스프링 컨테이너는 스프링 빈의 생명주기를 관리하고 스프링 빈들에게 추가 기능을 제공하는 역할을 한다. 코드에서 ApplicationContext를 스프링 컨테이너라 한다
    - `ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);` 이러식으로 구성정보를 인자로 넣어서 스프링 컨테이너를 생성할 수 있다.
- 스프링 컨테이너는 기본적으로 싱글톤 방식으로 빈을 등록한다.
- BeanFactory <- ApplicationContext <- AnnotationConfigApplicationContext / BeanFactory는 스프링 컨테이너의 최상위인터페이스로 ApplicationContext가 상속받고 부가기능을 추가로 제공한다.
- 스프링 컨테이너는 자바코드인지 xml코드인지 몰라도 BeanDefinition(인터페이스)이라는 빈 설정 메타정보에 의존하여 스프링 빈을 생성한다. 따라서 다양한 설정 형식을 사용할 수 있다.
    - BeanDefinition을 실무에서 직접 사용할 일은 거의 없지만 가끔 오픈 소스 코드를 볼때 보인다면 이러하다는 것을 이해하면 된다.

- 스프링 컨테이너에서 스프링 빈 조회하기
``` java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);    // 스프링 컨테이너 생성

MemberService memberService = ac.getBean("memberService",MemberService.class);      // MemberService.class타입의 스프링빈 이름이 memberService인 객체 가져오기

MemberService memberService = ac.getBean(MemberService.class);        // 타입만으로도 가져올 수 있으나 같은 타입이 여러개일 경우 에러 발생

Map<String,MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);   // 특정 타입의 빈을 모두 가져와 Map형식으로 저장

// NoSuchBeanDefinitionException 빈 이름으로 조회했는데 없는 빈일 경우 발생하는 오류
// NoUniqueBeanDefinitionException 같은 타입이 여러개일 경우 발생하는 오류
```
- 부모타입으로 조회하면 자식타입까지 함께 조회한다. 모든 자바 객체의 최고 부모 타입인 Object로 검색하면 모든 스프링 빈을 조회한다. `getBeansOfType`으로 조회한다.
    - 스프링 내부적으로 쓰는 bean까지 다 나온다.


<br>
<br>

## 싱글톤 패턴
- 클래스의 인스턴스가 한개만 생성되게 하는 디자인 패턴이다.
- 아래 코드와 같이 싱글톤 패턴을 적용하지 않는다면 동일한 요청들을 다 서로 다른 객체들을 만들어 메모리 공간을 낭비하게 된다.
``` java
// 싱글톤 패턴을 적용하지 않았을 경우
AppConfig appConfig = new AppConfig();

// 호출할 때 마다 객체를 생성
MemberService memberService1 = appConfig.memberService();
MemberService memberService2 = appConfig.memberService();

// 참조값이 다름
System.out.println(memberService1);
System.out.println(memberService2);
assertThat(memberService1).isNotSameAs(memberService2);
```
<br>


- 아래의 코드처럼 싱글톤 패턴을 적용한다면 동일한 요청에 같은 객체를 여러번 이용할 수 있다.

``` java
// 싱글톤 패턴을 적용했을 경우
// static 영역에 객체를 1개만 생성한다. 
private static final SingletonService instance = new SingletonService();

// public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회할 수 있다. SingletonService.getInstance(); 를 통해 조회 가능
public static SingletonService getInstance(){
    return instance;    // 동일한 요청이 여러번 호출되도 같은 객체를 반환한다.
}

//3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
private SingletonService(){
}

public void logic(){
    System.out.println("싱글톤 객체 로직 호출");
}
```
<br>

- 싱글톤 패턴의 단점
    1. 싱글톤 패턴을 구현하는 코드 자체가 많아져 복잡해진다.
    2. 의존관계상 구현체에 의존하게 되므로 DIP를 위반한다. SingletonService.getInstance()를 통해 구현체를 지정해서 가져와야 하기 때문이다.
    3. 구현체에 의존하므로 OCP원칙을 위반할 가능성이 높다.
    4. 객체를 미리 생성해두고 시작하기 때문에 유연하게 테스트하기 어렵다.
<br>

### 싱글톤 컨테이너 (스프링 컨테이너)
- 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서 인스턴스를 싱글톤(1개만 생성)으로 관리해주는 싱글톤 컨테이너이다.
    - 스프링 빈이 싱글톤으로 관리되는 빈이다.
    - 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라고 한다.
- @Configuration을 붙여야 @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어져 싱글톤이 보장된다.
    - @Configuration가 없으면 싱글톤이 보장되지 않는다.
- 싱글톤 패턴을 위한 코드가 들어가지 않아도 된다. 
- DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다

### 싱글톤 방식의 주의점
- 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
    - 가급적 읽기만 가능해야 한다. 가급적 수정이 안되게 해야 한다.
- 스프링 빈은 항상 무상태(stateless)로 설계한다
- 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있다. (실무에서 자주 발생하는 문제)
<br>

``` java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
StatefulService bean1 = ac.getBean(StatefulService.class);
StatefulService bean2 = ac.getBean(StatefulService.class);

bean1.order("user1", 10000);    // user1 10000원 주문
bean2.order("user2", 20000);    // user2 20000원 주문

int price =bean1.getPrice();    // user1이 주문 금액 조회하고 싶어서 했는데 중간에 user2가 20000원 주문한 것때문에 20000원이 들어감
System.out.println(price);
```
- 위의 코드를 보면 StatefulService의 price필드는 공유되는 필드인데 특정 클라이언트가 값을 변경한다. (문제 발생) 
<br>

- 해결법: 공유되는 필드를 사용하지 않는다.
``` java
public int order(String name,int price){
        System.out.println("name= "+name+" price= " + price);
        return price;
}
```

<br>
<br>

## 컴포넌트 스캔과 의존관계 자동 주입
- @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
    - 등록되는 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다. OrderServiceImpl => orderServiceImpl 
    - @Component("orderService2")를 통해 이름을 직접 지정할 수도 있다.
- 컴포넌트 스캔은 기존에 수동으로 빈에 등록하던 설정정보와 달리 설정정보에 @ComponentScan을 붙이고 사용하는 각 구현체에 @Componet를 붙이면 해당 구현체를 스캔해 자동으로 빈에 등록해준다.
- 자동으로 빈에 등록하기 때문에 의존성주입도 필요한 곳에 @Autowired를 붙이면 자동으로 맞는 타입으로 의존성을 주입해준다.
``` java
@Autowired
public memberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}
```
<br>

- excludeFilters를 사용하면 컴포넌트 스캔에서 제외할 것들을 정할 수 있다. includeFilters로 포함할 것들 설정 가능하다.
    - `@ComponentScan(excludeFilters=@Filter(type = FilterType.ANNOTATION, classes = Configuration.class))`
- basePackages를 통해 탐색할 패키지를 지정할 수 있다. 지정하지 않으면 기본적으로 설정정보가 있는 패키지부터 다 탐색하기 때문에 설정정보는 최상단에 놓는게 좋다.
``` java
@ComponentScan(
    basePackages =  "order.core.member",       // order.core.member안에 있는 모든 파일 탐색
    excludeFilters=@Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
```
<br> 

- 스프링 부트를 사용하고 @SpringBootApplication가 프로젝트 시작루트 위치에 놔두면 알아서 빈을 찾아서 자동으로 등록해준다. @SpringBootApplication안에  @ComponentScan이 들어있기 때문이다.
<br>

### 컴포넌트 스캔 기본 대상
- @Component : 컴포넌트 스캔에서 사용된다.
- @Controller : 스프링 컨트롤러에서 사용된다.
- @Service : 스프링 비즈니스 로직에서 사용된다.
- @Repository : 스프링 데이터 접근 계층에서 사용된다.
- @Configuration : 스프링 설정 정보에서 사용된다.
    - @Configuration, @Controller, @Service,@Repository 안에 모두 @Component가 들어있다.
    - 애노테이션이 특정 애노테이션을 들고 있는 것을 인식 할 수 있는 것은 자바 언어의 기능이 아닌 스프링의 기능이다.

<br>

### 자동 빈 등록 VS 수동 빈 등록
- 자동 빈 등록과 수동 빈 등록의 빈 이름이 같을 경우 수동 빈 등록이 우선권을 가진다. 수동 빈이 자동 빈을 오버라이딩 해버린다.
- 다만 자동 빈 등록과 수동 빈 등록의 중복의 경우 보통 의도적이 아닌 여러 설정들이 꼬여서 만들어지는 경우가 대부분이기 때문에 잡기 어려운 버그가 생길 수 있다.
    - 스프링부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 나도록 기본값을 바꿔놓았다.


<br>
<br>

## 의존관계 자동 주입
- 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야만 동작한다.

### 의존관계 주입 방법
1. 생성자 주입 : 생성자를 통해서 의존관계를 주입 받는 방법이다. 최근에는 대부분 생성자 주입을 권장한다.
    - 생성자 호출 시점에 한번만 호출되므로 불변,필수 의존관계에 사용된다.
``` java
@Autowired  // 지금처럼 생성자가 1개라면 @Autowired 생략해도 자동 주입된다.
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
}
```
<br>

2. 수정자 주입 : setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입한다.
    - 선택, 변경 가능성이 있는 의존관계에 사용한다.
    - @Autowired의 기본동작은 주입할 대상이 없으면 오류가 발생하지만 @Autowired(required = false)를 통해 주입할 대상이 없을때 코드가 동작하게 할 수 있다.
    - set메서드를 public으로 열어둬야 하기 때문에 누군가 실수로 변경할 수도 있기 때문에 좋은 설계가 아니다.
``` java
private MemberRepository memberRepository;
private DiscountPolicy discountPolicy;

@Autowired
public void setMemberRepository(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}

@Autowired
public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
}
```
<br>

3. 필드 주입(**사용하지 않는게 좋다**) : 필드에 주입하는 방법이다.
    - 스프링 컨테이너가 의존성 주입을 해주지 않는다
    - 외부에서 변경이 불가능해 테스트가 어렵다.
    - DI 프레임워크가 없으면 아무것도 할 수 없다.
    - ** 사용하지 않는게 좋다. **
``` java
@Autowired
private MemberRepository memberRepository;
@Autowired
private DiscountPolicy discountPolicy

// 순수 자바 코드로 테스트를 할때
// 생성자 주입의 경우  UserService userService = new UserService(userRepository); 일반적으로 POJO(자바 객체)를 스프링 컨테이너 없이 사용해도 의존성 주입이 가능하지만
// 필드 주입의 경우 UserService userService = new UserService(); 스프링과 같은 DI를 지원하는 프레임워크가 없다면 의존성 주입이 안되기 때문에 테스트가 불가능하다.
```
<br>

4. 일반 메서드 주입 : 일반 메서드 함수로 주입하는 방법이다.
     - 일반적으로 잘 사용되지 않는다.
``` java
@Autowired
 public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
 this.memberRepository = memberRepository;
 this.discountPolicy = discountPolicy;
 }
```
<br>

### 주입할 스프링 빈이 없어도 동작해야할 때
- @Autowired(require=false) 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출되지 않는다. (수정자 주입을 사용할 때)
- @Nullable 자동 주입할 대상이 없으면 null이 입력된다
- Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
``` java
@Autowired(required = false)
public void setNoBean1(Member noBean1){
    System.out.println("nobean1: "+ noBean1);
}

@Autowired
public void setNoBean2(@Nullable Member noBean1){
    System.out.println("nobean2: "+ noBean1);
}

@Autowired
public void setNoBean3(Optional<Member> noBean1){
    System.out.println("nobean3: "+ noBean1);
}

// 출력결과
/*
nobean2: null
nobean3: Optional.empty
*/
// @Autowired(required = false)는 수정자 메서드 자체가 호출되지 않으므로 찍히지 않는다.
```
<br>

### 롬복 라이브러리
- 원래 직접 짜야하던 getter/setter, 생성자 등을 자동으로 생성해서 사용할 수 있게 해주는 라이브러리이다. 
<br>

- 롬복 라이브러리 사용법
1. build.gradle에 라이브러리 및 환경 추가
``` java
compileOnly 'org.projectlombok:lombok'
 annotationProcessor 'org.projectlombok:lombok'
 testCompileOnly 'org.projectlombok:lombok'
 testAnnotationProcessor 'org.projectlombok:lombok'

configurations {
 compileOnly {
 extendsFrom annotationProcessor
 }
}
```
2. 롬복 확장자 설치

- 롬복 라이브러리 활용 예시
1. @Getter/Setter : getter/setter를 직접 짜지 않고 사용할 수 있다.
``` java
@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hi");
        System.out.println(helloLombok.getName());
    }
}
```
<br>

2. @ToString : @ToString으로 클래스에 주석을 추가하면 해당 클래스에 대한 toString() 메서드가 자동으로 생성하여 클래스 필드의 값을 문자열 표현으로 변환하고 이를 반환한다.
``` java
@ToString
public class MyClass {
    private String name="hi";
    private int age=3;
}

MyClass obj = new MyClass();
System.out.println(obj);
// MyClass(name=hi, age=3) 출력
// @ToString을 붙이지 않으면 MyClass@<hexadecimal hash code> 이런식으로 나옴
```
<br>

3. @AllArgsConstructor : @AllArgsConstructor 어노테이션을 사용하면 클래스에 대한 모든 필드를 사용하는 생성자가 자동으로 생성된다.
    - @RequiredArgsConstructor : 의존성관계 주입에 필요한 생성자를 자동으로 생성해준다.
``` java
@AllArgsConstructor
@Getter
public class MyClass {
    private String name;
    private int age;

    public static void main(String[] args) {
        MyClass obj = new MyClass("홍길동", 30);
        System.out.println(obj.getName()); // 출력: 홍길동
        System.out.println(obj.getAge());  // 출력: 30
    }
}
```
<br>

### 조회 빈이 2개 이상일때
1. @Autowired 필드명 매칭 : @Autowired는 먼저 타입 매칭을 시도하고 빈이 2개 이상이면 필드이름, 파라미터 이름으로 빈을 추가 매칭한다.
2. @Quilifier 사용 : 추가 구분자를 붙여주는 방법이다.  @Qualifier끼리 매칭한다. 없을 경우 빈 이름으로 찾는다.
    - 빈 등록할때 `@Qualifier("mainDiscountPolicy")` 를 붙여주고 의존관계를 주입할때 `@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy` 이런식으로 주입해주면 구분자가 똑같은 빈을 주입해준다.
3. @Primary 사용 : 우선순위를 지정하는 방법이다. @Primary를 붙여준 빈을 우선적으로 찾아서 주입해준다.
<br>

### 조회힌 빈이 다 필요할 때
- List나 Map을 이용해 조회 빈을 여러개 주입할 수 있다.
``` java
@Test
void findAllBean(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
}

static class DiscountService{
    private final Map<String,DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    @Autowired
    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
        this.policyMap = policyMap;
        this.policies = policies;
        System.out.println("PolicyMap: "+ policyMap);   
        System.out.println("Polices: "+ policies);
    }
    
}

/*
출력결과
PolicyMap: {fixDiscountPolicy=order.core.discount.FixDiscountPolicy@478ee483, rateDiscountPolicy=order.core.discount.RateDiscountPolicy@1a7288a3}
Polices: [order.core.discount.FixDiscountPolicy@478ee483, order.core.discount.RateDiscountPolicy@1a7288a3]
*/

// DiscountPolicy discountPolicy = policyMap.get("fixDiscountPolicy")  이런식으로 해당 키값에 맞는 값을 가져올 수 있다.
```
<br>

### 자동/수동 사용의 기준
-보통 편리한 자동 기능을 기본으로 사용한다.
<br>

- 자동 빈 등록을 사용하는 경우
    - 업무 로직 빈 : 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리 등 비즈니스 요구사항에 맞춰 개발할 때 추가되거나 변경되는 빈이다.
    - 업무 로직은 숫자도 매우 많고, 한번 개발해야 하면 컨트롤러, 서비스, 리포지토리 처럼 어느정도 유사한 패턴이 있다. 이런 경우 자동 기능을 적극 사용하는 것이 좋다. 보통 문제가 발생해 어떤 곳에서 문제가 발생했는지 명확하게 파악하기 쉽다
- 수동 빈 등록을 사용하는 경우
    - 기술 지원 빈 : 기술적인 문제나 공통 관심사를 처리할 때 주로 사용된다. 데이터베이스 연결이나 공통 로그 처리처럼 업무 로직을 지원하는 하부 기술이나 공통 기술들이다.
    - 애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해서 설정 정보에 바로 나타나게 해야 유지보수하기 편하다.

<br>
<br>

## 빈 생명주기 
- 스프링 빈의 이벤트 라이프사이클
    - 스프링 컨테이너 생성 => 스프링 빈 생성 => 의존 관계 주입 => 초기화 콜백 => 사용 => 소멸전 콜백 => 스프링 종료
    - 초기화 콜백은 빈이 생성되고 의존관계 주입이 완료된 후 호출
    - 소멸전 콜백은 빈이 소멸되기 직전에 호출
<br>

- 스프링 빈은 객체를 생성하고 의존관계 주입이 다 끝나야 필요한 데이터를 사용할 수 있다.
``` java
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 url= "+ url);
        connect();
        call("초기화");
        disconnect();
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void connect(){
        System.out.println("connet : "+url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message: "+ message);
    }

    public void disconnect(){
        System.out.println("close: "+ url);
    }
}

@Bean   // 빈의 초기화 콜백(메서드)
public NetworkClient networkClient(){       
        NetworkClient networkClient = new NetworkClient();  
        networkClient.setUrl("http://hi");  // 빈 객체가 생성되고 초기화되기전에 호출되므로 데이터 사용 안됨
        return networkClient;
}

/*
생성자 url= null    
connet : null
call: null message: 초기화
close: null
*/
```
<br>

### 빈 생명주기 콜백
1. 인터페이스 InitializingBean, DisposableBean : 차례대로 메서드로 초기화와 소멸을 지원한다. 
    - 스프링 전용 인터페이스로 해당 코드 자체가 스프링에 의존한다.
    - 메서드 이름이 고정이다.
    - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다
    - 지금은 다음의 더 나은 방법들이 있어서 거의 사용하지 않는다.
``` java
public class NetworkClient implements InitializingBean, DisposableBean  {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 url= "+ url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connet : "+url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message: "+ message);
    }

    public void disconnect(){
        System.out.println("close: "+ url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}

/* 바꾸고 위의 테스트 코드 출력시
생성자 url= null
connet : http://hi
call: http://hi message: 초기화
close: http://hi

*/
```
<br>

2. 빈 등록 초기화, 소멸 메서드 지정 : 설정 정보에 `@Bean(initMethod = "init", destroyMethod = "close")` 처럼 초기화, 소멸 메서드를 지정할 수 있다.
    - 아래 코드처럼 초기화,소멸 메서드를 만들어 놓고 지정할 수 있다.
    - 스프링 빈이 스프링 코드에 의존하지 않는다.
    - 메서드 이름을 자유롭게 줄 수 있다.
    - 코드가 아니라 설정정보를 사용해 코드를 못 고치는 외부라이브러리에서도 초기화,소멸 메서드를 적용할 수 있다.
    - @Bean의 destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있어 따로 적어주지 않아도 추론 기능은 close , shutdown 라는 이름의 메서드를 자동으로 호출해준다. 
``` java
public void init() {
        connect();
        call("초기화");
    }


public void close() {
    disconnect();
}
```
<br>

3. 애노테이션 @PostConstruct, @PreDestroy : 간단하게 애노테이션을 붙여주어 초기화,소멸 메서드를 지정할 수 있다.
    - 최근 스프링에서 가장 권장하는 방법이다.
    - 유일한 단점으로 외부 라이브러리에 적용하지 못하기 때문에 그런 경우 2번째 방법을 사용한다.

<br>
<br>

## 빈 스코프
- 빈 스코프는 말 그대로 빈이 존재할 수 있는 범위를 말한다.
<br>

### 스프링의 스코프 종류
1. 싱글톤(default) : 기본 스코프로 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프다.
2. 프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 짧은 범위의 스코프다. `@Scope("prototype")` 을 통해 지정 가능하다.
3. 웹 관련 스코프 (웹 환경에서만 동작)
    - request : 웹이 요청이 들어오고 나갈때까지 유지되는 스코프다. 각 요청마다 별도의 빈 인스턴스가 생성되고 관리된다.
        - A전용 요청과 B전용 요청이 있으면 따로 생성되어 관리된다.
        - 프록시 객체나 Provider를 이용해서 객체를 필요한 시점까지 지연처리 할 수 있다.
    - session : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프다.
    - application : 웹의 서블릿 컨텍스('ServletContext')와 같은 범위로 유지되는 스코프다.
<br>

### 프로토타입 스코프
- 프로토타입 스코프를 스프링 컨테이너에서 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해 반환한다. 의존성 주입, 초기화까지 끝나면 스프링 컨테이너에서 관리하지 않는다.
    - 싱글톤은 A,B,C라는 인스턴스를 만들어도 모두 데이터와 메서드를 공유하지만 프로토타입은 A,B,C가 라는 인스턴스를 만들면 각자의 데이터 공간을 따로 사용한다.
- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행 되지만, 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행된다
- 실무에서 프로토타입 빈을 사용하는 경우는 거의 드물다.

<br>

### 싱글톤과 프로토타입을 같이 쓰면 생기는 문제점
- 문제는 싱글톤과 프로토타입을 함께 사용할때 생긴다.
    - 싱글톤 빈에서 프로토타입 빈을 주입받아서 사용하면 주입은 스프링컨테이너가 생성되고 나서 끝나고 주입받은 객체를 싱글톤으로 사용되기 때문에 결과적으로 싱글톤을 쓰는 거랑 다름없는 현상이 일어난다. 이러면 싱글톤을 쓰지 이런 상활을 원한것이 아닐 것이다.
<br>

### 해결방법
1. ObjectProvider
    - 아래 코드와 같이 ObjectProvider을 지정하면 getObject를 지정한 빈을 컨테이너에서 대신 찾아주는 서비스(DL : 의존관계 조회)를 제공한다.
    - ObjectProvider의 getObject() 메서드를 호출하는 시점에 스프링 컨테이너에서 빈을 생성하고 주입한다. 빈의 생성을 지연시켜 원하는 시점에 getObject()를 배치하면 된다.
    - 필요할때마다 조회해서 프로토타입의 역할을 수행할 수 있다.
``` java
private ObjectProvider<PrototypeBean> prototypeBeanProvider;

/*
생성자 및 그 외 코드들
*/

PrototypeBean prototypeBean = prototypeBeanProvider.getObject();    
```

<br>

2. JSR-330 Provider
    - 이 방법을 사용하려면 스프링부트버전에 맞는 관련 라이브러리를 gradle에 추가해야 한다. `jakarta.inject:jakarta.inject-api:2.0.1` 스프링부트 3.0 이상
    - 첫번째 방법과 사용법은 비슷하다.
    - 자바 표준이므로 다른 코드에서도 사용 가능하다.
``` java
private Provider<PrototypeBean> provider;

/*
생성자 및 그 외 코드들
*/

PrototypeBean prototypeBean = provider.get();    
```



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


<br>
<br>










member.id라고만 해도 Thymeleaf를 통해 member.getId()랑 같은효과를 얻을수 있다

## 기타
- compileOnly 'org.springframework.boot:spring-boot-devtools' 라이브러리를 추가하고 설정에 들어가 Hot Code Replace 검색하여 auto로 변경하고 디버그로 실행하면 변경점이 재실행하지 않아도 반영된다.
- vscode 단축키 
    - ctrl+쉬프트+r을 통해 리턴값 자동완성 / 리펙토링 기능도 있음
    - ctrl+엔터를 통해 현재 명령문을 완성하고 다음줄로 이동가능
    - 컨트롤 + d로 똑같은 코드 한번에 변경 가능
    - 원하는 클래스에 우클릭 후 소스작업=> 테스트 생성을 통해 테스트 코드 자동생성 가능
- 가끔 다른 클래스의 메서드가 인식이 안될때는 vscode를 껏다 키기




원래는 동시성 문제를 막기위해 컨커런트 해시맵을 사용해야한다.

구현체가 하나일경우 관례적으로 뒤에 Impl을 붙여서 사용한다


애자일 소프트웨어 개발 선언

// ctrl + 쉬프트 + / => 내가 지정해놓은 vscode 키셋인데 원하는 메서드에 대고 누르면 테스트코드 자동생성 가능

프로세스는 운영체제로부터 자원을 할당받는 작업의 단위이고 스레드는 프로세스가 할당받은 자원을 이용하는 실행의 단위입니다.
프로세스는 실행 중인 프로그램으로 생각할 수 있습니다. 자체 메모리 공간, 리소스 및 상태가 있는 실행 중인 애플리케이션의 인스턴스입니다.
예: 웹 브라우저가 열려 있고 텍스트 편집기가 실행 중이며 컴퓨터에서 음악을 재생하는 미디어 플레이어가 있다고 가정합니다. 이러한 각 응용 프로그램은 별도의 프로세스입니다.
스레드는 프로세스 내 실행 단위입니다. 이는 프로세스 내의 단일 순차적 제어 흐름을 나타냅니다.
예: 웹 브라우저 프로세스에는 사용자 입력 처리, 웹 페이지 렌더링, 네트워크에서 데이터 가져오기 등의 작업을 담당하는 여러 스레드가 있을 수 있습니다. 이러한 스레드는 함께 작동하여 원활한 검색 환경을 제공합니다.


##### 인프런의 김영한님의 강의를 참고해서 작성하였습니다.






# 스프링 Spring 웹 개발
> 해당 페이지에는 웹 애플리케이션 개발을 위한 스프링의 내용을 담았다. 스프링의 특징과 기능 관련해서는 https://github.com/foskingson/STUDY/tree/main/JAVA/Spring 해당 페이지로 이동하자.

<br>
<br>
<br>

## 서블릿
> 웹에서 서블릿(Servlet)은 자바 웹 애플리케이션을 구성하는 중요한 구성 요소 중 하나이다. 서블릿은 동적인 웹 페이지를 생성하거나, HTTP 요청에 응답하기 위한 자바 클래스이다. 서블릿은 주로 웹 애플리케이션 서버(WAS)에서 실행됩니다. 클라이언트의 HTTP 요청이 있을 때마다 서블릿 컨테이너는 해당 요청을 처리하기 위해 적절한 서블릿을 호출하고, 서블릿은 요청을 처리하고 HTTP 응답을 생성하여 클라이언트에게 반환하는 역할을 한다..
- 클라이언트가 요청하면 쓰레드가 서블릿을 호출한다.
- 기본적으로 WAS가 서블릿 컨테이너의 생명주기와 관리를 담당 한다.
    - 서블릿 객체는 최초 로딩 시점에 생성되어 싱글톤으로 관리된다.


<br>

- 서블릿 작업 수행 과정
```
1. HTTP 요청 분석 및 처리: 클라이언트의 HTTP 요청을 읽어 들이고, 필요한 작업을 수행합니다. 이 작업에는 사용자가 입력한 데이터를 읽는 것이 포함될 수 있습니다.

2. 비즈니스 로직 실행: 요청에 따라 비즈니스 로직을 실행하고, 데이터베이스나 다른 외부 시스템과의 상호 작용을 처리합니다.

3.HTTP 응답 생성: 클라이언트에게 반환할 HTML, XML, JSON 등의 콘텐츠를 생성하고, HTTP 응답을 생성하여 클라이언트에게 반환합니다.
```

<br>

> 일반적으로 HttpServlet 클래스를 상속하여 서블릿을 작성하며, 이를 통해 HTTP 프로토콜을 통한 통신을 간편하게 처리할 수 있다.

<br>
<br>
<br>


## 동시요청 - 멀티 쓰레드
> 애플리케이션 코드를 하나하나 순차적으로 실행하는 것을 쓰레드라고 한다. 동시 처리가 필요할때 멀티 쓰레드를 사용한다. 멀티 쓰레드에 대한 부분을 WAS가 처리해준다. 개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않고 설정만 따로 해주면 된다.

- 예를 들어 자바 메인 메서드를 처음 실행하면 main이라는 이름의 쓰레드가 실행된다.

<br>

#### 요청마다 쓰레드를 생성할 경우
- 장점 
    - 동시 요청을 처리할 수 있다.
    - 리소스가 허용할 때까지 처리 가능하다.
    - 하나의 쓰레드가 지연되도 나머지는 정상작동한다.
- 단점 
    - 쓰레드의 생성 비용은 비싸다. (컨텍스트 스위칭 비용도 많이 발생한다.)
    - 요청이 올때마다 쓰레드를 생성하면 응답속도가 늦어진다.
    - 쓰레드 생성에 제한이 없다. (고객 요청이 너무 많으면 쓰레드가 계속 생성되어 CPU,메모리 임계점이 넘어서 서버가 죽을 수 있다.)

<br>

#### 쓰레드 풀
> 쓰레드를 미리 정해놓은 개수 만큼 만들어놓고 요청을 처리할때 하나를 사용하고 다 사용하면 다시 쓰레드 풀에 반납한다. 쓰레드 풀에 남는 쓰레드가 없으면 대기하거나 거절한다. 톰캣은 최대 200개가 기본 설정이다.

- 최대 쓰레드 수를 조절하는게 제일 중요하다.(튜닝 포인트)
    - 너무 낮게 설정하고 동시 요청이 많을때 서버 리소스는 여유롭지만 클라이언트는 금방 응답이 지연된다.
    - 너무 높게 설정하고 동시 요청이 많을 때 CPU,메모리 리소스 임계점 초과로 서버 다운된다.
    - 최대한 실제 서비스와 유사하게 성능 테스트를 시도해봐야 한다.

- 장점 
    - 쓰레드가 미리 생성되 있으므로 쓰레드를 생성하고 종료하는 비용이 절약되고 응답이 빠르다.
    - 생성 가능한 쓰레드의 최대치가 있으므로 너무 많이 요청이 들어와도 기존 요청은 안전하게 처리 가능하다.

<br>
<br>
<br>

## 서버 사이드 렌더링 SSR 
> 서버에서 최종 HTML을 생성해서 클라이언트에 전달한다. 주로 정적인 화면에 사용된다. JSP, 타임리프(선호)과 같은 템플릿 엔진을 사용할 수 있다.

<br>

## 클라이언트 사이드 렌더링 CSR
> HTML결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용된다. 주로 동적인 화면에 사용된다. 

- React, Vue.js는 SSR과 CSR 전부 지원하는 웹 프레임워크이다.



## Postman
>Postman은 API 개발 및 테스트를 위한 협업 플랫폼으로, 다양한 기능을 제공하여 API 개발 및 테스트 작업을 효율적으로 수행할 수 있도록 지원한다.

-  Postman을 사용하여 다양한 유형의 API 요청(GET, POST, PUT, DELETE 등)을 생성하고 실행할 수 있다. 또한 API 응답을 확인하고 관리할 수 있다.
    - Postman을 통해 원하는 주소로 request요청을 보내볼 수 있다.




## HttpServlet
- 먼저 메인 애플리케이션 파일에 `@ServletComponentScan`를 붙이고 서블릿 파일에 `@WebServlet(name = "name", urlPatterns ="/")`를 붙여주고 `extends HttpServlet` 인터페이스에서 상속받아 온다.

<br>


### HTTP API
- HTTP Request API
    - 요청 정보 조회 : RequestHeaderServlet 파일 참고
    - 요청 파라미터 정보 조회 : RequestParamServlet 파일 참고 (get,post로 들어온 데이터)
    - 요청 바디 정보 조회 : RequestBodyStringServlet 파일 참고 (단순 텍스트)
    - 요청 바디 정보 조회 : RequestBodyJsonServlet 파일 참고 (JSON 주로 사용) 
- HTTP Response API
    - HttpServletResponse 기본 사용법 : ResponseHeaderServlet 파일 참고
    - 응답 데이터 HTML 반환 : ResponseHtmlServlet 파일 참고
    - 응답 데이터 JSON 반환 : ResponseJsonServlet 파일 참고

<br>


<br>
<br>
<br>

## 템플릿 엔진
> 템플릿 엔진은 웹 애플리케이션에서 동적인 콘텐츠를 생성하는 데 사용되는 도구이다. 정적인 HTML 문서에 동적으로 데이터를 삽입하거나 반복문, 조건문 등의 로직을 적용하여 동적인 웹 페이지를 생성할 수 있다. 서블릿과 자바 코드만으로도 동적인 HTML을 구현할 수 있지만 코드가 매우 복잡하고 비효율적이다. 따라서 자바 코드로 HTML을 만드는 것보다 차라리 HTML 문서에 동적으로 변경해야 하는 부분만 자바 코드를 넣으면 편리 할 것이다. 이것이 템플릿 엔진이 나온 이유이다.

- 템플릿 엔진에는 JSP, Thymeleaf 등이 있다. 주로 Thymeleaf를 사용한다.
- 템플릿 엔진에는 html안에서 제어문을 수행할 수 있는 태그 라이브러리를 제공한다.


<br>
<br>
<br>

## 프론트 컨트롤러/핸들러 어댑터
> 프론트 컨트롤러는 웹 애플리케이션의 진입점이며, 클라이언트로부터의 모든 요청을 처리하는 주요 컴포넌트이다. 간단한 프론트 컨트롤러의 예시로는 서블릿 기반의 웹 애플리케이션을 들 수 있다.
주로 공통된 로직을 처리할 때 사용된다.

>핸들러 어댑터(Handler Adapter): 클라이언트의 요청을 처리하는 데 사용되는 다양한 컨트롤러를 관리하고 실행하는 인터페이스입니다. 스프링은 여러 가지 유형의 컨트롤러를 지원하며, 이러한 컨트롤러를 실행하고 관리하는 데에는 각각의 핸들러 어댑터가 사용된다.

<br>
<br>
<br>

## 스프링 MVC
> 스프링에서 제공하는 MVC프레임워크는 공통기능을 처리하는 프론트 컨트롤러와 여러 종류의 컨트롤러를 선택할 수 있게해주는 핸들러 어댑터 그리고 뷰로 구성되어 각 기능의 코드가 다형성을 가진다. 스프링 MVC의 대부분 기능을 인터페이스로 제공한다. 과거에는 직접 구조를 설계했지만 최근에는 어노테이션 기반으로 컨트롤러를 만든다.

- DispatcherServlet 
    ``` 
    DispatcherServlet은 스프링 MVC 프레임워크에서 중심적인 역할을 하는 클래스다. 이 클래스는 웹 애플리케이션의 모든 클라이언트 요청을 받아들이고, 적절한 핸들러(컨트롤러)에게 요청을 전달하는 역할을 수행한다. 일종의 프론트 컨트롤러(Front Controller) 역할을 한다.
    ```

<br>

- 스프링 부트가 자동 등록하는 핸들러 매핑 HandlerMapping (숫자는 우선순위)
    ```
    0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
    ```

- 스프링 부트가 자동 등록하는 핸들러 어댑터 HandlerAdapter (숫자는 우선순위)
    ```
    0 = RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    1 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
    2 = SimpleControllerHandlerAdapter : Controller 인터페이스(애노테이션X, 과거에 사용) 처리
    ```
<br>

- 스프링 부트가 자동 등록하는 뷰 리졸버 (숫자는 우선순위)
```
1 = BeanNameViewResolver : 빈 이름으로 뷰를 찾아서 반환한다. (예: 엑셀 파일 생성 기능에 사용)
2 = InternalResourceViewResolver : JSP를 처리할 수 있는 뷰를 반환한다.
```

<br>

- 동작 방식
    ```  
    1. 핸들러 조회: 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러)를 조회한다.
    2. 핸들러 어댑터 조회: 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
    3. 핸들러 어댑터 실행: 핸들러 어댑터를 실행한다.
    4. 핸들러 실행: 핸들러 어댑터가 실제 핸들러를 실행한다.
    5. ModelAndView 반환: 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
    6. viewResolver 호출: 뷰 리졸버를 찾고 실행한다. JSP의 경우 InternalResourceViewResolver 가 자동 등록되고, 사용된다.
    7. View 반환: 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환한다. JSP의 경우 InternalResourceView(JstlView) 를 반환하는데, 내부에 forward() 로직이 있다.
    8. 뷰 렌더링: 뷰를 통해서 뷰를 렌더링 한다
    ```

<br>
<br>
<br>

## @Controller
> 스프링이 자동으로 스프링 빈에 등록하고 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다. @Controller가 모델뷰나 뷰를 반환하면 View Resolver에서 값을 받아 렌더링 해준다.

    예를 들어 return "ok"; 라고 하면 이걸 뷰 이름으로 인식해서 뷰를 찾고 그 뷰가 렌더링된다.

## @RestController
> @Controller가 모델뷰나 뷰를 반환하면 View Resolver에서 값을 받아 렌더링 해준다면 @RestController는 HTTP Response Body로 직접 데이터가 반환된다. View Resolver를 거치지 않는다. 주로 RESTful 웹 서비스를 개발할 때 사용한다.

    - 예를 들어 return "ok"; 라고 하면 그대로  String 값이 ok가 화면에 출력된다.
    - @RestController 는 @Controller + @ResponseBody => 이 2개를 합쳐진 기능을 가지고 있다.

## @RequestMapping
> @RequestMapping은 스프링 MVC에서 사용되는 어노테이션 중 하나로, 요청 정보를 매핑하여 해당 URL이 호출되면 이 메서드가 호출된다. 핸들러 매핑과 핸들러 어댑터의 기능을 동시에 가지고 있다.

    - ModelAndView : 모델과 뷰 정보를 담아서 반환한다.
    - 경로 정보를 2개를 넣을 수도 있다.
    - /hello-basic 와  /hello-basic/는 다른 URL이지만 스프링은 같은 요청으로 매핑한다.
    - @RequestMapping 을 잘 보면 클래스 단위가 아니라 메서드 단위에 적용되기 때문에 컨트롤러 클래스를 유연하게 하나로 통합할 수 있다.
    - @RequestMapping 은 URL만 매칭하는 것이 아니라, HTTP Method도 함께 구분할 수 있다. @RequestMapping(value = "/new-form", method = RequestMethod.GET) 사용하지 않아도 동작은 하지만 구분 해주는게 좋다. 설정하지 않으면 모든 HTTP 메서드 요청에 호출된다.
    - @RequestMapping(value = "/new-form", method = RequestMethod.GET) => @GetMapping("new-form") 처럼 간단하게 사용할수도 있다.

<br>

- PathVariable : url에서 변수값을 빼와 사용할 수 있다.
``` java
@GetMapping("/mapping/{userId}")    // "/mapping/users/{userId}/orders/{orderId}" 처럼 다중 설계도 가능하다.
    public String mappingPath(@PathVariable("userId") String data) {
    // @PathVariable String userId; 처럼 변수명을 같게 하면 괄호부분 생략가능
    log.info("mappingPath userId={}", data);
    return "ok";
}

/*
 http://localhost:8080/mapping/3 로 접속시 로그 정보
 2024-03-06T19:28:10.952+09:00  INFO 1060 --- [nio-8080-exec-2] m.m.b.requestmapping.MappingController   : mappingPath userId=3
*/
```

<br>

- 추가 정보
```
- @GetMapping(value = "/mapping-param", params = "mode=debug") : 특정 파라미터 조건 매핑

- @GetMapping(value = "/mapping-header", headers = "mode=debug") : 특정 헤더 조건 매핑

- @PostMapping(value = "/mapping-consume", consumes = "application/json") : 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume / 헤더의 contents-type이 json인 경우에만 호출

- @PostMapping(value = "/mapping-produce", produces = "text/html") : 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce / 헤더의 Accept 기반
```



## @RequestParam
> 스프링은 HTTP 요청 파라미터를 @RequestParam를 통해 받아올 수 있다.`@RequestParam("username") String username` 와 같은 방식으로 인자로 받아 올 수 있다.

<br>
<br>
<br>

## 로깅
> 스프링(Spring)에서 로깅(logging)은 애플리케이션의 실행 중에 발생하는 이벤트나 정보를 기록하는 프로세스이다. 운영 시스템에서는 `System.out.println()` 를 사용하지 않고 로깅 라이브러리를 사용해서 로그를 출력한다. `System.out.println()` 를 사용하면 모든 로그가 남아 서버가 폭주할 수 있지만 로깅을 사용하면 로그 레벨을 통해 필요한 정보만 로그로 띄울 수 있다.

#### 로그를 사용 하면 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고 출력 모양을 조절 할 수 있다. 기본 성능도 Sysout보다 좋다.
<br>

- 로그 레벨
```
- 로그 레벨을 설정해서 출력 결과를 확인할 수 있다. LEVEL: TRACE > DEBUG > INFO > WARN > ERROR

- application.properties파일 안에서 logging.level.root=info 를 통해 로그 레벨을 설정할 수 있으며 이러면 모든 파일의 로그 레벨은 info로 그 이상의 레벨은 출력하지 않는다. 설정을 안해놨다면 기본은 info로 되어있다.

- 보통 개발 서버는 debug 출력하고 운영 서버는 info 출력한다.
```

<br>

- 로깅 라이브러리 사용 예시
``` java
private final Logger log = LoggerFactory.getLogger(getClass()); 
// @Slf4j 라는 롬복 기능을 사용하면 위에 로그 호출을 자동으로 해준다.

@RequestMapping("/log-test")
public String logTest() {
    String name="spring";
    System.out.println("info log = "+name);

    // ("info log = "+name) <= log를 사용할때는 이런 방식을 사용하지 않는다. 
    // 만약 로그레벨이 info인데 log.debug("debug log = " + name); 를 한다면 출력은 되지 않지만 불필요한 + 연산이 들어간다.
    log.trace("trace log = {}",name);
    log.debug("debug log = {}",name);
    log.info("info log = {}",name);
    log.warn("warn log = {}",name);
    log.error("error log = {}",name);
    return "ok";
}

/* 해당 패키지 로그레벨이 최상위 일 경우 결과
info log = spring
2024-03-06T18:34:14.619+09:00 TRACE 22328 --- [nio-8080-exec-1] m.mvcStudy2.basic.LogTestController      : trace log = spring
2024-03-06T18:34:14.621+09:00 DEBUG 22328 --- [nio-8080-exec-1] m.mvcStudy2.basic.LogTestController      : debug log = spring
2024-03-06T18:34:14.621+09:00  INFO 22328 --- [nio-8080-exec-1] m.mvcStudy2.basic.LogTestController      : info log = spring
2024-03-06T18:34:14.621+09:00  WARN 22328 --- [nio-8080-exec-1] m.mvcStudy2.basic.LogTestController      : warn log = spring
2024-03-06T18:34:14.621+09:00 ERROR 22328 --- [nio-8080-exec-1] m.mvcStudy2.basic.LogTestController      : error log = spring
*/
```

<br>

- 추가 정보
```
SLF4J - http://www.slf4j.org
Logback - http://logback.qos.ch
```

<br>
<br>
<br>

## HTTP 요청 데이터 조회 - 기본 / 헤더 조회
> 스프링 프레임워크에서는 클라이언트의 HTTP 요청에 포함된 데이터를 컨트롤러에서 애노테이션 기반으로 쉽게 조회할 수 있도록 지원한다.

- 여러가지 요청 데이터 조회해보기
``` java
@RequestMapping("/headers")
public String headers(HttpServletRequest request,
                    HttpServletResponse response,
                    HttpMethod httpMethod,
                    Locale locale,
                    @RequestHeader MultiValueMap<String,String> headerMap,
                    @RequestHeader("host") String host, // 스트링 헤더는 필수 헤더
                    @CookieValue(value = "myCookie",required = false) String cookie
    ) {
        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap);
        log.info("header host = {}", host);
        log.info("myCookie = {}", cookie);

        return "ok";
    }

/* 결과
2024-03-06T21:46:00.581+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : request = org.apache.catalina.connector.RequestFacade@5fadf5b1
2024-03-06T21:46:00.582+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : response = org.apache.catalina.connector.ResponseFacade@125ce9c2
2024-03-06T21:46:00.582+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : httpMethod = GET
2024-03-06T21:46:00.582+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : locale = ko_KR
2024-03-06T21:46:00.583+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : headerMap = {content-type=[text/plain], user-agent=[PostmanRuntime/7.36.3], accept=[*\/*], postman-token=[1f52bb47-26e5-41b2-a509-1e0e80120fd9], host=[localhost:8080], accept-encoding=[gzip, deflate, br], connection=[keep-alive], content-length=[2]}
2024-03-06T21:46:00.583+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : header host = localhost:8080
2024-03-06T21:46:00.583+09:00  INFO 31188 --- [nio-8080-exec-1] m.m.b.request.RequestHeaderController    : myCookie = null
*/
```
> 여기서 MultiValueMap은 MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다. keyA=value1&keyA=value2

<br>

- 추가 정보
```
@Controller 의 사용 가능한 요청 파라미터 목록 : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html

@Controller 의 사용 가능한 응답 값 목록 : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html
```

<br>
<br>
<br>

## HTTP 요청 데이터 조회 - 쿼리 파라미터 / HTML Form
> 쿼리 파라미터와 HTML Form 둘다 똑같은 형식으로 데이터를 받아오기 때문에 HttpServletRequest의 `request.getParameter()` 를 사용하면 다음 두가지 요청 파라미터를 조회할 수 있다.

``` java
@RequestMapping("/request-param-v1")
public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    log.info("username = {} / age = {}",username,age);
    
    response.getWriter().write("ok");
}

// 결과
// 2024-03-06T22:20:24.937+09:00  INFO 29120 --- [nio-8080-exec-2] m.m.b.request.RequestParamController     : username = 1 / age = 1
```

<br>
<br>
<br>

## @RequestParam
> 스프링에서 제공하는 요청 데이터를 간단하게 조회하게 해주는 애노테이션이다.

``` java
@ResponseBody   // @RestController 처럼 반환해줌
@RequestMapping("/request-param-v2")
public String requestParamV2(@RequestParam String username,@RequestParam int age) {
    // String,int,Integer와 같은 단순 타입이면 @RequestParam을 생략해서 String username라고 써도 동작한다. 다만 이름이 요청파라미터와 같아야한다.
    // 다만 유지보수 할때 직관적으로 알아보기 힘들다.
    log.info("username = {} / age = {}",username,age);
    return "ok";
}
```
> 기본적으로 요청 값이 없다면 오류가 나지만 `@RequestParam(required = false) String username,@RequestParam(required = false)` int age 를 통해 required를 추가하면 요청값이 없어도 동작한다.

<br>

- 주의
> 스프링 부트 3.2 파라미터 이름 인식 문제가 있을 수 있다. 아래의 문제가 난다면 자바 컴파일러에 -parameters 옵션을 넣어주어야 애노테이션에 적는 이름을 생략할 수 있다. 아니면 build.gradle로 가서 버전을 3.1.9로 낮춰야 한다. 버전을 낮추는게 가장 확실하다.
```
java.lang.IllegalArgumentException: Name for argument of type [java.lang.String] 
not specified, and parameter name information not found in class file either.
```

<br>
<br>
<br>

##  @ModelAttribute
> 실제 개발을 하면 요청 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주어야 하는데 스프링은 기본 틀이 되는 객체만 만들어놓으면 나머지는 자동으로 해주는  @ModelAttribute 기능을 제공한다.

``` java
@ResponseBody
@RequestMapping("/model-attribute-v1")
public String modelAttributeV1(@ModelAttribute("helloData") HelloData helloData){       // helloData라는 모델 값도 자동으로 생성해준다.
    // 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩) 한다.
    // 예) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
     
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    log.info("data = {}", helloData);
    return "ok";
}

/*
2024-03-07T18:56:55.057+09:00  INFO 27824 --- [nio-8080-exec-1] m.m.b.request.RequestParamController     : username=1, age=0
2024-03-07T18:56:55.058+09:00  INFO 27824 --- [nio-8080-exec-1] m.m.b.request.RequestParamController     : data = HelloData(username=1, age=0)
*/
```
> 요청 파라미터값과 객체안의 프로퍼티 명이 같다면 @ModelAttribute 는 생략할 수 있다. 다만 생략하면 직관적으로 알아보기 어려워진다.

<br>
<br>
<br>

## @Data
> 롬복 라이브러리의 기능으로 @Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 를 자동으로 적용해준다.

<br>
<br>
<br>

## HTTP 요청 메시지 - 단순 텍스트
> 요청 파라미터와 다르게 HTTP 메시지 바디에 데이터가 담겨오면 @RequestParam, @ModelAttribute를 사용해서 데이터를 읽을 수 없다. 다만 HTML Form 형식으로 전달될 경우는 요청 파라미터로 인정된다. 

- HttpEntity를 사용하면 HTTP 헤더, 바디 정보를 쉽게 조회할 수 있다. 응답에도 사용 가능하다.
    - @RequestBody / @ResponseBody를 통해 인자로 바로 받아오는 것도 가능하다.
``` java
@PostMapping("/request-body-string-v3")
public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
    // HttpEntity를 사용하면 스프링이 자동으로 바디에 있는 메시지를 제네릭 타입으로 형변환해준다.
    // @RequestBody String messageBody => 인자로 바로 받아서 사용할 수도 있다.
    String messageBody = httpEntity.getBody();

    log.info("message : {}",messageBody);
    
    return new HttpEntity<>("ok");
}
```
> RequestEntity / ResponseEntity 로 분리해서 사용할 수도 있다.

<br>
<br>
<br>

## HTTP 요청 메시지 - JSON
> JSON타입으로 요청한 메시지도 위에 단순 텍스트와 비슷한 방식으로 사용할 수 있다. HttpEntity 나 @RequestBody를 사용하면 HTTP 메시지 컨버터가 content-type을 확인하여 우리가 원하는 타입으로 변환해준다.

``` java
@ResponseBody
@PostMapping("/request-body-json-v3")
public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {   
    log.info("message : {}", helloData);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
}
```
> @RequestBody는 생략하면 안된다. 생략하면 @ModelAttribute로 간주된다.

<br>
<br>
<br>

## HTTP 응답 - 정적 리소스 / 뷰 템플릿
- 정적 리소스 : 웹 브라우저에 정적인 HTML,CSS,JS 를 제공할 때는 정적 리소스를 사용한다.
    - `src/main/resources/static` 정적 리소스의 경로로 `src/main/resources/static/basic/hello-form.html` 처럼 되어있다면 /basic/hello-form.html로 접속하면 해당 리소스가 렌더링된다.

- 뷰 템플릿 : 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 제공한다.
    - 일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만, 다른 것들도 가능하다. 뷰 템플릿이 만들 수 있는 것이라면 뭐든지 가능 하다.
    - 스프링 부트의 기본적인 뷰 템플릿 경로 `src/main/resources/templates`
    - mvcStudy2의 ResponseViewController를 통해 사용 예시 참고

<br>

- 정리
```
String을 반환하는 경우 
- @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.
- @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다.

Void를 반환하는 경우
- @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용한다. 
    - 요청 URL이 response/hello 라면 templates/response/hello.html 가 실행된다. 다만 명확성이 떨어지고 이런 경우도 별로 없어서 권장하지 않는다.

HTTP 메시지
- @ResponseBody , HttpEntity 를 사용하면 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 응답 데이터를 출력할 수 있다.
```

<br>
<br>
<br>

## HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
> HTTP API를 제공하는 경우에 HTML이 아니라 데이터에 전달해야 하므로 HTTP 메시지 바디에 JSON 형식으로 데이터를 실어 보낸다.

- 객체를 통해 JSON 형식으로 보낼 수 있다.
``` java
@ResponseStatus(HttpStatus.OK)  // 상태코드 지정
@ResponseBody
@GetMapping("/response-body-json-v2")
public HelloData responseBodyJsonV2() {
    HelloData helloData = new HelloData();
    helloData.setUsername("aaa");
    helloData.setAge(11);
    return helloData;
}
```


<br>
<br>
<br>


## HTTP 메시지 컨버터
> HTTP 메시지 컨버터는 기존의 뷰 템플릿으로 HTML을 생성하는 대신, **HTTP API (REST API)**와 같이 JSON 형식의 데이터 메시지 바디를 직접 읽거나 쓰기 위해 메시지 본문을 다루는 방식을 말한다. 
-  스프링 부트는 다양한 메시지 컨버터를 제공한다. 대상 클래스 타입과 미디어 타입을 체크해서 사용여부를 결정한다.

<br>

    1. 요청(Request) 메시지 컨버터: 클라이언트로부터 전송된 HTTP 요청의 메시지 본문을 파싱하여 자바 객체로 변환한다. @RequestBody, HttpEntity 를 통해 직접 변환하는 과정을 거치지 않아도 바로 값을 사용할 수 있다.

    2. 응답(Response) 메시지 컨버터: 서버에서 클라이언트로 전송하는 HTTP 응답의 메시지 본문을 자바 객체에서 JSON 형식으로 변환하여 전송한다. @ResponseBody를 사용할 경우 뷰 리졸버 대신 컨버터가 동작하여 body의 문자 내용을 직접 반환한다.
> canRead(), canWrite()를 통해 메시지 컨버터가 해당 클래스, 미디어 타입을 지원하는지 체크하고 read(), write()로 메시지 컨버터를 통해서 메시지를 읽고 쓰는 방식으로 동작한다. HTTP 요청의 Content-Type 헤더를 설정하지 않으면 기본적으로 \*/* 형식으로 모든 미디어 타입으로 간주된다.

    content-type: application/json

    @RequestMapping
    void hello(@RequestBody String data) {}

    예를 들어 이런 상황일 때 요청 데이터는 StringHttpMessageConverter가 동작하여 String값으로 받아오고 응답 데이터는 MappingJackson2HttpMessageConverter가 동작해 json형식으로 나가게 된다.

    기본적인 컨버터 우선 순위
    0 = ByteArrayHttpMessageConverter
    1 = StringHttpMessageConverter 
    2 = MappingJackson2HttpMessageConverter


## ArgumentResolver / ReturnValueHandler
> 요청의 경우 ArgumentResolver 에서 HTTP 메시지 컨버터를 호출해 필요한 객체를 생성하고 응답의 경우 ReturnValueHandler 에서 HTTP 메시지 컨버터를 호출해 응답 결과를 만든다.

<br>

- ArgumentResolver
> 스프링 MVC 패턴에서 보면 애노테이션 기반 컨트롤러는 다양한 파라미터를 사용할 수 있었다. HttpServletRequest , Model 은 물론이고, @RequestParam , @ModelAttribute 같은 애노테이션 그리고
@RequestBody , HttpEntity 같은 HTTP 메시지를 처리하는 부분까지 매우 유연하게 사용할 수 있었는데 이를 처리해주는 것이 ArgumentResolver 이다. 쉽게 말해 Argument Resolver는 컨트롤러 메서드에 전달된 요청의 여러 부분을 분석하고, 필요한 경우 해당 파라미터의 값을 추출하여 제공한다.

- ReturnValueHandler
> 인터페이스는 Spring MVC에서 컨트롤러 메서드의 반환 값을 처리하는 데 사용된다. 이 인터페이스를 구현하는 클래스들은 컨트롤러 메서드가 반환하는 값의 형태를 분석하고, 클라이언트에게 응답으로 제공할 적절한 형태로 변환된다. 앞선 ArgumentResolver와 비슷하게 사용된다고 볼 수 있다.

<BR>

#### 추가로 기능 확장이 필요할 때는 WebMvcConfiurer 검색해보기

<br>
<br>
<br>

## 타임 리프 (thymeleaf)
> HTML과 같은 웹 페이지를 동적으로 표현할 수 있게 도와주는 자바 템플릿 엔진이다. 예를 들면 사용자 정보를 동적으로 웹 페이지에 표시 할때 데이터베이스 같은 저장소에서 데이터를 가져와 웹 페이지에 띄워줄 수 있다.

    특징

    1. 서버 사이드 HTML 렌더링 (SSR) : 백엔드 서버에서 HTML을 동적으로 렌더링 하는데 사용한다.

    2. 내츄럴 템플릿 : 순수 HTML을 최대한 유지하려는 특징이 있다. 웹 브라우저에서 파일을 직접 열어도 내용을 확인 할 수 있다.

    3. 스프링 통합 지원 : 타임리프는 스프링과 통합되어 스프링의 다양한 기능을 편리하게 통합해서 사용할 수 있게 지원한다.

- `<html xmlns:th="http://www.thymeleaf.org">` 기본적으로 라이브러리를 설치하고 해당 선언을 넣어줘야 타임 리프를 사용할 수 있다.

#### 자세한 활용 방법은 `thymeleaf` 프로젝트 확인하기

<br>

- 간단한 사용 예시
``` html
<!--
@Controller
public class ExampleController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "John");
        model.addAttribute("age", 25);
        model.addAttribute("fruits", Arrays.asList("사과", "바나나", "딸기"));
        return "example";
    }
}
 -->

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>타임리프 예제</title>
</head>
<body>
    <h1 th:text="'안녕하세요, ' + ${name} + '님!'">안녕</h1>   
    <!--th:text="|안녕하세요, ${name}님!|" => 리터럴 대체 문법을 사용하여 더하기 없이도 사용 가능 -->

    <a href="item.html" th:href="@{/item}">링크</a>
    <!-- th:href="@{/basic/items/{itemId}(itemId=${item.id})}" URL표현식에서는 변수가 있을 경우 해당 방법이나 리터럴 대체 문법을 사용할 수 있다.-->

    <span th:text="'hello'">
    <!-- 타임리프에서 문자리터럴은 항상 '(작은 따옴표)로 감싸야한다. -->
    <!-- 리터럴 대체로 <span th:text="|hello|"> 로도 사용 가능하다.-->

    <div th:if="${age lt 18}">
        <p>미성년자입니다.</p>
    </div>
    <div th:if="${age ge 18}">
        <p>성인입니다.</p>
    </div>
    
    <ul>
        <li th:each="fruit : ${fruits}" th:text="${fruit}">안녕</li> <!-- th:each를 사용하면 html에서 반복문 사용 가능 ->
    </ul>
</body>
</html>
```
> 컨트롤러에서 모델 데이터를 받아와 사용할 수 있고 타임리프 뷰 탬플릿을 거치면 값을 th를 사용한 값으로 변경한다. 예를 들어 `a href="item.html" th:href="/item"` 이 코드에서 그냥 html로 열면 item.html 로 열리고 타임리프 뷰 탬플릿을 거치면 /item 경로로 열리게 된다. html을 파일로 직접 연다면 th:xxx가 있더라도 웹 브라우저는 th속성을 알지 못해 무시한다.  

#### 핵심은 th:xxx 가 붙은 부분은 서버사이드에서 렌더링 되고 기존 것을 대체 된다. th:xxx가 없다면 기존 html 속성이 그대로 사용된다. 

#### 가끔 vscode에서 스프링 부트 템플릿 html을 작성할 때 자동완성이 안될 수도 있다. 그럴 때는 오른쪽 하단에 언어 모드를 선택해 html로 변경해준다.

참고: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax

<br>
<br>
<br>

## 타임리프와 스프링의 통합
> 타임리프는 스프링 없이도 동작하지만 스프링과의 통합을 위한 다양한 기능을 제공한다. 

- 스프링 통합으로 추가되는 기능들
``` 
- 스프링의 SpringEL 문법 통합

- ${@myBean.doSomething()} 처럼 스프링 빈 호출 지원

- 편리한 폼 관리를 위한 추가 속성

- th:object (기능 강화, 폼 커맨드 객체 선택)

- th:field , th:errors , th:errorclass

- 폼 컴포넌트 기능

- checkbox, radio button, List 등을 편리하게 사용할 수 있는 기능 지원

- 스프링의 메시지, 국제화 기능의 편리한 통합

- 스프링의 검증, 오류 처리 통합

- 스프링의 변환 서비스 통합(ConversionService)
```

- 자세한 기능에 대한 내용은 springForm 폴더안에 있다.

<br>
<br>
<br>

## 리다이렉트
> 리다이렉트는 클라이언트의 요청을 다른 URL로 전달하는 기능이다

- 아래와 같이 간단하게 사용 가능하다.
``` java
@PostMapping("/{itemId}/edit")
public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
    itemRepository.update(itemId, item);
    return "redirect:/basic/items/{itemId}";
}
```

### POST 사용 로직에서 주의할 점
> 상품 등록이나 상품 주문시에 보통 POST를 이용해서 로직을 짠다. 아무 생각없이 로직을 짤 경우 상품 등록 완료 후 웹 브라우저를 새로고침을 한다면 상품이 등록될 것이다. 새로고침 자체가 마지막에 한 행동을 다시 하는 것이기 때문에 마지막에 한 행동인 POST의 서버에 데이터를 전송하는걸 반복하게 된다.

- 해결 방법 : 상품 저장 후 뷰 탬플릿으로 이동하는 것이 아닌 상품 상세 화면으로 리다이렉트를 호출해준다.
``` java
// study.itemservice.web.item.basic.BasicItemController 참고
@PostMapping("/add")
public String save(@ModelAttribute Item item,RedirectAttributes redirectAttributes) {
    Item saveItem = itemRepository.save(item);
    redirectAttributes.addAttribute("itemId", saveItem.getId());        
    redirectAttributes.addAttribute("status", true);    // 쿼리 파라미터로 붙어온다.
    // <h2 th:if="${param.status}" th:text="'저장 완료'"></h2> 이런 식으로 참일 경우 추가로 메시지를 넣을 수도 있다.
    // 타임리프에서는 parram을 통해 쿼리 파라미터를 따로 설정하지 않아도 가져올 수 있다.

    return "redirect:/basic/items/{itemId}";    // 좀 전에 redirectAttributes 를 통해 itemId에 넣은 값을 활용할 수 있게 된다.
}
```
> 그냥 +기호 통해서 URL에 변수를 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험하다. redirectAttributes 를 통해서 해결할 수 있다. 

<br>
<br>
<br>


## 메시지 / 국제화

#### 메시지
> 애플리케이션에서 사용되는 모든 텍스트 메시지를 중앙에서 관리하여 효율적으로 업데이트할 수 있다. 예를 들어 화면에 보이는 '상품명' 이라는 단어를 모두 '상품이름' 으로 고치려면 모든 화면들을 찾아가 모두 변경해야한다. 이걸 해결하는 것이 바로 메시지 기능이다.

- messages.properties 라는 메시지 관리용 파일을 만들어 중앙관리 하는 방식이 있다.

- 타임리프의 메시지 표현식 `#{...}` 를 통해 쉽게 스프링의 메시지를 조회 가능하다.
    - `th:text="#{hello.name(${item.itemName})}"` => 파라미터는 이런 식으로 해야한다.

- 파일의 위치는 `/resources/messages.properties` 에 두면 된다.

- 만약 Locale 선택 방식을 변경하려면 LocaleResolver 의 구현체를 변경해서 쿠키나 세션 기반의 Locale 선택 기능을 사용할 수 있다.

- 아래와 같이 메시지 소스를 설정할 수 있다.
```
// application.properties
// 스프링 부트 메시지 소스 기본 값 : spring.messages.basename=messages

spring.messages.basename=messages,config.i18n.messages
```


<br>

#### 국제화 
> 애플리케이션을 다양한 언어로 제공하고 사용자가 원하는 언어로 애플리케이션의 메시지를 표시할 수 있도록 한다.

- 메시지 파일( messages.properties )을 각 나라별로 별도로 관리하면 서비스를 국제화 할 수 있다.

- 타임리프 메시지 작업만 완료해놓으면 알아서 국제화 처리를 해준다. 크롬 설정에서 언어로 들어가서 영어를 맨 위로 올리면 확인할 수 있다.

<br>

#### 기능 구현 예시는 message 폴더 안에 있다.

<br>
<br>
<br>

## 검증 로직
> 사용자 입력 데이터나 외부에서 받은 데이터를 유효성 검사하고 처리하는 과정을 의미한다. 예를 들어 상품을 등록할 때, 상품의 가격을 입력하는 창에서 글자를 입력한다면 오류 화면으로 이동하게 된다. 이렇게 되면 사용자는 폼으로 다시 이동해 처음부터 입력을 해야한다. 따라서 이런 상황에서 사용자의 입력 데이터는 유지한 채로 어떤 오류가 발생했는지 알려줘야 한다.

- 컨트롤러의 중요한 역할 중 하나가 HTTP 요청이 정상인지 검증하는 것이다.
- 오류 코드의 관리는 구체적인 것부터 덜 구체적인 것 순서로 만들어준다.

<br>    

- 클라이언트 검증과 서버 검증
``` 
클라이언트 검증
- 자바스크립트를 통한 검증
- 클라이언트 검증은 조작할 수 있으므로 보안에 취약

서버 검증
- HTTP요청을 받아와 서버쪽 컨트롤러 같은 곳에서 검증
- 서버만으로 검증하면 즉각적인 고객 사용성이 부족
- 둘을 적절히 섞어서 사용하되 최종적으로 서버 검증은 필수
- API방식을 사용하면 API 스펙을 잘 정의해서 검증 오류를 API 응답 결과에 잘 남겨줘야함
```

<br>

- 에러 메시지 우선 순위 `bindingResult.rejectValue("itemName", "required");` 일때
``` 
#Level1
required.item.itemName: 상품 이름은 필수 입니다.

#Level2
required: 필수 값 입니다.

# 더 디테일한 메시지 코드가 우선순위가 높다.
```

<br>

#### BindingResult
> 데이터 바인딩 시 발생하는 유효성 검증 오류를 담는 통합 인터페이스이다. 객체안에 필드 값들의 타입 같은 것들이 올바르게 바인딩 됬는지 검사해준다. 또한 FieldError, ObjectError를 한데 묶어(List) Model에 주입시켜준다. 쉽게 생각하면 오류를 담아놓는 저장소이다.

``` 
// 필드 오류
public FieldError(String objectName, String field, String defaultMessage) {}

// 글로벌 오류
public ObjectError(String objectName, String defaultMessage) {}

// int 필드에 문자열이 담길 경우
- BindingResult 가 없으면 400 오류가 발생하면서 컨트롤러가 호출되지 않고, 오류 페이지로 이동한다.

- BindingResult 가 있으면 오류 정보( FieldError )를 BindingResult 에 담아서 컨트롤러를 정상 호출한다.
```
> @ModelAttribute의 경우에는 잘못된 타입입력으로인해 바인딩 자체가 안되더라도 BindingResult에 담기고 Controller가 호출되는반면 @RequestBody의 경우에는
바인딩자체가 호출이 안될때에는 Controller자체가 호출이 안된다.

<br>

#### WebDataBinder / @Validated
> 검증 로직을 분리해서 사용할 때 WebDataBinder에 검증기를 추가하면 해당 컨트롤러에서는 검증기를 자동으로 적용할 수 있다. 검증기가 여러개라면 supports를 통해 구분하여 사용한다.

- 스프링 부트 Validation 라이브러리를 사용하면 검증기가 없어도 자동으로 Bean Validator를 인지하고 스프링에 통합된다.
    - 다만 글로벌 검증기를 등록해놨다면 제거해놔야 Bean Validator가 동작한다.

``` java
@InitBinder
public void init(WebDataBinder dataBinder) {
    log.info("init binder {}", dataBinder);
    dataBinder.addValidators(itemValidator);
}

@PostMapping("/add")
public String addItemV6(@Validated @ModelAttribute Item item,BindingResult bindingResult, RedirectAttributes redirectAttributes) {

    // 검증에 실패하면 다시 입력으로
    if(bindingResult.hasErrors()){  
        log.info("ERROR : {}",bindingResult);
        return "validation/v2/addForm";
    }  

    // 성공 로직
    Item savedItem = itemRepository.save(item);
    redirectAttributes.addAttribute("itemId", savedItem.getId());
    redirectAttributes.addAttribute("status", true);
    return "redirect:/validation/v2/items/{itemId}";
}
```

- 글로벌 설정이 필요할 때 (모든 컨트롤러에 적용)
``` java
@SpringBootApplication
public class ItemServiceApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }
    @Override
    public Validator getValidator() {
        return new ItemValidator();
    }
}
```
<br>

#### Bean Validation
> 대부분의 검증 로직은 빈 값인지, 특정 크기를 넘는지와 같은 경우가 많다. 따라서 이러한 검증 로직을 모든 프로젝트에 적용할 수 있도록 애노테이션으로 만들어 놓은 것이 Bean Validation이다.

``` java
@Data
@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")    // jdk버전을 낮추거나 자바스크립트 엔진을 써야함
public class Item {
    private Long id;

    @NotBlank(message = "공백 X")
    private String itemName;

    @NotNull @Range(min=1000, max = 1000000)
    private Integer price;

    @NotNull @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
```
    Bean Validation - 한계

    - 데이터를 등록할 때와 수정할 때는 요구사항이 다를 수 있다.

    - 등록시에는 id 에 값이 없어도 되지만, 수정시에는 id 값이 필수여서 @NotNull을 추가해버리면 등록시에는 id가 없기 때문에 동작을 하지 않는다.

    - 이러한 등록과 수정의 요구사항이 다를 경우 충돌이 일어난다.

    해결 방법

    - BeanValidation의 groups 기능을 사용한다.

    - Item을 직접 사용하지 않고, ItemSaveForm, ItemUpdateForm 같은 폼 전송을 위한 별도의 모델 객체를 만들어서 사용한다.

    - groups 기능은 실제 잘 사용되지는 않는데, 그 이유는 실무에서는 주로 다음에 등장하는 등록용 폼 객체와 수정용 폼 객체를 분리해서 사용하기 때문이다.


<br>

#### 타임리프 스프링 검증 오류 통합 기능
```
#fields : #fields 로 BindingResult 가 제공하는 검증 오류에 접근할 수 있다.

th:errors : 해당 필드에 오류가 있는 경우에 태그를 출력한다. th:if 의 편의 버전이다.

th:errorclass : th:field 에서 지정한 필드에 오류가 있으면 class 정보를 추가한다.
```

<br>

#### 기능 구현 예시는 validation 폴더 안에 있다.

#### http://hibernate.org/validator/ => 검증 애노테이션 모음





<br>
<br>
<br>

## 기타

<br>

#### /WEB-INF
>이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출하는 것이다.

<br>
<br>

#### request.setAttribute()
> 해당 기능을 사용해 request객체에 데이터를 보관해서 뷰에 전달할 수 있다. request를 데이터를 보관하는 Model로 사용할 수 있다.

<br>
<br>


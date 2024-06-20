# 스프링 DB 접근

<br>
<br>
<br>

## JDBC
> 과거에는 프로젝트의 데이터베이스 종류가 바뀌면 코드도 다 바꿔야했는데 JDBC의 등장으로 JDBC의 표준 인터페이스를 사용한다면 데이터베이스가 바뀌어도 기존의 코드가 유지가 된다. 다만 코드는 유지가 되더라도 SQL은 해당 데이터베이스에 맞게 변경해야 한다. 이 부분은 뒤에 JPA를 통해 일정 부분 해결할 수 있다.

- 기본적인 동작 과정
```
1. 커넥션 연결: 주로 TCP/IP를 사용해서 커넥션을 연결한다.

2. SQL 전달: 애플리케이션 서버는 DB가 이해할 수 있는 SQL을 연결된 커넥션을 통해 DB에 전달한다.

3. 결과 응답: DB는 전달된 SQL을 수행하고 그 결과를 응답한다. 애플리케이션 서버는 응답 결과를 활용한다.
```

<br>

#### DriveManager 사용 코드
``` java
// DB접속정보 저장
public abstract class ConnectionConst { 
    public static final String URL ="db주소";
    public static final String USERNAME ="사용자명";
    public static final String PASSWORD="";
}

// DB연결
public static Connection getConnection(){
    try {
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD); //DriverManager는 라이브러리 등록된 드라이버 목록을 자동으로 인식하고 커넥션 획득할 수 있는지 확인
        log.info("get connection={}, class={}",connection,connection.getClass());
        return connection;
    } catch (SQLException e) {
        throw new IllegalStateException(e);
    }
}

// 데이터소스 인터페이스 사용 버전
@Test 
void dataSourceDriverManager() throws SQLException {
    // 항상 새로운 커넥션 획득, 설정과 사용의 분리
    DataSource datasource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    useDataSource(datasource);
}

private void useDataSource(DataSource dataSource) throws SQLException {
    Connection con1 = dataSource.getConnection();
    Connection con2 = dataSource.getConnection();
    log.info("connection={}, class={}", con1, con1.getClass());
    log.info("connection={}, class={}", con2, con2.getClass());
}
```

<br>
<br>
<br>

## 데이터소스
> 스프링 데이터소스는 데이터베이스와의 연결을 관리하기 위한 인터페이스를 제공한다. 커넥션을 획득하는 방법을 추상화해놓았다. JDBC DriverManager를 사용해 커넥션을 만들지 HikariCP와 같은 커넥션 풀을 사용할지는 데이터소스 인터페이스를 통해 구현체만 만들어주면 된다. 


<br>
<br>
<br>


## 커넥션 풀
> 데이터베이스와의 연결을 관리하는 기술이다. 커넥션을 매번 새로 만드는 것은 복잡하고 시간이 많이 소모되어 사용자가 사용할 때 응답속도가 지연되는 결과로 이어진다. 이것을 해결하기 위해 필요한 만큼의 커넥션을 미리 확보해 풀에 보관하여 관리한다. 쓰레드 풀과 비슷한 관리 방식이다. 실무에서 기본으로 사용한다.

<br>

- HikariDataSource 사용
``` java
@Test
void dataSourceConnectionPool() throws SQLException, InterruptedException {
    //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target)
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    dataSource.setMaximumPoolSize(10);
    dataSource.setPoolName("MyPool");
    useDataSource(dataSource);
    Thread.sleep(1000); //커넥션 풀에서 커넥션 생성 시간 대기
}    
```

<br>
<br>
<br>

## 트랜잭션
> 데이터베이스에서 하나의 거래를 안전하게 처리하도록 보장해는 것이다. 데이터베이스 관리 시스템(DBMS)에서 수행되는 작업의 단위를 가리킨다. 이는 데이터베이스 상태를 변경하는 하나 이상의 연산(쿼리 실행, 데이터 수정 등)으로 구성된다.
```
예를 들면 A의 5000원을 B에게 계좌이체할 때 A의 잔고는 5000원이 감소되고 B의 잔고는 5000원이 증가한다. 이때 2개의 작업이 성공적으로 수행이 되어야 하고, 만약 2번째 단계에서 오류가 나서 A의 잔고만 5000원이 발생하면 심각한 문제가 발생한다.
하지만 데이터베이스가 제공하는 트랙잭션 기능을 사용하면 1,2단계가 모두 성공해야 저장하고
하나라도 실패하면 거래전의 상태로 돌아갈 수 있다.

=> 이때 성공해서 데이터베이스에 반영하는 것을 커밋, 실패해서 되돌리는 것을 롤백이라고 한다.
```

<br>

### 트랜잭션 ACID
> 트랜잭션은 ACID라 하는 원자성(Atomicity), 일관성(Consistency), 격리성(Isolation), 지속성(Durability)을 보장해야 한다

```
1. 원자성: 트랜잭션 내에서 실행한 작업들은 마치 하나의 작업인 것처럼 모두 성공 하거나 모두 실패해야 한다.

2. 일관성: 모든 트랜잭션은 일관성 있는 데이터베이스 상태를 유지해야 한다. 예를 들어 데이터베이스에서 정한 무결성 제약 조건을 항상 만족해야 한다. 

3. 격리성: 동시에 실행되는 트랜잭션들이 서로에게 영향을 미치지 않도록 격리한다. 예를 들어 동시에 같은 데이터를 수정하지 못하도록 해야 한다. 격리성은 동시성과 관련된 성능 이슈로 인해 트랜잭션 격리 수준(Isolation level)을 선택할 수 있다. 

4. 지속성: 트랜잭션을 성공적으로 끝내면 그 결과가 항상 기록되어야 한다. 중간에 시스템에 문제가 발생해도 데이터베이스 로그 등을 사용해서 성공한 트랜잭션 내용을 복구해야 한다
```

<BR>

### 트랜잭션 사용법
```
1. 데이터 변경 쿼리를 실행하고 데이터베이스에 그 결과를 반영하려면 커밋 명령어인 commit 을 호출하고, 결과를 반영하고 싶지 않으면 롤백 명령어인 rollback 을 호출하면 된다.

2. 커밋을 호출하기 전까지는 임시로 데이터를 저장하는 것이다. 따라서 해당 트랜잭션을 시작한 세션(사용자)에게만 변경 데이터가 보이고 다른 세션(사용자)에게는 변경 데이터가 보이지 않는다.

3. 등록, 수정, 삭제 모두 같은 원리로 동작한다. 앞으로는 등록, 수정, 삭제를 간단히 변경이라는 단어로 표현하겠다


# 예시

- 세션1은 트랜잭션을 시작하고 신규 회원1, 신규 회원2를 DB에 추가했다. 아직 커밋은 하지 
않은 상태이다. 

- 새로운 데이터는 임시 상태로 저장된다.

- 세션1은 select 쿼리를 실행해서 본인이 입력한 신규 회원1, 신규 회원2를 조회할 수 있다.

- 세션2는 select 쿼리를 실행해도 신규 회원들을 조회할 수 없다. 왜냐하면 세션1이 아직 커밋을 하지 않았기 때문이다. 

- 커밋을 한다면 임시 데이터가 실제 데이터베이스에 반영되고 다른 세션에서도 조회 가능하다.

- 롤백을 한다면 임시데이터가 날라가 트랜잭션 시작 전으로 돌아간다.
```

<br>

### 자동 커밋 / 수동 커밋
> 보통 자동 커밋 모드가 기본으로 설정된 경우가 많기 때문에 수동 커밋 모드로 설정하는 것을 트랜잭션을 시작한다고 표현할 수 있다.

<br>

- 자동 커밋: 쿼리를 실행한 후 자동으로 커밋을 호출한다. 하나하나 실행할 때마다 자동으로 커밋이 되므로 트랜잭션 기능을 제대로 사용할 수 없다.
``` sql
set autocommit true; //자동 커밋 모드 설정
insert into member(member_id, money) values ('data1',10000); //자동 커밋
insert into member(member_id, money) values ('data2',10000); //자동 커밋
```

<br>

- 수동 커밋: 쿼리를 실행한 후 수동으로 커밋을 호출한다. 트랜잭션 기능을 수행하려면 자동 커밋을 끄고 수동 커밋을 사용한다.
``` sql
set autocommit false; //수동 커밋 모드 설정
insert into member(member_id, money) values ('data3',10000);
insert into member(member_id, money) values ('data4',10000);
commit; //수동 커밋
```


<BR>

### DB 락
> 세션1이 트랜잭션을 시작하고 데이터를 수정하는 동안 아직 커밋을 하지 않았는데 세션2에서 동시에 같은 데이터를 수정하게 되면 문제가 발생한다. 바로 트랜잭션의 원자성이 깨지는 것이다. 따라서 이러한 문제를 해결하기 위해서 하나의 세션이 트랜잭션 하는 동안 다른 세션에서 해당 데이터를 수정하지 못하도록 막는 것을 DB 락이라고 한다.

<BR>

- DB 락 과정
```
세션1은 A라는 데이터를 500으로 변경하려 하고 세션2는 A라는 데이터를 1000으로 변경하려 한다.

1. 먼저 요청한 세션1이 A데이터의 락을 획득하고 트랜잭션을 시작한다.

2. 세션2는 A라는 데이터가 남아있지 않으므로 세션1의 트랜잭션이 끝날때까지 대기한다. (시간이 오래걸리면 시간초과 오류) 

3. 세션1의 트랜잭션이 종료되면 A데이터의 락을 반납한다.

4. 대기하던 세션2가 A데이터의 락을 획득하고 트랜잭션 수행 후 똑같이 락을 반납한다.
```
> `SET LOCK_TIMEOUT 60000 :` 락 획득 제한시간 설정 (60초)

<br>

- 조회 : 일반적으로 데이터베이스 조회는 락을 획득하지 않고 바로 조회할 수 있다.
    - 조회하는 동안 락을 획득하고 싶을 경우 `select for update`를 사용한다. 이 경우도 트랜잭션이 종료되면 락을 반납한다. (커밋이나 롤백을 하면 종료)
    - 예를 들어서 애플리케이션 로직에서 memberA 의 금액을 조회한 다음에 이 금액 정보로 애플리케이션에서 어떤
계산을 수행한다. 그런데 이 계산이 돈과 관련된 매우 중요한 계산이어서 계산을 완료할 때 까지 memberA 의 금
액을 다른곳에서 변경하면 안된다. 이럴 때 조회 시점에 락을 획득하면 된다.

<br>


## 트랜잭션 문제점
```
1. JDBC 구현 기술이 서비스 계층에 누수되는 문제
- 트랜잭션을 적용하기 위해 JDBC 구현 기술이 서비스 계층에 누수되었다.
- 서비스 계층은 순수해야 한다. 구현 기술을 변경해도 서비스 계층 코드는 최대한 유지할 수 있어야 한다. (변화에 대응)
- 그래서 데이터 접근 계층에 JDBC 코드를 다 몰아두는 것이다.
- 물론 데이터 접근 계층의 구현 기술이 변경될 수도 있으니 데이터 접근 계층은 인터페이스를 제공하는것이 좋다.
- 서비스 계층은 특정 기술에 종속되지 않아야 한다. 지금까지 그렇게 노력해서 데이터 접근 계층으로 JDBC 관련 코드를 모았는데, 트랜잭션을 적용하면서 결국 서비스 계층에 JDBC 구현 기술의 누수가 발생했다.


2. 트랜잭션 동기화 문제
- 같은 트랜잭션을 유지하기 위해 커넥션을 파라미터로 넘겨야 한다.
- 이때 파생되는 문제들도 있다. 똑같은 기능도 트랜잭션용 기능과 트랜잭션을 유지하지 않아도 되는 기능으로 분리해야 한다.

3. 트랜잭션 적용 반복 문제
트랜잭션 적용 코드를 보면 반복이 많다. try , catch , finally ...
```

<br>

## 스프링 트랜잭션 추상화
> 트랜잭션의 추상화는 위에 나오는 트랜잭션의 문제를 해결하기 위해 인터페이스를 만들어 트랜잭션을 추상화하는 것이다. 그리고 스프링은 이미 이러한 트랜잭션의 추상화 기술을 제공하고 있다.
- JDBC, JPA, 기타 등등 스프링에서 이미 만들어둔 트랜잭션 구현체를 골라 사용하면 된다.
- 이러한 스프링의 인터페이스와 구현체를 포함해 트랜잭션 매니저라 부른다.

<br>


## 스프링 트랜잭션 동기화
> 트랜잭션 매니저에서는 동기화 기능도 제공한다. 이것은 쓰레드 로컬을 사용해서 커넥션을 동기화 해준다. 이를 통해 이전처럼 파라미터로 커넥션을 전달하는 문제를 해결할 수 있다.

<br>

- 동작 과정
```
1. 트랜잭션을 시작하려면 커넥션이 필요하다. 트랜잭션 매니저는 데이터소스를 통해 커넥션을 만들고 트랜잭션을시작한다. 

2. 트랜잭션 매니저는 트랜잭션이 시작된 커넥션을 트랜잭션 동기화 매니저에 보관한다.

3. 리포지토리는 트랜잭션 동기화 매니저에 보관된 커넥션을 꺼내서 사용한다. 따라서 파라미터로 커넥션을 전달하지 않아도 된다. DataSourceUtils.releaseConnection()

4. 트랜잭션이 종료되면 트랜잭션 매니저는 트랜잭션 동기화 매니저에 보관된 커넥션을 통해 트랜잭션을 종료하고, 커넥션도 닫는다.
```
> 쓰레드 로컬을 사용하면 각각의 쓰레드마다 별도의 저장소가 부여된다. 따라서 해당 쓰레드만 해당 데이터에 접근할 수 있다.

- `DataSourceUtils.getConnection()` 트랜잭션 동기화 매니저가 관리하는 커넥션이 있으면 해당 커넥션을 반환하고 없으면 새로운 커넥션 생성 후 반환한다.
- `DataSourceUtils.releaseConnection()` 트랜잭션을 사용하기 위해 동기화된 커넥션은 커넥션을 닫지 않고 그대로 유지하고 트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 해당 커넥션을 닫는다.


<br>

## 트랜잭션 템플릿
> 트랜잭션을 사용하는 로직에서 반복되는 부분을 템플릿 콜백 패턴을 활용해서 관리한다. 스프링은 TransactionTemplate 라는 템플릿 클래스를 제공한다.

``` java
public class TransactionTemplate {
    private PlatformTransactionManager transactionManager;
    public <T> T execute(TransactionCallback<T> action){..}
    void executeWithoutResult(Consumer<TransactionStatus> action){..}
}
```

<br>

## 트랜잭션 AOP
> 아직까지 서비스 계층에는 순수하게 비즈니스 로직만 남아있지는 않다. 이것을 해결하기 위해 스프링 AOP를 통해 프록시를 도입하면 문제를 해결할 수 있다. @Transactional 을 사용하
면 스프링이 AOP를 사용해서 트랜잭션을 편리하게 처리해준다. 쉽게 생각하면 비즈니스 로직과 트랜잭션 코드를 분리하는 것이다.

- 이러한 방식으로 선언적 트랜잭션 관리라고 한다. 전까지 했던 방식은 프로그래밍 방식 트랜잭션 관리라고 한다.

<br>

- 스프링이 제공하는 트랜잭션 AOP
```
1. 스프링이 제공하는 AOP 기능을 사용하면 프록시를 매우 편리하게 적용할 수 있다. 스프링 핵심 원리 - 고급편을 통해 AOP를 열심히 공부하신 분이라면 아마도 @Aspect , @Advice , @Pointcut 를 사용해서 트랜잭션 처리용 AOP를 어떻게 만들지 머리속으로 그림이 그려질 것이다.

2. 물론 스프링 AOP를 직접 사용해서 트랜잭션을 처리해도 되지만, 트랜잭션은 매우 중요한 기능이고, 전세계 누구나 다 사용하는 기능이다. 스프링은 트랜잭션 AOP를 처리하기 위한 모든 기능을 제공한다. 스프링 부트를 사용하면 트랜잭션 AOP를 처리하기 위해 필요한 스프링 빈들도 자동으로 등록해준다.

3.개발자는 트랜잭션 처리가 필요한 곳에 @Transactional 애노테이션만 붙여주면 된다. 스프링의 트랜잭션 AOP는 이 애노테이션을 인식해서 트랜잭션 프록시를 적용해준다
```
> 스프링이 제공하는 트랜잭션 AOP는 스프링 빈에 등록된 트랜잭션 매니저를 찾아서 사용하기 때문에 트랜잭션 매니저를 스프링 빈으로 등록해두어야 한다


<br>

### 트랜잭션 AOP 주의사항
> `@Transactional` 을 사용하면 스프링의 트랜잭션 AOP가 적용된다. 트랜잭션 AOP는 기본적으로 프록시 방식의 AOP를 사용한다. `@Transactional` 을 적용하면 프록시 객체가 요청을 먼저 받아서 트랜잭션을 처리하고, 실제 객체를 호출해준다. 따라서 트랜잭션을 적용하려면 항상 프록시를 통해서 대상 객체(Target)을 호출해야 한다. 만약 프록시를 거치지 않고 대상 객체를 직접 호출하게 되면 AOP가 적용되지 않고, 트랜잭션도 적용되지 않는다

<br>

- 주의1. 프록시 메서드 내부 호출 예시
``` java
@Slf4j
@SpringBootTest
public class InternalCallV1Test {
    @Autowired
    CallService callService;

    @Test
    void printProxy() {
        log.info("callService class={}", callService.getClass());
    }

    @Test
    void internalCall() {
        callService.internal();
    }

    @Test
    void externalCall() {   // 이걸 실행해도 트랜잭션이 동작하지 않는다.
        callService.external();
    }

    @TestConfiguration
    static class InternalCallV1Config {
        @Bean
        CallService callService() {
            return new CallService();
        }
    }

    @Slf4j
    static class CallService {
        public void external() {
            log.info("call external");
            printTxInfo();
            internal(); // 메서드 내부 호출 상황
        }

        @Transactional
        public void internal() {
            log.info("call internal");
            printTxInfo();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }
    }
}
```
```
1. 클라이언트인 테스트 코드는 callService.external() 을 호출한다. 여기서 callService 는 트랜잭션 프록시이다.

2. callService 의 트랜잭션 프록시가 호출된다.

3. external() 메서드에는 @Transactional 이 없다. 따라서 트랜잭션 프록시는 트랜잭션을 적용하지 않는다.

4. 트랜잭션 적용하지 않고, 실제 callService 객체 인스턴스의 external() 을 호출한다.

5. external() 은 내부에서 internal() 메서드를 호출한다. 그런데 여기서 문제가 발생한다. 프록시를 거치지 않고 메서드를 호출해버린다.

=> 해결 방법 : internal() 메서드를 별도의 클래스로 분리하여 해결할 수 있다.
```
> 결론적으로 트랜잭션 AOP는 같은 클래스 내부에서 프록시를 내부호출로 불러오면 동작하지 않지만 별도의 클래스로 분리하여 외부호출한다면 정상 동작한다.


<br>

- 주의2. 스프링의 트랜잭션 AOP 기능은 public 메서드에만 트랜잭션을 적용하도록 기본 설정이 되어있다. 그래서protected , private , package-visible 에는 트랜잭션이 적용되지 않는다.

<br>

- 주의3. 스프링 초기화 시점에는 트랜잭션 AOP가 적용되지 않을 수 있다.
``` java
@Slf4j
@SpringBootTest
public class InitTxTest {

    @Autowired
    Hello hello;

    @Test
    void go() { // 초기화 코드는 스프링이 초기화 시점에 호출한다.
    }

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    static class Hello {

        @PostConstruct // 초기화 코드
        @Transactional
        public void initV1() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx active={}", isActive);
        }

        @EventListener(value = ApplicationReadyEvent.class) // 스프링 컨테이너가 완전히 준비되었을 때 호출
        @Transactional
        public void init2() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init ApplicationReadyEvent tx active={}", isActive);
        }
    }
}
```
> 왜냐하면 초기화 코드가 먼저 호출되고, 그 다음에 트랜잭션 AOP가 적용되기 때문이다. 따라서 초기화 시점에는 해당 메서드에서 트랜잭션을 획득할 수 없다. 가장 좋은 대안은 `@EventListener(value = ApplicationReadyEvent.class)` 를 사용해서 스프링 컨테이너가 완전히 준비되었을 때 메서드를 호출하는 것이다.

<br>
<br>

### 트랜잭션의 옵션

```java
public @interface Transactional {
    String value() default "";
    String transactionManager() default "";
    Class<? extends Throwable>[] rollbackFor() default {};
    Class<? extends Throwable>[] noRollbackFor() default {};
    Propagation propagation() default Propagation.REQUIRED;
    Isolation isolation() default Isolation.DEFAULT;
    int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;
    boolean readOnly() default false;
    String[] label() default {}
 }
 ```

1. rollbackFor
    - 이 옵션을 사용하면 기본 정책에 추가로 어떤 예외가 발생할 때 롤백할 지 지정할 수 있다. 체크 예외라도 롤백하게 할 수 있다.
    - `@Transactional(rollbackFor = Exception.class)`

2. rollbackFor 와 반대이다. 기본 정책에 추가로 어떤 예외가 발생했을 때 롤백하면 안되는지 지정할 수 있다.

3. propagation

4. isolation
    - 트랜잭션 격리 수준을 지정할 수 있다. 기본 값은 데이터베이스에서 설정한 트랜잭션 격리 수준을 사용하는 DEFAULT이다.
    - 애플리케이션 개발자가 트랜잭션 격리 수준을 직접 지정하는 경우는 드물다.

5. timeout
    - 트랜잭션 수행 시간에 대한 타임아웃을 초 단위로 지정한다. 기본 값은 트랜잭션 시스템의 타임아웃을 사용한다.
    - 운영환경에 따라 동작하는 경우도 있고 그렇지 않은 경우도 있기 때문에 꼭 확인하고 사용해야 한다.

6. label
    - 트랜잭션 애노테이션에 있는 값을 직접 읽어서 어떤 동작을 하고 싶을 때 사용할 수 있다. 일반적으로 사용하지 않는다.

7. readOnly
    - 트랜잭션은 기본적으로 읽기 쓰기가 모두 가능한 트랜잭션이 생성된다.
    - readOnly=true 옵션을 사용하면 읽기 전용 트랜잭션이 생성된다. 이 경우 등록, 수정, 삭제가 안되고 읽기 기능만 작동한다.
    - readOnly 옵션을 사용하면 읽기에서 다양한 성능 최적화가 발생할 수 있다.
    - 주로 프레임워크, JDBC드라이버, 데이터베이스에서 적용된다.

<br>

### 스프링부트 리소스 자동 등록
1. 데이터소스: 스프링부트는 데이터소스를 dataSource라는 빈 이름으로 자동으로 등록해준다. 다음과 같이 application.propertie에 설정을 지정할 수 있다.
``` properties
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.username=sa
spring.datasource.password=
```

2. 트랜잭션 매니저: 스프링 부트는 적절한 트랜잭션 매니저(PlatformTransactionManager)를 자동으로 transactionManager라는 빈이름으로 등록해준다.

<br>
<br>


### 트랜잭션 전파
> 외부 트랜잭션이 수행중인데, 내부 트랜잭션이 추가로 수행될 때 어떻게 동작할지 결정하는 것을 트랜잭션 전파(propagation)라 한다. 스프링에서 이 경우 외부 트랜잭션과 내부 트랜잭션을 묶어서 하나의 트랜잭션을 만들어준다. 내부 트랜잭션이 외부 트랜잭션에 참여하는 것이다. 이것이 기본 동작이고, 옵션을 통해 다른 동작방식도 선택할 수 있다. 여기서 내부 트랜잭션과 외부 트랜잭션을 논리 트랜잭션이라고 부르고 이걸 묶는 것을 물리 트랜잭션이라고 부른다. 물리 트랜잭션은 우리가 이해하는 실제 데이터베이스에 적용되는 트랜잭션을 뜻하고 논리 트랜잭션은 트랜잭션 매니저를 통해 트랜잭션을 사용하는 단위이다.

- 예를 들면 트랜잭션 AOP를 사용하는 LogRepository와 MemberRepository가 있는데 각각 `@Transactional` 달아준다면 서로 다른 트랜잭션에서 동작하는 것이고 이 둘을 사용하는 MemberService에만 `@Transactional `를 달아준다면 두개의 트랜잭션을 묶어서 단일 트랜잭션으로 사용하는 것이다. 만약 3개 다 `@Transactional`을 달아주면 하나의 물리 트랜잭션안에 3개의 논리 트랜잭션이 존재하는 것이고 외부 트랜잭션이 시작될 때, 즉 MemberService가 시작될때 신규 트랜잭션이 생기고 나머지 2개는 내부 트랜잭션으로 원래 존재하던 트랜잭션에 참여한다.

<br>

- 원칙
```
모든 논리 트랜잭션이 커밋되어야 물리 트랜잭션이 커밋된다.

하나의 논리 트랜잭션이라도 롤백되면 물리 트랜잭션은 롤백된다.
```

<br>

> 외부 트랜잭션만 물리 트랜잭션을 시작하고 커밋한다. 만약 내부 트랜잭션이 실제 물리 트랜잭션을 커밋하면 트랜잭션이 끝나버리기 때문에, 트랜잭션을 처음 시작한 외부 트랜잭션까지 이어갈 수 없다. 따라서 내부 트랜잭션은 DB 커넥션을 통한 물리 트랜잭션을 커밋하면 안된다.

<br>

- 다양한 트랜잭션 전파 옵션
    - 실무에서는 대부분 REQUIRED 옵션을 사용한다. 그리고 아주 가끔 REQUIRES_NEW 을 사용하고, 나머지는 거의 사용하지 않는다. 그래서 나머지 옵션은 이런 것이 있다는 정도로만 알아두고 필요할 때 찾아보자.
``` m
1. REQUIRED
> 가장 많이 사용하는 기본 설정이다. 기존 트랜잭션이 없으면 생성하고, 있으면 참여한다. 트랜잭션이 필수라는 의미로 이해하면 된다. (필수이기 때문에 없으면 만들고, 있으면 참여한다.)
    - 기존 트랜잭션 없음: 새로운 트랜잭션을 생성한다.
    - 기존 트랜잭션 있음: 기존 트랜잭션에 참여한다.

2. REQUIRES_NEW
> 항상 새로운 트랜잭션을 생성한다.
    - 기존 트랜잭션 없음: 새로운 트랜잭션을 생성한다.
    - 기존 트랜잭션 있음: 새로운 트랜잭션을 생성한다.

3. SUPPORT
> 트랜잭션을 지원한다는 뜻이다. 기존 트랜잭션이 없으면, 없는대로 진행하고, 있으면 참여한다.
    - 기존 트랜잭션 없음: 트랜잭션 없이 진행한다.
    - 기존 트랜잭션 있음: 기존 트랜잭션에 참여한다.

4. NOT_SUPPORT
> 트랜잭션을 지원하지 않는다는 의미이다.
    - 기존 트랜잭션 없음: 트랜잭션 없이 진행한다.
    - 기존 트랜잭션 있음: 트랜잭션 없이 진행한다. (기존 트랜잭션은 보류한다)

5. MANDATORY
> 의무사항이다. 트랜잭션이 반드시 있어야 한다. 기존 트랜잭션이 없으면 예외가 발생한다.
    - 기존 트랜잭션 없음: IllegalTransactionStateException 예외 발생
    - 기존 트랜잭션 있음: 기존 트랜잭션에 참여한다.

6. NEVER
> 트랜잭션을 사용하지 않는다는 의미이다. 기존 트랜잭션이 있으면 예외가 발생한다. 기존 트랜잭션도 허용하지 않는 강한 부정의 의미로 이해하면 된다.
    - 기존 트랜잭션 없음: 트랜잭션 없이 진행한다.
    - 기존 트랜잭션 있음: IllegalTransactionStateException 예외 발생

7. NESTED
    - 기존 트랜잭션 없음: 새로운 트랜잭션을 생성한다.
    - 기존 트랜잭션 있음: 중첩 트랜잭션을 만든다.
    - 중첩 트랜잭션은 외부 트랜잭션의 영향을 받지만, 중첩 트랜잭션은 외부에 영향을 주지 않는다.
    - 중첩 트랜잭션이 롤백 되어도 외부 트랜잭션은 커밋할 수 있다.
    - 외부 트랜잭션이 롤백 되면 중첩 트랜잭션도 함께 롤백된다.
    - JDBC savepoint 기능을 사용한다. DB 드라이버에서 해당 기능을 지원하는지 확인이 필요하다.
    - 중첩 트랜잭션은 JPA에서는 사용할 수 없다.
```


<br>
<br>
<br>

## 데이터베이스 errorCode
> SQLException 내부에 들어있는 errorCode 를 활용하면 데이터베이스에서 어떤 문제가 발생했는지 확인할 수 있다. 다만 데이터베이스마다 에러코드가 다르다.
- H2 예시
    - 23505 : 키 중복 오류
    - 42000 : SQL 문법 오류

<br>
<br>
<br>


## 스프링 데이터 접근 예외 추상화
> 스프링은 데이터 접근 계층에 대한 수십 가지 예외를 정리해서 일관된 예외 계층을 제공한다. 예외의 최고 상위는 org.springframework.dao.DataAccessException 이다. 런타임 예외를 상속 받았기 때문에 스프링이 제공하는 데이터 접근 계층의 모든 예외는 런타임 예외이다. DataAccessException 은 크게 2가지로 구분하는데 NonTransient 예외와 Transient 예외이다. 

- Transient 는 일시적이라는 뜻이다. Transient 하위 예외는 동일한 SQL을 다시 시도했을 때 성공할 가능성이 있다.
    - 예를 들어서 쿼리 타임아웃, 락과 관련된 오류들이다. 이런 오류들은 데이터베이스 상태가 좋아지거나, 락이 풀렸을 때 다시 시도하면 성공할 수 도 있다.
- NonTransient 는 일시적이지 않다는 뜻이다. 같은 SQL을 그대로 반복해서 실행하면 실패한다
    - SQL 문법 오류, 데이터베이스 제약조건 위배 등이 있다.

> 스프링은 데이터베이스에서 발생하는 오류 코드를 스프링이 정의한 예외로 자동으로 변환해주는 변환기를 제공한다. 아래는 사용 예시
``` java
SQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
DataAccessException resultEx = exTranslator.translate("select", sql, e);
```

<br>
<br>
<br>

## JdbcTemplate
> 스프링은 JDBC의 반복 문제를 해결하기 위해 JdbcTemplate 이라는 템플릿을 제공한다.

<br>

- 트랜잭션을 위한 커넥션 동기화는 물론이고, 예외 발생시 스프링 예외 변환기도 자동으로 실행해준다.
``` java
public MemberRepositoryV5(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
}

@Override
public Member save(Member member) {
    String sql = "insert into member(member_id, money) values(?, ?)";
    template.update(sql, member.getMemberId(), member.getMoney());
    return member;
}
```



<br>
<br>
<br>

## 데이터 접근 기술
1. SQL Mapper : 개발자는 SQL만 작성하면 해당 SQL의 결과를 객체로 편리하게 매핑해준다.
    - JdbcTemplate
    - MyBatis

2. ORM 관련 기술 : JPA를 사용하면
기본적인 SQL은 JPA가 대신 작성하고 처리해준다. 개발자는 저장하고 싶은 객체를 마치 자바 컬렉션에 저장하
고 조회하듯이 사용하면 ORM 기술이 데이터베이스에 해당 객체를 저장하고 조회해준다.
    - JPA, Hibernate
    - 스프링 데이터 JPA
    - Queryds


<br>
<br>
<br>

## JdbcTemplate
> jdbc를 편하게 사용할 수 있도록 도와주는 도구로 복잡한 설정이 필요 없고 jdbc의 반복 작업을 해결할 수 있다. 개발자는 SQL을 작성하고, 전달할 파리미터를 정의하고, 응답 값을 매핑하기만 하면 된다. 하지만 동적 SQL을 해결하기 어렵다.

- 자세한 CRUD코드는 item-service-db 프로젝트의 `JdbcTemplateItemRepositoryV1` 참고

<br>

- 라이브러리 추가
``` java
//JdbcTemplate 추가
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
//H2 데이터베이스 추가
runtimeOnly 'com.h2database:h2'
```

<br>

### JdbcTemplate - 이름 지정 파라미터 
> JdbcTemplate을 기본으로 사용하면 파라미터를 순서대로 바인딩 한다. 하지만 누군가 sql 코드의 바인딩 순서를 변경한다면 치명적인 오류가 생기게 된다. JdbcTemplate은 이런 문제를 보완하기 위해 NamedParameterJdbcTemplate 라는 이름을 지정해서 파라미터를 바인딩 하는 기능을 제공한다. 파라미터를 전달하려면 Map 처럼 key , value 데이터 구조를 만들어서 전달해야 한다.
여기서 key 는 :파리이터이름 으로 지정한, 파라미터의 이름이고 , value 는 해당 파라미터의 값이 된다.

<br>

- 이름 지정 바인딩에서 자주 사용하는 파라미터의 종류는 크게 3가지가 있다.
```
- Map

- SqlParameterSource
    - MapSqlParameterSource
    - BeanPropertySqlParameterSource
```
> 데이터 객체에 필요한 정보가 모두 들어있다면 BeanPropertySqlParameterSource가 편하고 없는게 있다면 MapSqlParameterSource를 사용해야 한다. 예를 들어 ItemUpdateDto 에는 itemId 가 없다. 따라서 BeanPropertySqlParameterSource 를 사용할 수 없고, 대신에 MapSqlParameterSource 를 사용했다

- 자세한 CRUD코드는 item-service-db 프로젝트의 `JdbcTemplateItemRepositoryV2` 참고

<br>

### JdbcTemplate - SimpleJdbcInsert
> JdbcTemplate은 INSERT SQL를 직접 작성하지 않아도 되도록 SimpleJdbcInsert 라는 편리한 기능을 제공한다.

- SimpleJdbcInsert
``` java
this.jdbcInsert = new SimpleJdbcInsert(dataSource)
 .withTableName("item") // 데이터를 저장할 테이블 명을 지정한다
 .usingGeneratedKeyColumns("id");   //  key 를 생성하는 PK 컬럼 명을 지정한다.
// .usingColumns("item_name", "price", "quantity");  INSERT SQL에 사용할 컬럼을 지정한다. 생략 가능
```

- 자세한 CRUD코드는 item-service-db 프로젝트의 `JdbcTemplateItemRepositoryV3` 참고

<br>
<br>
<br>




## JPA (Java Persistence API)
> 자바 진영의 ORM 기술 표준으로 SQL문을 직접 작성하지 않고 객체와 DB간의 매핑을 도와주는 기술이다. JPA를 사용하면 사용자가 작성한 엔티티(Entity) 클래스와 애노테이션(Annotation)을 분석하여 해당 엔티티를 데이터베이스의 테이블에 매핑하고, 사용자가 요청한 작업에 대한 SQL 쿼리를 자동으로 생성한다.

- ORM :  Object-relational mapping(객체 관계 매핑)의 약자로 객체는 객체대로 관계형 DB는 관계형 DB대로 설계하도록 중간에서 매핑해주는 기술이다.

<br>

- JPA의 CRUD
```
• 저장: jpa.persist(member)

• 조회: Member member = jpa.find(memberId)

• 수정: member.setName(“변경할 이름”)

• 삭제: jpa.remove(member)
```

<br>

### JPA 사용
> 시작하기전에 먼저 `implementation 'org.springframework.boot:spring-boot-starter-data-jpa'` 라이브러리를 추가해준다.

- 객체와 테이블 매핑
``` java
@Data
@Entity // JPA가 사용하는 객체라는 뜻
public class Item {
    // @Id : 테이블의 PK와 해당 필드를 매핑한다 
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : PK 생성 값을 데이터베이스에서 생성하는 IDENTITY 방식을 사용한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "item_name",length = 10) // @Column : 객체의 필드를 테이블의 컬럼과 매핑한다
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() { // JPA는 기본생성자가 필수이다.
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
```

<br>

- 기능 작성
``` java
@Slf4j
@Repository
@Transactional // JPA에서 데이터 변경할때는 항상 트랜잭션 안에서 이루어져야 한다.
public class JpaItemRepository implements ItemRepository {

    // JPA의 모든 동작은 엔티티 매니저를 통해서 이루어진다. 엔티티 매니저는 내부에 데이터소스를 가지고 있고, 데이터베이스에 접근할 수 있다
    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item); // insert쿼리문을 생성하고 id도 자동으로 생성해준다.
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId); // 객체를 찾아와서 바꾸기만 해도 변경된걸 기반으로 업데이트 쿼리를 날린다.
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String jpql = "select i from Item i"; // jpql문법은 sql과 거의 비슷하지만 테이블 대상이 아닌 엔티티 객체를 대상으로 한다.

        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();

        // 여전히 동적쿼리를 만들기 쉽지 않음
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            jpql += " where";
        }
        boolean andFlag = false;
        if (StringUtils.hasText(itemName)) {
            jpql += " i.itemName like concat('%',:itemName,'%')";
            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                jpql += " and";
            }
            jpql += " i.price <= :maxPrice";
        }
        log.info("jpql={}", jpql);
        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        if (StringUtils.hasText(itemName)) {
            query.setParameter("itemName", itemName);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }
        return query.getResultList();
    }
}
```

<br>

- 레포지토리 등록
``` java
@Configuration
public class JpaConfig {
    private final EntityManager em;

    public JpaConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(em);
    }
}
```

<BR>

> `@Repository`는 컴포넌트 스캔의 대상일 뿐 아니라 해당 애노테이션이 붙은 클래스는 예외 변환 AOP의 적용 대상이 된다. 스프링과 JPA를 함께 사용하는 경우 스프링은 JPA 예외 변환기(PersistenceExceptionTranslator)를 등록한다. 예외 변환 AOP 프록시는 JPA 관련 예외가 발생하면 JPA 예외 변환기를 통해 발생한 예외를 스프링 데이터 접근 예외로 변환한다.

<br>


### 스프링 데이터 JPA
> 스프링 데이터 JPA는 기존 JPA를 편리하게 사용하도록 도와주는 라이브러리이다. 따라서 JPA를 어느정도 숙지하고 있어야 원할하게 사용할 수 있다. 마치 스프링부트와 스프링의 관계와 비슷하다. 자신이 작성한 JPQL이 어떤 쿼리로 생성될지 이해해야 한다.

- 주요기능
    - 공통 인터페이스 기능 : JpaRepository 인터페이스를 통해서 기본적인 CRUD 기능 제공한다. 스프링 데이터 JPA는 리포지토리 인터페이스를 구현하는 프록시 객체를 동적으로 생성한다.
        
    - 쿼리 메서드 기능 : 인터페이스에 메서드만 적어두면, 메서드 이름을 분석해서 쿼리를 자동으로 만들고 실행해주는 기능을 제공한다. 규칙에 따라 작성해야 한다.

<br>

- 적용 1
    - `implementation 'org.springframework.boot:spring-boot-starter-data-jpa'` 라이브러리 추가
``` java
public interface SpringDataJpaItemRepository extends JpaRepository<Item,Long> { // 기본적인 CRUD는 인터페이스 상속받아서 이미 완성
    List<Item> findByItemNameLike(String itemName); // 상품명이 같은거만 찾기
    List<Item> findByPriceLessThanEqual(Integer price); // 해당 가격 이하만 찾기

    // 쿼리 메서드
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName);

    // 쿼리 직접 실행
    @Query("select i from Item where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName,@Param("price") Integer price); // @Param 꼭 넣어줘야 한다.
}
```

<br>

- 적용 2
``` java
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaItemRepositoryV2 implements ItemRepository {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = repository.findById(itemId).orElseThrow(); // orElseThrow()는 Optional 객체에 저장된 값이 null인 경우 예외를
                                                                   // 발생시키는 역할
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {    // 여전히 동적쿼리는 복잡하다. 뒤에 나오는 querydsl로 해결한다.
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        if (StringUtils.hasText(itemName) && maxPrice != null) {
            // return repository.findByItemNameLikeAndPriceLessThanEqual("%" + itemName +// "%", maxPrice);
            return repository.findItems("%" + itemName + "%", maxPrice);
        } else if (StringUtils.hasText(itemName)) {
            return repository.findByItemNameLike("%" + itemName + "%");
        } else if (maxPrice != null) {
            return repository.findByPriceLessThanEqual(maxPrice);
        } else {
            return repository.findAll();
        }
    }
}

```

<br>
<br>
<br>

## 테스트용 데이터베이스 분리
> 로컬에서 사용하는 애플리케이션 서버와 테스트에서 같은 데이터베이스를 사용하면 로컬에 사용되는 데이터때문에 테스트에서 문제가 발생할 수 있다. 이런 문제를 해결하려면 테스트를 다른 환경과 철저하게 분리해야 한다.

<br>

- 테스트의 중요한 원칙
```
테스트는 다른 테스트와 격리해야 한다.

테스트는 반복해서 실행할 수 있어야 한다.
```

<br>

1. 데이터베이스(H2) 분리
    - jdbc:h2:tcp://localhost/~/test local에서 접근하는 서버 전용 데이터베이스
    - jdbc:h2:tcp://localhost/~/testcase test 케이스에서 사용하는 전용 데이터베이스
> 다만 이렇게 분리해도 여전히 테스트를 한번 실행하게 되면 데이터가 축적되어 반복해서 테스트를 돌리지 못하게 된다. 이때 도움이 되는 것이 트랜잭션의 롤백이다.


<br>


2. 트랜잭션 롤백 : 테스트 실행 전에 트랜잭션을 시작하고 끝날 때 롤백을 함으로 써 데이터가 축적되지 않게 해준다.
``` java
@BeforeEach
void before(){
    status = transactionManager.getTransaction(new DefaultTransactionDefinition());
}

@AfterEach
void afterEach() {
    transactionManager.rollback(status);
}
```
> 위에 있는 방식을 스프링에서는 @Transactional만 넣으면 자동으로 해준다. @Transactional 이 테스트에 있으면 스프링은 테스트를 트랜잭션 안에서 실행하고, 테스트가 끝나면 트랜잭션을 자동으로 롤백시켜 버린다. 하지만 강제로 커밋을 하고 싶을 때가 있을 수 있다. 이때는 @Commit을 사용해준다.

<br>
<br>
<br>

## 임베디드 모드 DB
> H2 데이터베이스는 자바로 개발되어 있고, JVM안에서 메모리 모드로 동작하는 특별한 기능을 제공한다. 애플리케이션을 실행할 때 H2 데이터베이스도 해당 JVM 메모리에 포함해서 함께 실행할 수 있다. 

- 이로써 h2데이터베이스를 켜지않고 테스트를 할 수 있고 종료될때 알아서 데이터가 사라진다.
- 스프링부트를 사용한다면 데이터베이스의 별다른 설정이 없을 시 임베디드 데이터베이스를 사용하게 된다. 
    - application.properties의 설정정보가 없을 경우

<br>


- 메모리 DB는 애플리케이션이 종료될 때 함께 사라지기 때문에, 애플리케이션 실행 시점에 데이터베이스 테이블도 새로 만들어주어야 한다. 스프링 부트는 SQL 스크립트를 실행해서 애플리케이션 로딩 시점에 데이터베이스를 초기화하는 기능을 제공한다. `test/resources/schema.sql` 에 작성해주면 된다.
``` sql
drop table if exists item CASCADE;
create table item
(
 id bigint generated by default as identity,
 item_name varchar(10),
 price integer,
 quantity integer,
 primary key (id)
);
```

<br>
<br>
<br>

## QueryDSL
> JPA, MongoDB,SQL 같은 기술들을 위해 type-safe SQL을 만드는 프레임워크이다. type-safe SQL는 컴파일 시점에 타입 관련된 오류를 방지하게 해준다. Querydsl로 복잡한 조회 기능(복잡한 쿼리나 동적쿼리)을 보완할 수 있다. 단순한 경우는 SpringDataJPA로 복잡한 경우는 Querydsl을 사용한다.

<br>

- 라이브러리 추가
``` java
implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta' 
annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
annotationProcessor "jakarta.annotation:jakarta.annotation-api"
annotationProcessor "jakarta.persistence:jakarta.persistence-api"

clean { //자동 생성된 Q클래스 gradle clean으로 제거
    delete file('src/main/generated')
}
```
- 라이브러리 추가 후 작업
1. Gradle -> Tasks -> build -> clean 
2. Gradle -> Tasks -> other -> compileJava 
3. build -> generated -> sources -> annotationProcessor -> java/main 하위에 Q 타입이 생성된다. 
4. `gradle clean` 을 수행하면 build 폴더 자체가 삭제된다. 따라서 별도의 설정은 없어도 된다. 

    - 프로젝트를 실행했을 때도 Q 타입이 생성된다.
    - querydsl gradle 로 검색하면 본인 환경에 맞는 대안을 금방 찾을 수 있다.


<br>
<br>

- Repository에 QueryDsl 적용하기
``` java
@Repository
@Transactional
public class JpaItemRepositoryV3 implements ItemRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);   // QueryDsl을 사용하기 위한 작업
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        List<Item> result = query
                .select(item)
                .from(item)
                .where(likeItemName(itemName), maxPrice(maxPrice))  // 누가봐도 이해하기 쉽게 메서드로 추출하여 사용
                .fetch();
        return result;
    }

    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}
```





<br>
<br>
<br>

## 기타

### DTO(data transfer object)
> 기능은 없고 데이터를 전달만 하는 용도로 사용되는 데이터 전송 객체이다. DTO에 기능이 있으면 안되는건 아니지만 주 목적은 데이터를 전송하는 것에 있다. 이러한 용어는 프로젝트에서 용도를 파악하기 위한 규칙으로 정하는 것이기 때문에 각 프로젝트를 할때 일관성 있게 규칙을 정하면 된다.


<br>
<br>


### @Profile
> 해당 애노테이션을 사용하면 특정 프로필의 경우에만 해당 스프링 빈을 등록하여 기능한다. 스프링은 로딩 시점에 application.properties 의 spring.profiles.active 속성을 읽어서 프로필로 사
용한다.

-  application.properties `spring.profiles.active=local` 해당 설정을 넣어 프로필을 지정할 수 있다.

``` java
@Profile("local")
public TestDataInit testDataInit(ItemRepository itemRepository) {
    return new TestDataInit(itemRepository);
}

```

<br>
<br>

### 권장하는 DB 식별자 선택 전략
1. null값은 허용하지 않는다.
2. 유일해야 한다.
3. 변하면 안된다.


<br>

- 테이블 기본 키 선택 전략
```
자연 키 : 비즈니스에 의미가 있는 키로 주민번호, 이메일, 전화번호를 예로 들 수 있다.

대리 키(대체 키) : 비즈니스와 관련 이 없는 키로 오라클 시퀀스, auto_increment, identity, 키생성 테이블을 예로 들 수 있다.
```
> 자연 키보다는 대리 키를 권장한다. 비즈니스 규칙은 생각보다 쉽게 변할 수 있기 때문에 자연 키는 권장하지 않는다. 
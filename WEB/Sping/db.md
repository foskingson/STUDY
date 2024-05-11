# 스프링 DB 연동

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
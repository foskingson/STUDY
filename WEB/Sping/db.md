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


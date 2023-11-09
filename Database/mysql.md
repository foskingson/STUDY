# MySQL
 <br>
 
 ### 정의 :mag:
 > 오라클 사의 오픈소스 관계형 데이터베이스 관리 시스템
<br>

### 1. MySQL의 구조

 >  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAGpB%2Fbtq3meih8Xt%2Fa90XgfmK2Sd1O9RA0KYTWK%2Fimg.png" width="800" height="400"/> 
출처: 생활코딩 (https://opentutorials.org/course/3161/19534)  
> mysql에서는 테이블들이 존재하고 그것을 하나로 묶은 것을 데이터베이스(스키마) 라고 부른다.
> 또한 이 여러 데이터베이스(스키마)를 하나로 묶은 것을 데이터베이스 서버라고 부른다. 
<br>

### MySQL 접속 방법 (윈도우기준)
> 1. cmd를 켠다
> 2. mysql의 실행파일이 있는 곳으로 경로를 옮긴다.
> 3. mysql.exe -uroot -p 를 통해 실행시킨다
> 4. 비밀번호 입력
<br>

### 데이터베이스(스키마) 생성
> CREATE DATABASE [이름];
> 생성 되었는지 확인 : SHOW DATABASES;
> 사용할 데이터베이스로의 변경 : USE [이름];
<br>


### 테이블 생성
>CREATE TABLE [이름]( <br>
>데이터이름 데이터타입[길이] <br>
>PRIMARY KEY(이름) <br>
>) 
- PRIMARY KEY는 테이블에 사용되는 기본 키로 각 행을 고유하게 식별하는 값이고 이값은 NULL값을 가지면 안됨
- 테이블 생성할때 데이터 타입 뒷부분에 추가할때 NOT NULL(이 값은 무조건 있어야함) , AUTO_INCREMENT(자동으로 값이 1씩 늘어남) 등을 사용할수 있음
- DESC 테이블명; 을 통해 확인 가능
<br>

  ### MySQL의 CRUD :star:
  - INSERT : 테이블 안에 데이터를 추가하는 구문 EX) INSERT INTO 테이블명 (데이터이름1,데이터이름2···) VALUES(값1,값2···);
  - SELECT : 테이블 안을 조회하여 출력하는 구문 EX) SELECT 데이터이름1,데이터이름2 FROM 테이블이름 WHERE 데이터이름=값;
  - UPDATE : 테이블 안의 값을 변경하는 구문 EX) UPDATE 테이블명 SET 데이터이름=바꿀값 WHERE 데이터이름=값;
  - DELETE : 테이블 안의 값을 지우는 구문 EX) DELETE FROM 테이블명 WHERE 데이터이름=값;
  <br>

### 테이블 분리
> ID,TITLE,DESCRIPTION,CREATED,AUTHOR,PROFILE이 들어있는 테이블이 있고 똑같은 사람이 여러번 값을 넣으면<br>
> 그 사람이 PROFILE을 바꿨을때 그 사람의 여러번 값을 넣은 만큼 PROFILE의 값을 바꿔줘야한다<br>
> 따라서 테이블을 ID,TITLE,DESCRIPTION,CREATED,AUTHOR_ID 라는 A테이블과 ID,AUTHOR,PROFILE 라는 B테이블로 나눠서 관리하면<br>
> 똑같은 사람이 여러번 값을 넣고 PROFILE 을 바꿔도 B테이블의 한사람의 값만 바꿔주면 되기 때문에 효율적이다
<br>
   
 ### MySQL의 JOIN :star::star:
  >두개 이상의 테이블의 값을 합쳐서 출력할 수 있다. 위에 있는 분리된 테이블을 예시로 들어보자 <br>
>EX) SELECT * FROM A LEFT JOIN B ON A.AUTHOR_ID=B.ID; <br>
>라고 작성하면 A테이블의 값 전체와 B테이블값 전체를 붙여서 확인할수있다 <br>
>ON A.AUTHOR_ID=B.ID 부분은 A랑 B를 어떻게 합칠지의 기준이 모호하기 때문에 기준을 잡아준것이다. <br>

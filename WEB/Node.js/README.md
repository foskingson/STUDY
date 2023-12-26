# Node.js
- 자바스크립트를 웹 브라우저 외부에서 사용할수 있게 해주는 자바스크립트 실행 환경(런타임)이다.
- 서버와 클라이언트로 구성된 웹사이트를 만들때 자바스크립트 하나의 언어로 만들수 있게 해준다.
- node.js는 NPM이라는 패키지 관리자가 함께 제공되 다른 패키지와 라이브러리를 쉽게 설치하고 관리 할수 있다.
- Node.js는 V8 JavaScript 런타임을 기반으로 구축되어 JavaScript 코드를 기본 기계어 코드로 컴파일하여 빠른 실행을 수행한다.
- 단일 스레드를 통해 연결을 효율적으로 처리할수 있지만 CPU부하가 큰 경우 처리가 힘들다.

<br>
<br>

### 모듈
- 모듈은 특정 기능을 수행하거나 관련된 기능을 그룹화하는 데 사용된다. <br>
+ `module.export = A` 을 통해 A라는 모듈을 다른 파일에서 사용하게 할수 있고 `var A = require('해당모듈이 담긴 경로')` 를 통해 가져와서 다른파일에서 사용할수 있다.<br>
- nodejs에 내장된 핵심모듈 중 일부
    - fs : 파일 시스템과 상호 작용하는 방법을 제공하여 파일 읽기 및 쓰기, 디렉터리 생성, 기타 파일 관련 작업 수행 등을 수행할 수 있다.
    - http 및 https: 이 모듈을 사용하면 각각 HTTP 및 HTTPS 서버를 생성하고 HTTP 요청을 할 수 있다. 이는 웹 서버를 구축하고 웹 서비스와 상호 작용하는 데 사용된다.
    - path: 파일 및 디렉터리 경로 작업에 도움을 주고 경로 확인, 결합 및 정규화 방법을 제공한다.
    - querystring: URL 쿼리 문자열을 구문 분석하고 형식을 지정하는 방법이 포함되어 있다. url에서 ?뒤에 오는 것들을 쿼리 스트링이라고 한다
    - url: URL 확인 및 구문 분석 방법을 제공한다.
  
 <br>


### fs(파일 시스템)
+ 먼저 상단에 `var fs=require('fs');` 를 작성해 fs모듈을 불러와야한다. <br>
- 파일 시스템에 관련된 기능들을 가진 객체이다. <br>
- fs를 통해 읽어온 파일 값은 콜백함수 안에서만 사용가능하다. <br>

#### fs.readFile : 파일 읽어오기 
+ 동기식으로 파일 읽어오기 `console.log(fs.readFilesync('test.txt','utf-8'))` => 같은 폴더 안에 있는 test.txt의 내용을 불러와 출력 <br>
- 비동기식으로 파일 읽어오기 
``` javascript
    fs.readFile('test.txt','utf-8',(err,result)=>{
        console.log(result):  
        // 같은 폴더 안에 있는 test.txt의 내용을 불러오는 동안 다른 작업을 하고 있다가 다 되면 해당 함수 실행
    })
``` 


#### fs.readdir : 파일 목록 읽어오기
``` javascript
    fs.readdir('./data',(err,files)=>{
        //해당 경로 디렉토리 안에 있는 파일을 차례대로 읽어와 출력
        i=0;
        while(i<files.length>){ 
            console.log(files[i])   
            i=i+1;
        }
    })
``` 


#### fs.writeFile : 파일 생성하기
``` javascript
// A라는 파일을 생성 파일 내용에는 B가 들어 있다.
 fs.writeFile(`./data/A`,'B','utf-8',function(err){  
        })

``` 


#### fs.rename + fs.writeFile : 파일 수정하기
``` javascript
fs.rename(`./data/A`,`./data/B`,()=>{    //파일명을 A=>B 로 수정
    fs.writeFile('./data/B','C','utf8',function(err){ // 파일 내용을 C로 수정
    });
});
```
<br>

### PM2 
+ `npm install pm2 -g` 터미널에 해당 명령어를 통해 설치가 가능하다. <br>
    - 윈도우에서 npm을 통해 설치할때 오류가 난다면 환경변수를 확인해봐야한다. <br>
    - 리눅스에서 해당 명령어를 할때 오류가 난다면 앞에 sudo를 붙여 관리자 권한으로  실행한다. <br>
+ `pm2 start [파일이름]` 을 통해 시작 가능하다.  <br>
    - 뒤에 --watch라는 옵션을 붙어줘야 변동사항이 있을때 서버를 자동으로 재시작한다. <br>
+ `pm2 list` 를 통해 실행중인 프로세스를 확인가능하다. <br> 
+ `pm2 stop [파일이름]` 를 통해 종료할수 있다 <br>
+ `pm2 log` 를 통해 문제점이 있을때 바로 확인할수있다. <br>
- nodejs를 통해 웹서버를 열고 코드를 수정하면 서버를 닫았다 열어야하는데 PM2는 코드의 수정이 이루어지면 자동으로 서버를 닫았다 열어 웹에 반영시켜준다. <br>
<br>


<br>
<br>

### MySQL + nodejs
+ `npm install -S mysql` 로 mysql모듈 설치 후 사용할수 있다.
- 데이터 베이스를 연결하여 nodejs를 사용하면 데이터의 관리(생성,읽기,업데이트,삭제)를 효과적으로 할수 있다.

``` javascript
var mysql = require('mysql');   // mysql 모듈 가져오기

var connection=mysql.createConnection({     // 연결 정보를 담은 객체를 생성
    host: 'localhost',
    user: 'root',
    password : 'al2732',
    database : 'nodesql'
});

connection.connect();   // 데이터베이스에 연결

connection.query('SELECT * FROM topic', function(err,result){
    if(err) {
        console.log(err)
    }
    console.log(result) // 쿼리 결과를 출력
})  // 첫번째인자인 sql문이 데이터베이스에 접속해 실행하고 두번째인자인 콜백함수가 호출

connection.end();
```
<br>


### express
 + `npm install express --save` 를 통해 모듈을 설치한다. <br>
 - express는 nodejs를 좀 더 편리하고 보기 좋게 사용할 수 있도록 도와주는 프레임워크이다. <br>
 - 주요 기능으로는 미들웨어와 라우팅 기능이 있다. <br>
    - 미들웨어는 클라이언트에게 요청이 오고 그 요청을 보내기 위해 응답하려는 중간(미들)에 목적에 맞게 처리를 하는 거쳐가는 함수 이다. <br>
- 미들웨어 종류
    - 1. 애플리케이션 레벨 미들웨어 :  app.use()를 사용하여 app 객체에 바인딩되고 애플리케이션에 대한 모든 요청에 ​​대해 실행되는 미들웨어 기능이다
    - 2. 라우터 레벨 미들웨어 애플리케이션 수준 미들웨어와 유사하지만 app 객체 대신 express.Router() 인스턴스에 바인딩 되어 경로에 관련된 미들웨어이다.
    - 3. 오류 처리 미들웨어 : 오류를 처리하는 데 사용되는 4개의 매개변수(err, req, res, next)가 있는 특수 미들웨어 기능이다.
    - 4. 내장 미들웨어 : Express에는 추가 기능을 제공하는 미들웨어 기능이 내장되어 있다. 예로는 정적 파일 제공을 위한 'express.static'가 있다.
    - 5. 타사 미들웨어 : Express 기능을 확장하기 위해 타사 개발자가 개발한 미들웨어로  요청 본문 구문 분석을 위한 body-parser, 보안을 위한 HTTP 헤더 설정을 위한 helmet 등이 있다.
    - 6. 맞춤형 미들웨어 : 직접 맞춤형 미들웨어를 만들어 코드의 양을 줄일수 있다.
- nodejs_express 폴더 안의 main.js 파일을 통해 간단한 미들웨어 사용 예시를 확인 할수 있다.
<br>
<br>

### 기타
- form을 통해 서버에서 정보를 가져올때는 get, 서버의 정보를 수정하거나 생성 및 삭제 할때는 post를 사용한다 <br>
- 배열은 순서대로 데이터를 정리하고 객체는 순서없이 데이터를 정리할수 있다. <br>
- sanitize-html 모듈을 통해 html태그를 필터링 하여 xss을 예방할수 있다. <br>
- package.json의 "dependecies" 부분을 통해 다른 곳에서 가져와 사용된 라이브러리를 확인할수 있다. <br>
- msa 아키텍처는 서버를 분산해 개발해 더 적은 단위로 서비스를 분리하므로 서비스간 의존선을 낮추고 유연하고 확장성 높은 아키텍처이다. 다만 복잡도가 높고 서비스 간 통신의 관리가 어렵다. <br>
- 템플릿엔진을 이용하면 더 쾌적하게 개발할수있다. 더적은코드를 사용할수도 있고 html에 제어문을 사용할수도있다. <br>










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
    - querystring: URL 쿼리 문자열을 구문 분석하고 형식을 지정하는 방법이 포함되어 있다.
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
<br>

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
<br>

#### fs.writeFile : 파일 생성하기
``` javascript
// A라는 파일을 생성 파일 내용에는 B가 들어 있다.
 fs.writeFile(`./data/A`,'B','utf-8',function(err){  
        })

``` 
 <br>

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
+ 먼저 `npm install -S mysql` 로 mysql모듈 설치한다


<br>
<br>

### 기타
- form을 통해 서버에서 정보를 가져올때는 get, 서버의 정보를 수정하거나 생성 및 삭제 할때는 post를 사용한다 <br>
- 배열은 순서대로 데이터를 정리하고 객체는 순서없이 데이터를 정리할수 있다. <br>
- sanitize-html 모듈을 통해 html태그를 필터링 하여 xss을 예방할수 있다.
- package.json의 "dependecies" 부분을 통해 다른 곳에서 가져와 사용된 라이브러리를 확인할수 있다.












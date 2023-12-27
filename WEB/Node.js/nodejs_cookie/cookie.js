// HTML 5에서는 쿠키에 의존하지 않고 로컬저장소나 세션저장소를 통해 브라우저를 닫았다 열어도 키값 쌍으로 데이터를 저장할수 있다.
// 쿠키를 통해 로그인한 사용자를 식별자로 구분할수 있다. 보안에 민감
// npm install -s cookie        // 쿠키를 핸들링하는 모듈 설치

// 세션 쿠키: 브라우저를 껐다 키면 사라짐
// permanent 쿠키(영구적 쿠키) : 브라우저를 꺼도 사라지지 않음


var cookie = require('cookie');

var http = require('http');
http.createServer(function(request, response){
    if(request.headers.cookie!==undefined){ // 쿠키값이 없을때 에러나는것을 대비
        var cookies = cookie.parse(request.headers.cookie)  // request 헤더에 있는 쿠키를 객체화 시켜줌
        console.log(cookies) 
    }
    
     response.writeHead(200, {  // 헤더 작성
         'Set-Cookie':['aacookie=aa', 'bbcookie=bb'] ,   // 세션쿠키 생성
         'Set-Cookie':[`permanent=cookie; Max-Age=${600000}`], // permanent 쿠키 생성 뒤에 Max-Age를 통해 600000초동안 쿠키가 살아있게 기간을 지정할수 있다.
         'Set-Cookie':[`secure=secure; Secure`],     // Secure을 통해 https에서만 쿠키를 사용할수 있도록함 쿠키값 탈취등의 보안문제방지를 위해 사용
         'Set-Cookie':[`http=http; HttpOnly`],       // HttpOnly를 통해 document.cookie와 같은 자바스크립트코드로 접근할수 없게 할수 있다. 보안문제 방지로 사용
         'Set-Cookie':[`path=path; path=/cookie`],   // path를 지정해 해당 경로에서만 쿠키가 생성되게 할수있다.
         'Set-Cookie':[`domain=domain; Domain=o2.org`] // 도메인을 지정해 그 도메인에서 쿠키가 생성되게 할수있다.
         
    });    

    
    response.end('Cookie!!'); 
}).listen(3000);
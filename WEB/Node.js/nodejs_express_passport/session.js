// npm install -s express-session
// nodemon을 이용해 재시작 없이 코드를 반영할수 있다. npm install nodemon --save-dev
// npm rum backend로 실행가능

// npm install -s session-file-store   세션 저장소 모듈 설치


var express = require('express') 
var parseurl = require('parseurl')
var session = require('express-session')
var FileStore = require('session-file-store')(session)      // session모듈을 가져온 다음에 가져와야됨
  
var app = express()

app.use(session({   // 요청이 있으면 세션이 시작됨
    secret: 'asadlfkj!@#!@#dfgasdg',    // 꼭 넣어야 하는 시크릿코드 (보안의 이유로 다른 파일로 만들어서 변수처리해야함)
    resave: false,                      // 세션 데이터가 바뀌기 전까지 세션저장소에 저장하지 않음
    saveUninitialized: true ,            // 세션이 필요하기전까지 세션을 구동시키지 않음
    store:new FileStore()               // 웹서버가 꺼져도 저장되는 저장공간 생성, sessions 폴더 안에 파일로 저장되어 있음
  }))

app.get('/', function (req, res, next) {
    console.log(req.session) // 위의 세션 미들웨어를 실행하면 request.session이라는 객체가 생성됨
    if(req.session.num==undefined){     
        req.session.num=1;  
    }else{
        req.session.num=req.session.num+1;  // 방문한 횟수마다 1씩 늘어남 , 서버를 껏다 키면 초기화, store:new FileStore() 를 추가해 초기화 방지 가능
    }
    res.send(`hello ${req.session.num}`)
})
 
app.listen(3000, function(){
    console.log('3000!');
});
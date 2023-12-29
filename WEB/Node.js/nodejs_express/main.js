// npm install express --save  기본 설치
// express의 주요기능 ***미들웨어***와 라우팅
// 미둘웨어 클라이언트에게 요청이 오고 그 요청을 보내기 위해 응답하려는 중간(미들)에 목적에 맞게 처리를 하는, 말하자면 거쳐가는 함수들
// 템플릿엔진을 이용해 더 쾌적하게 개발할수있다. 더적은코드를 사용할수도 있고 html에 제어문을 사용할수도있다.
// msa 아키텍처 서버를 분산해 개발



var express =require('express'); // express 프레임워크 가져오기
var app = express();             // express 애플리케이션 생성

var bodyParser=require('body-parser') // npm install body-parser 통해 설치후 사용
var compression=require('compression')// npm install compression -save 데이터를 압축시켜주는 미들웨어

var helmet = require('helmet')
app.use(helmet()) // npm install --save helmet 기본적인 보안 솔루션을 다룰수 있게해주는 미들웨어
// 다른 보안 솔루션들도 조치해두는게 좋음

var template = require('./lib/template.js');
var fs = require('fs');
var qs = require('querystring');
var path = require('path');
var indexRouter= require('./routes/')
var topicRouter= require('./routes/topic') // 라우팅


app.use(express.static('public'))// 정적인 자원을 사용할수 있게해주는 미들웨어


app.use(bodyParser.urlencoded({extended:false}))  // 사용자가 URL 인코딩 데이터로 들어오는 요청을 할때마다 해당 미들웨어를 통해 구문 분석하는 데 사용
// 요청이 들어오면 자동으로 구문분석후에 body를 생성함
app.use(compression())   // 요청이 들어올때마다 데이터를 압축시켜서 용량을 줄여줌

app.get('*',(request,response,next)=>{  // 직접 미들웨어를 만들어 코드의 양을 줄일수 있다. app.get('*')를 통해 get으로 요청될때만 미들웨어 실행
  fs.readdir('./data', function(error, filelist){
    request.list=filelist;

    next(); // next()를 통해 다음 미들웨어를 실행할지 결정
    // next('route') 를 사용하면 다음 미들웨어는 건너뛰고 다다음 미들웨어를 실행한다
  })
})

app.get('/test', (req, res) => res.send('zz'))  // /test 경로에서 zz출력 , 훨씬 편리한 라우팅 방식

app.use('/',indexRouter)
app.use('/topic',topicRouter)







app.use(function(req,res,next){ 
  res.status(404).send('없다')
})

app.use(function (err, req, res, next) {
  console.error(err.stack)
  res.status(500).send('Something broke!')
}); 

app.listen(4000) // 서버를 시작하고 4000포트로 들어오는 연결 수신




























var express = require('express')
var router = express.Router()   // 라우팅 모듈 가저요기

var template = require('../lib/template.js');
var auth = require('../lib/auth.js');





router.get('/', (request, response) =>{  // 이것도 2번째 인자는 사실 미들웨어임
   // console.log('/',request.user) // deserializeUser의 done에서 받은 authData가 request.user에 들어있음
   
   var fmsg=request.flash()
   var message=''
   console.log(fmsg)
   if(fmsg.message){
     message=fmsg.message;
   }
 
    var title = 'Welcome';
    var description = 'Hello, Node.js';
    
    var list = template.list(request.list);
    var html = template.HTML(title, list, 
      `
      <h3><div style="color:blue">${message}</div></h3>
      <h2>${title}</h2>${description} 
      <img src="/images/hello.jpg" style="width:300px;height:300px; display:block; margin-top:30px">
      `,
      `<a href="/topic/create">create</a>`,auth.StatusUI(request,response)
    );
    response.send(html);
  } ) // 해당 경로로 접속했을때 함수 호출해 res.send('Hello World!')를 리턴함 , 메인화면에서 Hello World! 출력
  
  module.exports=router // 라우팅한걸 다른곳에서 쓸수 있게하기
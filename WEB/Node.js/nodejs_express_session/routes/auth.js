var express = require('express')
var router = express.Router()   // 라우팅 모듈 가저요기
var fs = require('fs');
var sanitizeHtml = require('sanitize-html');
var template = require('../lib/template.js');
var path = require('path');
var session = require('express-session')
var FileStore = require('session-file-store')(session)  

var authData={  // 현실에서는 소스코드안에 계정정보가 있으면 안됨
    id:'foskingson',
    password:'al2732',  // 패스워드는 평문이면 안됨
    nick:'jo'
}







router.get('/login', (request, response) => { 
    var title = 'WEB - login';
    var list = template.list(request.list);
    var html = template.HTML(title, list, `
      <form action="/auth/login_process" method="post">
        <p><input type="text" name="id" placeholder="id"></p>
        <p><input type="text" name="password" placeholder="password"></p>
        <p>
          <input type="submit" value="로그인">
        </p>
      </form>
    `, '');
    response.send(html);
  })


router.post('/login_process', function(request, response){  
    var post = request.body;  
    var id = post.id;
    var password=post.password;
    
    if(id===authData.id && password===authData.password){
        request.session.is_logined=true;
        request.session.nick=authData.nick;
        // 세션 데이터를 받으면 세션 저장소에 저장하는 작업을 하는데 느리게 된다면 세션데이터를 받았는데 안받았다고 표시되는 경우가 있다. 
        // 그 경우를 대비해 request.session.save() 를 사용하면 세션 저장소에 저장하는 작업을 바로 실행할수 있다.
        request.session.save(()=>{      
            response.redirect(`/`)
        });
        
    }else{
        request.session.check=false;
        request.session.save(()=>{      
            response.redirect(`/`)
        });
        
    }
})

router.get('/logout', function(request, response){  
    request.session.destroy(function(err){  // 세션 삭제
        
    response.redirect(`/`)
    
})
})



  module.exports=router
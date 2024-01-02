var express = require('express')
var router = express.Router()   // 라우팅 모듈 가저요기
var fs = require('fs');
var sanitizeHtml = require('sanitize-html');
var template = require('../lib/template.js');
var path = require('path');
var session = require('express-session')
var FileStore = require('session-file-store')(session)  




router.get('/login', (request, response) => { 
  var fmsg=request.flash()
  var message=''
  console.log(fmsg)
  if(fmsg.message){
    message=fmsg.message;
  }

  var title = 'WEB - login';
  var list = template.list(request.list);
  var html = template.HTML(title, list, `
    <h3>
    <div style="color:red">${message}</div>
    </h3>
    <form action="/auth/login_process" method="post">
      <p><input type="text" name="id" placeholder="id"></p>
      <p><input type="password" name="password" placeholder="password"></p>
      <p>
        <input type="submit" value="로그인">
      </p>
    </form>
  `, '');
  response.send(html);
})





router.get('/logout', function(request, response){  
  request.logout(function (err) {
    if (err) {
    return next(err);
    }
   /* request.session.destroy(function(err){  // 세션 삭제
      response.redirect(`/`)
  })*/
  request.session.save(function(err){  // 현재 세션 상태 저장
    response.redirect(`/`)
  })
  });


    
})



  module.exports=router
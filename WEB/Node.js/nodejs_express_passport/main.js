// npm install -s passport
// npm install -s passport-local
// npm install -s connect-flash // 플래쉬 메시지 출력해주는 미들웨어
// 미들웨어는 실행순서가 제일 중요

//pm2 start main.js --watch --ignore-watch="sessions/*" sessions폴더는 자동 재시작이 되지 않도록하기(세션정보가 저장 안되는 현상을 방지하기 위해서)
// pm2 start main.js --watch --no-daemon --ignore-watch "data/* sessions/*" 


var express = require('express');
var app = express();

var session = require('express-session')
var FileStore = require('session-file-store')(session)

var bodyParser = require('body-parser');
var compression = require('compression');
var helmet = require('helmet');
var template = require('./lib/template.js');
var fs = require('fs');
var qs = require('querystring');
var path = require('path');
var indexRouter = require('./routes/');
var topicRouter = require('./routes/topic');
var authRouter = require('./routes/auth');



app.use(helmet());
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(compression());
app.use(session({   
  secret: 'asadlfkj!@#!@#dfgasdg',    
  resave: false,                      
  saveUninitialized: true ,           
  store:new FileStore()              
}))




/*
app.get('/flash', function(req, res){
  // Set a flash message by passing the key, followed by the value, to req.flash().
  req.flash('msg', 'Flash is back!!');  //세션정보에 flash정보 추가
  res.send('flash');
});

app.get('/flash-display', function(req, res){
  // Get an array of flash messages by passing the key to req.flash()
  var fmsg =  req.flash();  //flash정보를 변수에 저장하고 세션정보에서 flash정보를 삭제 flash정보를 변수에 저장 1회용메시지
  console.log(fmsg);
  res.send(fmsg);
});
*/

var passport=require('./lib/passport')(app)


//사용자가 전송한 데이터를 passport가 로그인 데이터를 처리하는 코드
// 리디렉션됬음에도 불구하고 세션에 플래시메세지가 저장되지 않는 경우 커스텀 콜백 사용 passport 홈페이지 참고
app.post('/auth/login_process', function (req, res, next) {
  passport.authenticate('local', function (err, user, info) {
   
      if (err) {
          return next(err);
      }
      if (!user) {
          req.session.save(function () {
            console.log('info', info.message);
            req.flash('message', info.message);
            return req.session.save(function () {
              res.redirect('/auth/login');
          });
          });
          return;
      }
      req.login(user, function (err) {
          if (err) { return next(err); }
          req.session.save(function () {
            console.log('info', info.message);
            req.flash('message', info.message);
            return req.session.save(function () {
              res.redirect('/');
          });
          });
      });
  })(req, res, next);
    })

app.get('*', (request, response, next) => {
  fs.readdir('./data', function (error, filelist) {
    request.list = filelist;
    next();
  });
});


app.use('/', indexRouter);
app.use('/topic', topicRouter);
app.use('/auth', authRouter);

app.use(function (req, res, next) {
  res.status(404).send('None');
});

app.use(function (err, req, res, next) {
  console.error(err.stack);
  res.status(500).send('Something broke!'); //500번 서버측 오류 오타일 확률 높음
});

app.listen(4000);
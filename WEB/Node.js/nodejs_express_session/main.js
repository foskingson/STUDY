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
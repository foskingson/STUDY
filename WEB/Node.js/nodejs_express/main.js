// npm install express --save  기본 설치
// express의 주요기능 ***미들웨어***와 라우팅
// 미둘웨어 클라이언트에게 요청이 오고 그 요청을 보내기 위해 응답하려는 중간(미들)에 목적에 맞게 처리를 하는, 말하자면 거쳐가는 함수들
// 템플릿엔진을 이용해 더 쾌적하게 개발할수있다. 더적은코드를 사용할수도 있고 html에 제어문을 사용할수도있다.
// msa 아키텍처 서버를 분산해 개발

app.use(helmet()) // npm install --save helmet 기본적인 보안 솔루션을 다룰수 있게해주는 미들웨어
// 다른 보안 솔루션들도 조치해두는게 좋음

var express =require('express'); // express 프레임워크 가져오기
var app = express();             // express 애플리케이션 생성

var bodyParser=require('body-parser') // npm install body-parser 통해 설치후 사용
var compression=require('compression')// npm install compression -save 데이터를 압축시켜주는 미들웨어

var template = require('./lib/template.js');
var fs = require('fs');
var sanitizeHtml = require('sanitize-html');
var qs = require('querystring');
var path = require('path');

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

app.get('/', (request, response) =>{  // 이것도 2번째 인자는 사실 미들웨어임
  var title = 'Welcome';
  var description = 'Hello, Node.js';
  var list = template.list(request.list);
  var html = template.HTML(title, list, 
    `<h2>${title}</h2>${description} 
    <img src="/images/hello.jpg" style="width:300px;height:300px; display:block; margin-top:30px">
    `,
    `<a href="/create">create</a>`
  );
  response.send(html);
} ) // 해당 경로로 접속했을때 함수 호출해 res.send('Hello World!')를 리턴함 , 메인화면에서 Hello World! 출력

app.get('/page/:pageId', (request, response,next) => {   // pageId의 값은 request.params에 객체로 저장됨
  var id= request.params.pageId;
  fs.readFile(`data/${request.params.pageId}`, 'utf8', function(err, description){
    if(err){
      next(err)

    }else{
      var title = request.params.pageId;
      var sanitizedTitle = sanitizeHtml(title);
      var sanitizedDescription = sanitizeHtml(description, {
        allowedTags:['h1']
      });
      var list = template.list(request.list);
      var html = template.HTML(sanitizedTitle, list,
        `<h2>${sanitizedTitle}</h2>${sanitizedDescription}`,
        ` <a href="/create">create</a>
          <a href="/update/${id}">update</a> 
          <form action="/delete_process" method="post">
            <input type="hidden" name="id" value="${sanitizedTitle}">
            <input type="submit" value="delete">
          </form>`
      );
      response.send(html);
    }
    
  });
})

app.get('/create', (request, response) => {
  var title = 'WEB - create';
  var list = template.list(request.list);
  var html = template.HTML(title, list, `
    <form action="/create_process" method="post">
      <p><input type="text" name="title" placeholder="title"></p>
      <p>
        <textarea name="description" placeholder="description"></textarea>
      </p>
      <p>
        <input type="submit">
      </p>
    </form>
  `, '');
  response.send(html);
})

app.post('/create_process', function(request, response){  // form에서 post방식으로 받았으므로 post메서드 사용
 /* var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var title = post.title;
          var description = post.description;
          fs.writeFile(`data/${title}`, description, 'utf8', function(err){
            response.redirect(302, `/page/${title}`);   // 리다이렉트를 간편하게 사용가능
          })
      });
*/

// 위의 주석 코드를 bodyparser미들웨어를 통해 아래와 같이 간단하게 만들수있음
    var post = request.body;  
    var title = post.title;
    var description = post.description;
    fs.writeFile(`data/${title}`, description, 'utf8', function(err){
      response.redirect(302, `/page/${title}`);   // 리다이렉트를 간편하게 사용가능
    })
})



app.get('/update/:pageId', (request, response) => {
  var id= request.params.pageId;
  var filteredId = path.parse(id).base;
  fs.readFile(`data/${filteredId}`, 'utf8', function(err, description){
    var title = id;
    var list = template.list(request.list);
    var html = template.HTML(title, list,     `
    <form action="/update_process" method="post">
      <input type="hidden" name="id" value="${title}">
      <p><input type="text" name="title" placeholder="title" value="${title}"></p>
      <p>
        <textarea name="description" placeholder="description">${description}</textarea>
      </p>
      <p>
        <input type="submit">
      </p>
    </form>
    `,
    ''
  );
  response.send(html);
  });
})

app.post('/update_process', function(request, response){  
  var post = request.body;
  var id = post.id;
  var title = post.title;
  var description = post.description;
  console.log(title)
  fs.rename(`data/${id}`, `data/${title}`, function(error){
    fs.writeFile(`data/${title}`, description, 'utf8', function(err){
      response.writeHead(302, {Location: `/page/${title}`});
      response.end();
    })
  })
})


app.post('/delete_process', function(request, response){  
  var post = request.body;
  var id = post.id;
  var filteredId = path.parse(id).base;
  fs.unlink(`data/${filteredId}`, function(error){
    response.writeHead(302, {Location: `/`});
    response.end();
  })
})

app.use(function(req,res,next){ 
  res.status(404).send('없다')
})

app.use(function (err, req, res, next) {
  console.error(err.stack)
  res.status(500).send('Something broke!')
}); 

app.listen(4000) // 서버를 시작하고 4000포트로 들어오는 연결 수신




























/*
var http = require('http');
var fs = require('fs');
var url = require('url');
var qs = require('querystring');
var template = require('./lib/template.js');
var path = require('path');
var sanitizeHtml = require('sanitize-html');

var app = http.createServer(function(request,response){
    var _url = request.url;
    var queryData = url.parse(_url, true).query;
    var pathname = url.parse(_url, true).pathname;
    if(pathname === '/'){
      if(queryData.id === undefined){
        fs.readdir('./data', function(error, filelist){
          var title = 'Welcome';
          var description = 'Hello, Node.js';
          var list = template.list(filelist);
          var html = template.HTML(title, list,
            `<h2>${title}</h2>${description}`,
            `<a href="/create">create</a>`
          );
          response.writeHead(200);
          response.end(html);
        });
      } else {
        fs.readdir('./data', function(error, filelist){
          var filteredId = path.parse(queryData.id).base;
          fs.readFile(`data/${filteredId}`, 'utf8', function(err, description){
            var title = queryData.id;
            var sanitizedTitle = sanitizeHtml(title);
            var sanitizedDescription = sanitizeHtml(description, {
              allowedTags:['h1']
            });
            var list = template.list(filelist);
            var html = template.HTML(sanitizedTitle, list,
              `<h2>${sanitizedTitle}</h2>${sanitizedDescription}`,
              ` <a href="/create">create</a>
                <a href="/update?id=${sanitizedTitle}">update</a>
                <form action="delete_process" method="post">
                  <input type="hidden" name="id" value="${sanitizedTitle}">
                  <input type="submit" value="delete">
                </form>`
            );
            response.writeHead(200);
            response.end(html);
          });
        });
      }
    } else if(pathname === '/create'){
      fs.readdir('./data', function(error, filelist){
        var title = 'WEB - create';
        var list = template.list(filelist);
        var html = template.HTML(title, list, `
          <form action="/create_process" method="post">
            <p><input type="text" name="title" placeholder="title"></p>
            <p>
              <textarea name="description" placeholder="description"></textarea>
            </p>
            <p>
              <input type="submit">
            </p>
          </form>
        `, '');
        response.writeHead(200);
        response.end(html);
      });
    } else if(pathname === '/create_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var title = post.title;
          var description = post.description;
          fs.writeFile(`data/${title}`, description, 'utf8', function(err){
            response.writeHead(302, {Location: `/?id=${title}`});
            response.end();
          })
      });
    } else if(pathname === '/update'){
      fs.readdir('./data', function(error, filelist){
        var filteredId = path.parse(queryData.id).base;
        fs.readFile(`data/${filteredId}`, 'utf8', function(err, description){
          var title = queryData.id;
          var list = template.list(filelist);
          var html = template.HTML(title, list,
            `
            <form action="/update_process" method="post">
              <input type="hidden" name="id" value="${title}">
              <p><input type="text" name="title" placeholder="title" value="${title}"></p>
              <p>
                <textarea name="description" placeholder="description">${description}</textarea>
              </p>
              <p>
                <input type="submit">
              </p>
            </form>
            `,
            `<a href="/create">create</a> <a href="/update?id=${title}">update</a>`
          );
          response.writeHead(200);
          response.end(html);
        });
      });
    } else if(pathname === '/update_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var id = post.id;
          var title = post.title;
          var description = post.description;
          fs.rename(`data/${id}`, `data/${title}`, function(error){
            fs.writeFile(`data/${title}`, description, 'utf8', function(err){
              response.writeHead(302, {Location: `/?id=${title}`});
              response.end();
            })
          });
      });
    } else if(pathname === '/delete_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var id = post.id;
          var filteredId = path.parse(id).base;
          fs.unlink(`data/${filteredId}`, function(error){
            response.writeHead(302, {Location: `/`});
            response.end();
          })
      });
    } else {
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3003);
*/
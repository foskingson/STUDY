// 인증 / 개인화 / 추적 (개인 사용자의 방문횟수나 언어를 설정했을때 그 설정이 유지되도록 할수있다.)
// 실습용 실제로 이런식으로 쿠키를 이용해 로그인을 만들면 보안사고남


var http = require('http');
var fs = require('fs');
var url = require('url');
var qs = require('querystring');
var template = require('./lib/template.js');
var path = require('path');
var sanitizeHtml = require('sanitize-html');
var cookie = require('cookie')

function authIsOwner(request,response){
  var isOwner=false;
  var cookies={}

  if(request.headers.cookie){
    cookies=cookie.parse(request.headers.cookie);
    
  }

  if(cookies.id=='foskingson' && cookies.password==='al2732'){
    isOwner=true;
  }

  return isOwner

}

function authStatusUI(request,response){
  var authStatusUI=`<a href="/login">login</a>`
  
  if(authIsOwner(request,response)===true){
    authStatusUI=`<a href="/logout_process">logout</a>`
  }
  return authStatusUI

}


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
            `<a href="/create">create</a>`,authStatusUI(request,response)
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
                </form>`, authStatusUI(request,response)
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
        `, '', authStatusUI(request,response));
        response.writeHead(200);
        response.end(html);
      });
    } else if(pathname === '/create_process'){
      if(authIsOwner(request,response)===false){
        response.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'});
        response.end('로그인이 필요합니다. <a href="/login">login</a>');
        return false
      }
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
            `<a href="/create">create</a> <a href="/update?id=${title}">update</a>`, authStatusUI(request,response)
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
    } else if(pathname==='/login'){
      fs.readdir('./data', function(error, filelist){
        var title = 'Welcome';
        var description = 'Hello, Node.js';
        var list = template.list(filelist);
        var html = template.HTML(title, list,
          `<form action="/login_process" method="post">
            <p><input type="text" name="id" placeholder="email"></p>
            <p><input type="text" name="password" placeholder="password"></p>
            <p><input type="submit" value="로그인하기"></p>
          </form>`,
          ``
        );
        response.writeHead(200);
        response.end(html);
      });

    }
    
    else if(pathname==="/login_process"){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          if(post.id==='foskingson' && post.password==='al2732'){
            response.writeHead(302, {
              'Set-Cookie': [`id=${post.id}`,`password=${post.password}`,`nick=jo`],
              Location:'/'});
              response.end();
          }else{
            response.writeHead(302, {Location:'/'});
            response.end();

          }
          
          
          
      });

      
    }
    else if(pathname==="/logout_process"){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          
          response.writeHead(302, {
            'Set-Cookie': [`id=; Max-Age=0`,`password=; Max-Age=0`,`nick=; Max-Age=0`], // ; Max-Age=0을 붙여 바로 쿠키를 삭제하루 있다.
            Location:'/'
          });
          response.end();
          
          
      });

      
    }else {
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3000);
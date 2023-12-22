var http = require('http');
var fs = require('fs');
var url = require('url');
var qs = require('querystring');
var template = require('./lib/template.js');
var path = require('path');
var sanitizeHtml = require('sanitize-html');
var mysql = require('mysql');   // 데이터베이스를 이용해서 fs를 대신하여 코드를 짬

var db=mysql.createConnection({     
  host: 'localhost',
  user: 'root',
  password : 'al2732',
  database : 'nodesql'
});

db.connect();   


var app = http.createServer(function(request,response){
    var _url = request.url;
    var queryData = url.parse(_url, true).query;
    var pathname = url.parse(_url, true).pathname;
    if(pathname === '/'){
      if(queryData.id === undefined){
        /*fs.readdir('./data', function(error, filelist){
          var title = 'Welcome';
          var description = 'Hello, Node.js';
          var list = template.list(filelist);
          var html = template.HTML(title, list,
            `<h2>${title}</h2>${description}`,
            `<a href="/create">create</a>`
          );
          response.writeHead(200);
          response.end(html);
        });*/

        db.query('SELECT * FROM topic', function(err,topics){
          if(err) {
              console.log(err)
          }
          
          var title = 'Welcome';
          var description = 'Hello, Node.js';
          var list = template.list(topics);
          var html = template.HTML(title, list,
            `<h2>${title}</h2>${description}`,
            `<a href="/create">create</a>`
          );
          response.writeHead(200);
          response.end(html);
        })  


      } else {
        /*fs.readdir('./data', function(error, filelist){
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
        });*/
        db.query(`SELECT * FROM topic`, function(err,topics){
          if(err) {
            throw err; // 에러를 출력하면서 즉시 중지
        }
          db.query(`SELECT topic.id, title, description, created, author_id, name, profile FROM topic LEFT JOIN author ON topic.author_id = author.id WHERE topic.id = ${queryData.id};`, function(err2,topic){
            if(err2) {
                throw err2;
            }
            
            var id=topic[0].id;
            
            var title = topic[0].title;
            var description = topic[0].description;
            var list = template.list(topics);
            var html = template.HTML(title, list,
              `<h2>${title}</h2>${description}
              <p>글쓴이 : ${topic[0].name}</p>

              `,
              `<a href="/create">create</a>
              <a href="/update?id=${id}">update</a>
                  <form action="delete_process" method="post">
                    <input type="hidden" name="id" value="${id}">
                    <input type="submit" value="delete">
                  </form>`
            );
            response.writeHead(200);
            response.end(html);
          })
      })
      }
    
    } else if(pathname === '/create'){
      /*fs.readdir('./data', function(error, filelist){
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
      });*/

      db.query('SELECT * FROM topic', function(err,topics){
        if(err) {
          console.log(err)
      }
        db.query('SELECT * FROM author' ,(err2,authors)=>{
          if(err2) {
            console.log(err2)
            }

            
            var title = 'WEB - create';
            var list = template.list(topics);
            var html = template.HTML(title, list,
              `<form action="/create_process" method="post">
                  <p><input type="text" name="title" placeholder="title"></p>
                  <p>
                    <textarea name="description" placeholder="description"></textarea>
                  </p>
                  <p>
                    ${template.authorSelect(authors)}
                  </p>
                    <input type="submit">
                  </p>
              </form>`, ``);
            response.writeHead(200);
            response.end(html);
          })

        })
        


    } else if(pathname === '/create_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var title = post.title;
          var description = post.description;
          /*fs.writeFile(`data/${title}`, description, 'utf8', function(err){
            response.writeHead(302, {Location: `/?id=${title}`});
            response.end();
          })*/
          db.query('INSERT INTO topic (title,description,created,author_id) VALUES(?,?,NOW(),?)',[post.title,post.description,post.author] ,function(err,result){
            // 쿼리문안에 변수를 넣으려면 위에 방법처럼 ?를 사용하고 뒤에 인자로 배열을주고 배열값을 차례대로 주고싶은 변수를 넣는다
            if(err) {
                throw err
            }
            
            response.writeHead(302, {Location: `/?id=${result.insertId}`});
            response.end();
          })
      });
    } else if(pathname === '/update'){
      
      /*fs.readdir('./data', function(error, filelist){
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
      });*/
      db.query('SELECT * FROM topic', function(err,topics){
        if(err) {
            console.log(err)
        }
        db.query(`SELECT * FROM topic WHERE id=${queryData.id}`, function(err2,topic){
          if(err2) {
              throw err2;
          }
          db.query('SELECT * FROM author' ,(err3,authors)=>{
            if(err3) {
              throw err3;
          }
            var id=topic[0].id;
  
            var title = topic[0].title;
            var description = topic[0].description;
            var list = template.list(topics);
            var html = template.HTML(title, list,
              `
              <form action="/update_process" method="post">
              <input type="hidden" name="id" value="${id}">
              <p><input type="text" name="title" placeholder="title" value="${title}"></p>
              <p>
                <textarea name="description" placeholder="description">${description}</textarea>
              </p>
              <p>
                    ${template.authorSelect(authors,topic[0].author_id)}
                  </p>
              <p>
                <input type="submit">
              </p>
            </form>`,
              ``
            );
            response.writeHead(200);
            response.end(html);
            })
          
          
        })
      
      })

      
    } else if(pathname === '/update_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var title = post.title;
          var description = post.description;

          
          db.query('UPDATE topic SET title=?, description=?, author_id=? WHERE id=?',[post.title,post.description,post.author,post.id] ,function(err,result){
            // 쿼리문안에 변수를 넣으려면 위에 방법처럼 ?를 사용하고 뒤에 인자로 배열을주고 배열값을 차례대로 주고싶은 변수를 넣는다
            if(err) {
                throw err
            }
            
            response.writeHead(302, {Location: `/?id=${post.id}`});
            response.end();
          })
      });
    }else if(pathname === '/delete_process'){
      var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
          var post = qs.parse(body);
          var id = post.id;
          var filteredId = path.parse(id).base;
          /*fs.unlink(`data/${filteredId}`, function(error){
            response.writeHead(302, {Location: `/`});
            response.end();
          })*/


          db.query(`DELETE FROM topic WHERE id=${post.id}`,(err,result)=>{
            if(err) {
              throw err
           }
          
            response.writeHead(302, {Location: `/`});
            response.end();
          })

      });
    } else {
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3000);

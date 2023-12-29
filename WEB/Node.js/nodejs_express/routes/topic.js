var express = require('express')
var router = express.Router()   // 라우팅 모듈 가저요기
var fs = require('fs');
var sanitizeHtml = require('sanitize-html');
var template = require('../lib/template.js');
var path = require('path');


router.get('/create', (request, response) => { // /topic/:pageId앞에 작성해야 에러가 나지 않음 순서중요
    // 앞에 `topic`으로 main.js에서 라우팅했으니까 /topic/create가 아니라 그냥 /create로 작성해야함
    var title = 'WEB - create';
    var list = template.list(request.list);
    var html = template.HTML(title, list, `
      <form action="/topic/create_process" method="post">
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
  
  router.post('/create_process', function(request, response){  
   
    var post = request.body;  
    var title = post.title;
    var description = post.description;
    fs.writeFile(`data/${title}`, description, 'utf8', function(err){
      response.redirect(302, `/topic/${title}`);   
    })
  })
  
  router.get('/update/:pageId', (request, response) => {
    var id= request.params.pageId;
    var filteredId = path.parse(id).base;
    fs.readFile(`data/${filteredId}`, 'utf8', function(err, description){
      var title = id;
      var list = template.list(request.list);
      var html = template.HTML(title, list,     `
      <form action="/topic/update_process" method="post">
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
  
  router.post('/update_process', function(request, response){  
    var post = request.body;
    var id = post.id;
    var title = post.title;
    var description = post.description;
    fs.rename(`data/${id}`, `data/${title}`, function(error){
      fs.writeFile(`data/${title}`, description, 'utf8', function(err){
        response.redirect(302, `/topic/${title}`);   
      })
    })
  })
  
  router.post('/delete_process', function(request, response){  
    var post = request.body;
    var id = post.id;
    var filteredId = path.parse(id).base;
    fs.unlink(`data/${filteredId}`, function(error){
      response.writeHead(302, {Location: `/`});
      response.end();
    })
  })
  
  router.get('/:pageId', (request, response,next) => {   // pageId의 값은 request.params에 객체로 저장됨
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
          ` <a href="/topic/create">create</a>
            <a href="/topic/update/${id}">update</a> 
            <form action="/topic/delete_process" method="post">
              <input type="hidden" name="id" value="${sanitizedTitle}">
              <input type="submit" value="delete">
            </form>`
        );
        response.send(html);
      }
      
    });
  })

  module.exports=router
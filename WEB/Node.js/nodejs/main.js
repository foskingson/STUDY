// 자바스크립트를 통해 nodejs기능을 사용할수있다.
// 터미널에서 node .\main.js 를 통해 웹서버 localhost:3000이 열림
// url에서 ?뒤에 오는 것들을 쿼리 스트링이라고 함
var http = require('http');
var fs = require('fs');
var url=require('url'); // node.js의 url 모듈을 가져와 변수 url에 넣는다. 
var qs=require(`querystring`);
var sanitizeHtml=require('sanitize-html')



function templateHTML(title,list,body,control){
  return `
  <!doctype html>
  <html>
  <head>
    <title>WEB1 - ${title} </title> 
    <meta charset="utf-8">
  </head>
  <body>
    <h1><a href="/">WEB</a></h1>
    <ol>
      ${list}
    </ol>
    ${control}
  ${body}
  </body>
  </html>
  `

}

function dataList(files){
  var i=0;
  var list=``
    while(i<files.length){
      list=list+`<li><a href="/?id=${files[i]}">${files[i]}</a></li>\n`
      i=i+1
    }
    return list
}



var app = http.createServer(function(request,response){ //nodejs로 웹서버의 접속이 들어올때마다 createServer의 콜백함수를 호출 
  // request는 요청할때 웹브라우저가 보낸정보 response는 응답할때 우리가 웹브라우저에 전송할 정보
    var _url = request.url;         
    var queryData=url.parse(_url,true).query; //url의 모듈중 하나인 parse를 사용해 _url의 url구문을 분석한다. true를 통해 쿼리문자열까지 분석한다는 이야기이다.
    var pathname=url.parse(_url,true).pathname; // pathname은 url에서 도메인이름뒷부분 쿼리스트링 앞부분에오는 부분을 뜻함 
    
  
    
   

    
    if(pathname==='/'){
      if(queryData.id===undefined){ // 한번에 내어쓰기하려면 쉬프트+탭

        fs.readdir('./data/',(err,files)=>{ // 파일의 목록 가져오기
          list=dataList(files);
          var title='welcome'
          var desc='hi'
          var template= templateHTML(title,list,`<h2>${title}</h2>${desc}`,`<a href="/create">생성</a>`);
          response.writeHead(200);
          response.end(template);
          })     
      }else{
        fs.readdir('./data/',(err,files)=>{
          list=dataList(files);
          
          fs.readFile(`./data/${queryData.id}`, 'utf-8',function(err,desc){   // data폴더안의 파일을 읽어와 해당 데이터를 desc변수에 저장
            var desc=sanitizeHtml(desc) // xss에 대비해 안에 있는 html태그를 필터링 해준다.
            // 두번째로 객체를 넣어 허락할 태그를 추가 할수 있다.
            var title=queryData.id;
            
            var template= templateHTML(title,list,`<h2>${title}</h2>${desc}`,` 
            <a href="/create">생성</a>  
            <a href="/update?id=${title}">수정</a> 
            <form action="/delete_process" method="post">
              <input type="hidden" name="id" value="${title}">
              <input type="submit" value="삭제">
            </form> 
            
            
            `);
            
            response.writeHead(200);
            response.end(template);
            
        });
          
          })
        

      }
      
    }else if(pathname ==='/create'){
      fs.readdir('./data/',(err,files)=>{
        list=dataList(files);
        var title='글 생성'
        var desc='hi'
        var template= templateHTML(title,list,`
        <form action="/create_process" method="post">
          <p><input type="text" name="title" placeholder="title"></p>
          <p>
            <textarea name="desc" placeholder="description"></textarea>
          </p>
          <p>
            <input type="submit">
          </p>
        </form>`,'');
        response.writeHead(200);
        response.end(template);
        })     
      
    }else if(pathname ==='/create_process'){  
      var body='';
      request.on('data',function(data){   
        // 데이터가 많을때를 대비해 데이터를 조각내 조금씩 body에 추가한다.
        body=body+data; // 콜백함수가 호출될때마다 body에 data 추가   

      });
      request.on('end',function(){
        var post=qs.parse(body) // ?key1=value1&key2=value2와 같은 URL의 쿼리 문자열 매개변수를 구문 분석하고 이를 JavaScript 개체로 변환
        var title= post.title;
        var desc=post.desc;
        fs.writeFile(`./data/${title}`,desc,'utf-8',function(err){  // 파일을 생성
          response.writeHead(302, {Location : 'http://localhost:3001/'}); //http 상태코드 302 리다이렉션 지정된 위치로 이동
          response.end(`success`);
        })
        // 리다이렉션 : 어떠한 처리를 한 다음에 다시 다른 페이지로 보내는것

      })

      
    }else if(pathname ==='/update'){
      fs.readdir('data/',(err,files)=>{
        list=dataList(files);
        fs.readFile(`data/${queryData.id}`, 'utf-8',function(err,desc){   // data폴더안의 파일을 읽어와 해당 데이터를 desc변수에 저장
          var desc=sanitizeHtml(desc)
          var title=queryData.id;
          var template= templateHTML(title,list,`
          <form action="/update_process" method="post">
          <input type="hidden" name="id" value=${title}>
            <p><input type="text" name="title" placeholder="title" value=${title}></p>
            <p>
              <textarea name="desc" placeholder="description" >${desc}</textarea>
            </p>
            <p>
              <input type="submit">
            </p>
          </form>`,``);
          response.writeHead(200);
          response.end(template);
          
      });
        
        })

    }else if(pathname ==='/update_process'){
      var body='';
      request.on('data',function(data){   
        // 데이터가 많을때를 대비해 데이터를 조각내 조금씩 body에 추가한다.
        body=body+data; // 콜백함수가 호출될때마다 body에 data 추가   

      });
      request.on('end',function(){
        var post=qs.parse(body) // ?key1=value1&key2=value2와 같은 URL의 쿼리 문자열 매개변수를 구문 분석하고 이를 JavaScript 개체로 변환
        var title= post.title;
        var desc=post.desc;
        var prevTitle=post.id;
        
        
        fs.rename(`./data/${prevTitle}`,`./data/${title}`,()=>{    //파일명 수정
          fs.writeFile('./data/'+title,desc,'utf8',function(err){ // 파일 내용 수정
            response.writeHead(302, { Location: `/?id=${encodeURIComponent(title)}` }); // 한국어가 주소에 있으면 이상하게 되므로 인코딩해서 리다이렉션
            response.end();
          });
        });
        // 리다이렉션 : 어떠한 처리를 한 다음에 다시 다른 페이지로 보내는것

      })

      

    }else if(pathname==='/delete_process'){
      var body='';
      request.on('data',function(data){   
        // 데이터가 많을때를 대비해 데이터를 조각내 조금씩 body에 추가한다.
        body=body+data; // 콜백함수가 호출될때마다 body에 data 추가   

      });
      request.on('end',function(){
        var post=qs.parse(body) // ?key1=value1&key2=value2와 같은 URL의 쿼리 문자열 매개변수를 구문 분석하고 이를 JavaScript 개체로 변환
        var prevTitle=post.id;
        console.log(prevTitle)
        fs.unlink(`data/${prevTitle}`,()=>{   // 파일 삭제
          response.writeHead(302, { Location: `/` }); 
          response.end();
        })

      })


    }
    
    else{
      response.writeHead(404);
        response.end('Not found');

    }
    

    

   

   
    
    
 
});
app.listen(3001);  // http서버를 구동시킴
 // 수정된걸 확인하려면 웹서버를 껏다 켜야함

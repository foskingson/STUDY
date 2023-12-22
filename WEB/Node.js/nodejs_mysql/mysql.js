var mysql = require('mysql');   // mysql 모듈 가져오기

var connection=mysql.createConnection({     // 연결 정보를 담은 객체를 생성
    host: 'localhost',
    user: 'root',
    password : 'al2732',
    database : 'nodesql'
});

connection.connect();   // 데이터베이스에 연결

connection.query('SELECT * FROM topic', function(err,result){
    if(err) {
        console.log(err)
    }
    console.log(result) // 쿼리 결과를 출력
})  // 첫번째인자인 sql문이 데이터베이스에 접속해 실행하고 두번째인자인 콜백함수가 호출

connection.end();
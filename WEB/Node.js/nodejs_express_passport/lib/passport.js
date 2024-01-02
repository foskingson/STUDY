module.exports=function(app){
var passport = require('passport')  //passport 가져오기 passport는 세션을 사용하기 때문에 세션뒤에 배치하는게 좋음
,LocalStrategy=require('passport-local').Strategy; // local전략은 아이디와 비밀번호를 통해 로그인하는 방식
var flash=require('connect-flash')
app.use(passport.initialize()); //express에 passport 미들웨어 설치하기
app.use(passport.session());
app.use(flash())  //flash미들웨어 사용


var authData={  // 현실에서는 소스코드안에 계정정보가 있으면 안됨
    id:'foskingson',
    password:'al2732',  // 패스워드는 평문이면 안됨
    nick:'jo'
  }


passport.serializeUser(function(user, done) { //로그인 성공했을때 한번 호출되면서 세션정보에 저장하는 코드
 
  done(null,user.id); // 세션정보에 passport를 통해 user.id값을 저장함
  //done(null, user.id);
});

passport.deserializeUser(function(id, done) {   //필요할때 세션정보를 조회하는 코드, 로그인후 페이지 방문할때마다 콜백호출 
  done(null,authData)
  /*User.findById(id, function(err, user) { <<이건 데이터베이스에 조회해서 유저정보를 가져오는 코드
    done(err, user);
  });*/
});



passport.use(new LocalStrategy(
  {
    usernameField:'id',   // form로 받아오는 아이디 비밀번호 이름 지정
    passwordField:'password'
  },
  function(username, password, done) {    // 폼에서 전송받은 값을 인자로 받음
    
    if(username!==authData.id ){
      return done(null, false, {
        message: 'Incorrect username.'

      });
    }
    if(password!==authData.password){
      return done(null, false, {
        message: 'Incorrect password.'
      });
    }
    return done(null, authData,{
        message: '로그인 성공~'
    }); //serializeUser메서드의 첫번째 인자로 authData가 들어감
    }
));
return passport;
}


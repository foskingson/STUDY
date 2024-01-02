module.exports = {
    IsOwner:function(request,response){
        if(request.user){
          return true;
        }else{
          return false;
        }
    },
    StatusUI:function(request,response){
        var authStatusUI='<a href="/auth/login">login</a>'
          
        if(this.IsOwner(request,response)){
          authStatusUI=`${request.user.nick} | <a href="/auth/logout">logout</a>`
        }
        return authStatusUI
      
      }
  }

// this를 사용할때는 화살표함수와 일반함수의 차이에대해 인지하고 사용할것
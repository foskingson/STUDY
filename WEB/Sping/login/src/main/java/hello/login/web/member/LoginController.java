package hello.login.web.member;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.login.LoginForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;    // final 안빼게 주의

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm form) {
        return "/login/loginForm";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindResult,HttpServletResponse response) {
        if(bindResult.hasErrors()){
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if(loginMember==null){
            bindResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login/loginForm";
        }

        // 로그인 성공 처리 TODO

        // 쿠키 시간정보 안주면 세션 쿠키 (브라우저 종료시 모두 사라짐)
        Cookie idCookie = new Cookie("memberId",String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response,"memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    
}

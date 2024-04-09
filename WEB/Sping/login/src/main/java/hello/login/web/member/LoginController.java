package hello.login.web.member;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.login.LoginForm;
import hello.login.web.session.SessionManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm form) {
        return "/login/loginForm";
    }
    
    //@PostMapping("/login")
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

    //@PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindResult,HttpServletResponse response) {
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

        // 세션 관리자를 통해 세션 생성후 데이터 보관
        sessionManager.createSession(loginMember, response);

        
        return "redirect:/";
    }

    //@PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindResult,HttpServletRequest request) {
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
        // 세션이 있으면 세션 반환 없으면 신규 세션 생성 / false를 인자로 주면 세션이 없을때 세션을 생성하지 않고 null 반환
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 저장
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindResult,HttpServletRequest request,
    @RequestParam(defaultValue = "/") String redirectURL) {
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
        // 세션이 있으면 세션 반환 없으면 신규 세션 생성 / false를 인자로 주면 세션이 없을때 세션을 생성하지 않고 null 반환
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 저장
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        
        return "redirect:"+redirectURL;
    }


    

    //@PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response,"memberId");
        return "redirect:/";
    }

    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request,HttpServletResponse response){
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }


    private void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    
}

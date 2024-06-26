package hello.login.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String home() {
        return "home";
    }

    //@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId",required = false) Long memberId,Model model) {

        if(memberId==null){
            return "home";
        }

        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember==null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
        
    }

    //@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request,Model model) {
        // 세션 관리자 저장된 회원정보 조회
        Member member = (Member)sessionManager.getSession(request);

        //로그인
        if(member==null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
        
    }

    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if(session==null){
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("member", loginMember);
        return "loginHome";
        
    }

    //@GetMapping("/")
    public String homeLoginV3Spring(
        @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,Model model) {
        if(loginMember==null){
            return "home";
        }
     

        model.addAttribute("member", loginMember);
        return "loginHome";
        
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember,Model model) {
        if(loginMember==null){
            return "home";
        }
    
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
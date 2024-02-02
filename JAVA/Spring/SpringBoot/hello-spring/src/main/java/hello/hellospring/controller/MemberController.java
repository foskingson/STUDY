package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller //@Componet와 관련된 에노테이션
public class MemberController {
    
    private final MemberService memberService;
    // 컨트롤러는 서비스를 의존하고있고 서비스는 레포지토리를 의존하고 있다.

    // 스프링 컨테이너는 스프링이 실행될때 같이 실행된다.
    @Autowired // @Autowired를 통해 스프링컨테이너에 있는 MemberService를 가져다가 연결을 시켜준다. 컴포넌트 스캔을 통한 방식
    public MemberController(MemberService memberService){   //new를 통해 여러개를 만들어 쓰기보다 의존성을 만들어 여기저기 파일에서 memberService를 하나처럼 사용한다.
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        System.out.println(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String checkMember(Model model) {
        List<Member> member = memberService.findMembers();
        model.addAttribute("members",member);
        return "members/checkMember";
    }
    
    
}

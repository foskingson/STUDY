package mvcStudy.mvcStudy.web.springmvc.v3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("new-form")
    public String newForm(){
        return "new-form";
    }

    @PostMapping("save")
    public String save(
        @RequestParam("username") String username,
        @RequestParam("age") int age,
        Model model
        )  {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
  }

    @Get
    public String requestMethodName(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}

package mvcStudy.mvcStudy.web.springmvc.v1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;


@Controller
public class SpringMemberListControllerV1 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView requestMethodName() {
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);
        return mv;
    }
    
}

package mvcStudy.mvcStudy.web.springmvc.v1;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.ModelView;

@Controller

public class SpringMemberSaveControllerV1 {
    MemberRepository memberRepository = MemberRepository.getInstance();
    
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response)  {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
  }
}

package mvcStudy.mvcStudy.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
    
}

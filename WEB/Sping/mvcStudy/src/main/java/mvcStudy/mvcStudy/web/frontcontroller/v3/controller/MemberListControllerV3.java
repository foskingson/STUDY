package mvcStudy.mvcStudy.web.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;

import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.ModelView;
import mvcStudy.mvcStudy.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();   
        
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);

        return mv;
        }
    
}

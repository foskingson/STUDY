package mvcStudy.mvcStudy.web.frontcontroller.v4.controller;

import java.util.List;
import java.util.Map;

import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.ModelView;
import mvcStudy.mvcStudy.web.frontcontroller.v4.ControllerV4;

public class MemberListControllerV4 implements ControllerV4{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String,Object> model) {
        List<Member> members = memberRepository.findAll();   
        model.put("members", members);

        return "members";
        }
    
}

package mvcStudy.mvcStudy.web.frontcontroller.v3.controller;

import java.util.Map;

import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.ModelView;
import mvcStudy.mvcStudy.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
  }
    
}

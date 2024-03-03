package mvcStudy.mvcStudy.web.frontcontroller.v2.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.MyView;
import mvcStudy.mvcStudy.web.frontcontroller.v2.ControllerV2;

public class MemberListControllerV2 implements ControllerV2{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
    
}

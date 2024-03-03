package mvcStudy.mvcStudy.web.frontcontroller.v2.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcStudy.mvcStudy.domain.member.Member;
import mvcStudy.mvcStudy.domain.member.MemberRepository;
import mvcStudy.mvcStudy.web.frontcontroller.MyView;
import mvcStudy.mvcStudy.web.frontcontroller.v2.ControllerV2;

public class MemberSaveControllerV2 implements ControllerV2{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        memberRepository.save(member);

        // Model에 데이터를 보관
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");

    }
    
}

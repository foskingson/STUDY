<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mvcStudy.mvcStudy.domain.member.Member, mvcStudy.mvcStudy.domain.member.MemberRepository" %>

<%
    //request,response 사용가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<html>
<head>
    <meta charset="UTF-8" />
    <title>title</title>
</head>
<body>
    성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
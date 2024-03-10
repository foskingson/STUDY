<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>title</title>
</head>
<body>
    성공
<ul>
    <li>id=${member.id}</li>    <!--  Spring MVC의 ModelAndView 객체에서 해당 키(key)에 해당하는 값(value)을 가져와서 템플릿(여기서는 JSP 파일)에 출력 -->
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
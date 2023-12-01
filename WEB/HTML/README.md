# HTML :yellow_book:
- HTML은 웹페이지를 생성하고 구성하기 위한 표준 마크업 언어이다.

<br>
<br>

### 기본 구조
``` HTML
<!DOCTYPE html> <!-- <!DOCTYPE html>: 문서 유형과 HTML 버전을 정의 -->
<html> 
<head>
    <meta charset="UTF-8"> <!-- 텍스트 인코딩을 위해 문자 세트를 UTF-8로 설정 -->
    <title>제목</title>
</head>
<body>
    <!-- 내용  -->
</body>
</html>
```
<br>

### 블록태그와 인라인태그
- 블록 태그 : 사용 가능한 전체 너비를 차지하고 줄바꿈이 일어난다.
    - 블록태그의 예 : 	&#60;div> , &#60;p> , &#60;h1>~&#60;h6> , &#60;ul> , &#60;ol> , &#60;li> , &#60;table> , &#60;tr> , &#60;td> 등
- 인라인 태그 : 필요한 만큼의 너비만 차지하고 줄바꿈이 일어나지 않는다.
    - 인라인 태그의 예 : &#60;span> , &#60;a> , &#60;strong> , &#60;img> , &#60;br> 등 
<br>

### h1~h6 태그
- 섹션, 문단의 제목을 나타내고 숫자가 작을수록 글자의 크기가 크다. <br>
``` HTML
<h1>This is a Heading 1</h1>
<h2>This is a Heading 2</h2>
<!-- ... -->
<h6>This is a Heading 6</h6>
```
<br>

### p 태그
- 문단을 나타내는 태그이다. <br>
+ `<p>문단</p>` <br>
<br>

### ul,ol 태그
- ol태그는 1,2,3...처럼 차례대로 순서가 나오는 목록이고 ul은 순서가 나오지 않는 목록이다. <br>
``` HTML
<ul>
    <li>Item 1</li> 
    <li>Item 2</li>
</ul>

<ol>
    <li>Item 1</li>
    <li>Item 2</li>
</ol>
```
<br>

### a 태그
- 링크된 주소로 연결해주는 태그이다. <br>
+ `<a href="https://example.com">Visit Example.com</a>`  <br>
<br>


### img 태그
- 웹페이지에 이미지를 보여주는 태그이다. <br>
+ `<img src="image.jpg" alt="이미지 설명">`  <br>
<br>


### form 태그
- form 태그는 데이터를 입력받아 해당 데이터를 서버에 제출한다. <br>
- action 속성 : 데이터가 전달될 URL을 지정한다. <br>
- method 속성 : 양식을 제출할때  사용할 HTTP 메서드이다 <br>
    - 일반적인 메서드 값으로 get과 post가 있다.  <br>
    - get : 256~4096 byte까지만 전송 가능하고 url에 데이터를 추가해서 보낸다. <br>
    - post : 길이 제한이 없고 body에 데이터를 추가해서 보낸다.<br>
``` HTML
<form action="/submit" method="post">
        <!-- action: 폼 데이터가 전송될 서버의 URL을 지정합니다. -->
        <!-- method: 폼 데이터를 서버로 전송하는 HTTP 메소드를 지정합니다. (get 또는 post) -->

        <!-- 텍스트 입력 필드 -->
        <label for="username">사용자 이름:</label>
        <input type="text" id="username" name="username" placeholder="사용자 이름을 입력하세요">

        <!-- 비밀번호 입력 필드 -->
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">

        <!-- 체크박스 -->
        <input type="checkbox" id="subscribe" name="subscribe" value="yes">
        <label for="subscribe">뉴스레터 구독</label>

        <!-- 라디오 버튼 -->
        <input type="radio" id="male" name="gender" value="male">
        <label for="male">남성</label>
        <input type="radio" id="female" name="gender" value="female">
        <label for="female">여성</label>

        <!-- 제출 버튼 -->
        <button type="submit">제출</button>
    </form>
```
<br>
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


### h1~h6 태그
- 섹션, 문단의 제목을 나타내고 숫자가 작을수록 글자의 크기가 크다. <br>
``` HTML
<h1>This is a Heading 1</h1>
<h2>This is a Heading 2</h2>
<!-- ... -->
<h6>This is a Heading 6</h6>
```

### p 태그
- 문단을 나타내는 태그이다. <br>
+ `<p>문단</p>` <br>
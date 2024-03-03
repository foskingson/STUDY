# 웹 보안
> 인터넷을 기반으로 구현된 서비스 중 HTTP를 이용하여 정보를 공유하는 서비스를 웹이라 한다. 정보를 제공하는 주체를 웹 서버 (Web Server), 정보를 받는 이용자를 웹 클라이언트 (Web Client)라고 부른다. 과거에는 단순히 정보를 보여주는 것에서 그쳤다면, 현재는 정보를 검색하고 직접 제품을 구매할 수 있도록 변화하였다. 웹에서 처리하는 정보 자산들이 많아짐에 따라 이들을 안전하게 보관하고 처리해야 할 필요성도 함께 증가하였다.

<br>
<br>
<br>

## Same Origin Policy (SOP)
> SOP(동일 출처 정책)는 웹 보안 모델 중 하나로, 웹 브라우저가 스크립트가 있는 문서나 리소스가 동일한 오리진에서 로드되어야만 한다는 규칙을 적용한다. 오리진(Origin)이란 프로토콜, 호스트, 포트로 구성된 URL의 조합을 말한다. 즉 SOP는 다른 오리진의 악의적인 페이지가 클라이언트의 권한을 이용해 대상 사이트에 HTTP 요청을 보내고, HTTP 응답 정보를 획득 하는 코드를 실행하는 것을 방지한다. 기본적으로 모든 주요 웹 브라우저에서 구현되어 있다.

- 오리진 구분 방법 (https://same-origin.com/ 라는 오리진을 아래 URL과 비교)
    ```
    1. https://same-origin.com/frame.html => Same Origin => Path만 다름

    2. http://same-origin.com/frame.html => Cross Origin => Scheme이 다름

    3. https://cross.same-origin.com/frame.html => Cross Origin => Host가 다름

    4. https://same-origin.com:1234/ => Cross Origin => Port가 다름
    ```

<br>

- SOP 적용 예시
``` html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SOP 예제</title>
</head>
<body>
<iframe id="frame1" src="https://www.example.com"></iframe>
<iframe id="frame2" src="https://www.otherdomain.com"></iframe>

<script>
  // frame1의 내용을 가져와 콘솔에 출력
  const frame1Content = document.getElementById('frame1').contentDocument.body.innerHTML;
  console.log(frame1Content); // 성공

  // frame2의 내용을 가져와 콘솔에 출력 (SOP에 의해 차단됨)
  try {
    const frame2Content = document.getElementById('frame2').contentDocument.body.innerHTML;
    console.log(frame2Content);
  } catch (error) {
    console.error(error); // 동일 출처 정책 오류 발생
  }
</script>
</body>
</html>
```

<br>

### Cross Origin Resource Sharing (CORS)
> 교차 출처 리소스 공유 (Cross Origin Resource Sharing, CORS)는 HTTP 헤더에 기반하여 Cross Origin 간에 리소스를 공유하는 방법이다. 발신측에서 CORS 헤더를 설정해 요청하면, 수신측에서 헤더를 구분해 정해진 규칙에 맞게 데이터를 가져갈 수 있도록 설정한다.

<br>

- CORS 헤더
    - 표를 살펴보면, 발신측에서 POST 방식으로 HTTP 요청을 보냈으나, OPTIONS 메소드를 가진 HTTP 요청이 전달된 것을 확인할 수 있다. 이를 CORS preflight라고 하며, 수신측에 웹 리소스를 요청해도 되는지 질의하는 과정이다.

| 헤더                           | 설명                                                                                     |
|------------------------------|------------------------------------------------------------------------------------------|
| Access-Control-Allow-Origin  | 헤더 값에 해당하는 출처에서 오는 요청만 처리됩니다.                                      |
| Access-Control-Allow-Methods | 헤더 값에 해당하는 메서드에 대한 요청만 처리됩니다.                                       |
| Access-Control-Allow-Credentials | 쿠키 사용 여부를 결정합니다. 예를 들어, 쿠키 사용을 허용합니다.                        |
| Access-Control-Allow-Headers | 헤더 값에 해당하는 헤더가 사용 가능한지를 나타냅니다.                                     |

```
HTTP/1.1 200 OK
Access-Control-Allow-Origin: https://dreamhack.io
Access-Control-Allow-Methods: POST, GET, OPTIONS
Access-Control-Allow-Credentials: true
Access-Control-Allow-Headers: Content-Type
```
<br>

### JSON with Padding (JSONP)
> 기본적으로 이미지나 자바스크립트, CSS 등의 리소스는 SOP에 구애 받지 않고 외부 출처에 대해 접근을 허용한다. JSONP 방식은 이러한 특징을 이용해 \<script> 태그로 Cross Origin의 데이터를 불러온다. 하지만 \<script> 태그 내에서는 데이터를 자바스크립트의 코드로 인식하기 때문에 Callback 함수를 활용해야 한다. Cross Origin에 요청할 때 callback 파라미터에 어떤 함수로 받아오는 데이터를 핸들링할지 넘겨주면, 대상 서버는 전달된 Callback으로 데이터를 감싸 응답한다.

<br>
<br>
<br>

## XSS (Cross Site Scripting)
> XSS는 클라이언트 사이드 취약점 중 하나로, 공격자가 웹 리소스에 악성 스크립트를 삽입해 이용자의 웹 브라우저에서 해당 스크립트를 실행할 수 있다. 해당 취약점은 SOP 보안 정책이 등장하면서 공격이 이전에 비해 힘들어 졌지만 이를 우회하는 다양한 기술로 XSS 공격은 지속되고 있다.

<br>

- 공격 과정
```
1. 공격자는 취약한 웹 애플리케이션에 악의적인 스크립트를 삽입한다.

2. 희생자가 해당 웹 페이지를 방문할 때, 악성 스크립트가 함께 로드되어 실행된다.

3. 악성 스크립트는 희생자의 세션 쿠키를 탈취하거나, 사용자의 개인 정보를 도용하거나, 다른 악의적인 동작을 수행할 수 있다.
```

<BR>

- 공격 형태
```
- Stored XSS : 악성 스크립트를 서버(데이터베이스 또는 파일 등의 형태)에 심어놔 서버의 응답이 담겨 오는 공격이다. 대표적으로 게시물과 댓글에 악성 스크립트 업로드하는 방식이 있다.

- Reflected XSS : 악성 스크립트를 URL에 삽입해 서버의 응답이 담겨오는 공격이다. 대표적으로 게시판의 검색창에 악성 스크립트를 포함해 검색하는 방식이 있다. 공격을 위해서는 다른 이용자를 악성 스크립트가 포함된 링크에 접속하도록 유도해야 한다. 이용자에게 링크를 직접 전달하는 방법은 악성 스크립트 포함 여부를 이용자가 눈치챌 수 있기 때문에 주로 Click Jacking 또는 Open Redirect 등 다른 취약점과 연계 한다.

- DOM-based XSS : 악성 스크립트를 URL Fragment에 삽입하는 공격이다.

- Universal XSS : 클라이언트의 브라우저 혹은 브라우저 플러그인에서 발생하는 취약점으로 SOP 정책을 우회하는 공격이다.
```


















<br><br><br><br><br>

##### 드림핵 공부내용 정리
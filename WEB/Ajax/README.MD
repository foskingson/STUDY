# Ajax (Asynchronous JavaScript and XML)
- Ajax는 서버와 클라이언트간에 정보를 비동기적으로 전달하기 위해 자바스크립트에서 사용하는 하나의 라이브러리이다.
- Ajax는 웹서버와 통신하는 라이브러리기 때문에 간단하게 비트나미를 사용해 아파치와 mysql을 시작해서 웹서버를 가동하고 사용했다.
- SPA를 구성하는데 유용하게 사용된다.




### Fetch API
- Fetch API는 네트워크 전체에서 리소스를 가져오기 위한 인터페이스를 제공하는 자바스크립트 API이다. 이러한 Fetch APi를 통해 ajax의 비동기 통신을 구현할 수 있다.
```javascript 간단한 Fetch APi를 통한 Ajax 구현
<input type="button" value="fetch" onclick="
            fetch('css')                // 서버에 css파일에 대한 응답을 요청함
            .then(resp=>resp.text())    // 응답이 성공적으로 끝나면 response객체를 받아 문자열로 변환 (then을 통해 비동기처리를 시켜줌)
            .then(text=>{               //  문자열로 변환된걸 result 객체로 받아 아래의 함수를 실행한다
                alert(text);
                document.querySelector('article').innerHTML=text;
            })     
        ">
```

### 해시뱅
+ `<a href="#!hash"></a>` SPA에서 이런식으로 링크를 걸어 북마크로 활용할 수 있다.
-  해시 느낌표라고도 알려진 Hashbang은 단일 페이지 애플리케이션(SPA)에서 AJAX(Asynchronous JavaScript and XML) 기능을 활성화하기 위해 웹 개발에 사용되는 기술
- 장점
    - Hashbang은 History API를 지원하지 않는 이전 브라우저와 호환됩니다. 이렇게 하면 이전 브라우저를 사용하는 사용자도 문제 없이 SPA를 경험할 수 있다.
    - 해시뱅은 북마크 및 공유 가능한 URL을 생성하는 데 자주 사용됩니다. 해시뱅 이후의 모든 항목은 조각 식별자로 처리되므로 전체 페이지 다시 로드를 트리거하지 않습니다. 이를 통해 사용자는 애플리케이션의 특정 상태를 북마크에 추가하고 해당 URL을 공유할 수 있습니다.
- 단점
    - Hashbang URL은 미학적으로 덜 만족스럽고 사용자 친화적인 것으로 간주될 수 있습니다. 사용자는 깔끔한 URL에 비해 혼란스럽거나 읽기 어렵다고 느낄 수 있다.




폴리필(Polyfill)은 웹 개발에서 사용되는 용어로, 웹 브라우저의 호환성 문제를 해결하기 위해 추가로 제공되는 코드
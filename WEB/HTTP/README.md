# HTTP (Hypertext Transfer Protocol) 🌏
>  World Wide Web에서 데이터 통신의 기반 역할을 하는 응용 프로그램 계층 프로토콜이다. 메시지의 형식화 및 전송 방법과 웹 서버 및 브라우저가 다양한 명령에 응답하는 방법을 정의한다. 쉽게 말하면 HTTP를 통해 거의 모든 형태의 데이터를 주고 받을 수 있다. 현재 가장 많이 사용하는 HTTP버전은 HTTP/1.1이고 HTTP/3까지 나와 있다. HTTP/3은 UDP를 이용한 방식으로 HTTP/1.1의 성능을 개선한 버전이다.

<br>

- 특징
    1. 클라이언트 서버 구조 : 클라이언트는 서버에 요청을 보내고, 서버는 해당 요청을 처리하고 응답을 다시 보낸다.
    2. Stateless(무상태) : 클라이언트에서 서버로의 각 요청은 이전 요청에 대한 지식 없이 독립적으로 처리된다. 다만 로그인과 같은 stateful이 필요한 경우 세션,쿠기를 이용해 최소한으로 stateful을 설계한다. stateless로 설계한다면 상태를 저장하지 않기 때문에 중간에 서버가 바뀌어도 문제가 생기지 않는다.
    3. 비연결성 : HTTP는 기본적으로 연결을 유지하지 않고 응답을 완료하면 연결을 끊는다. 이러한 방식은 수천명이 서비스를 사용해도 서버에서 동시에 처리하는 요청을 줄일 수 있다. 다만 하나의 요청마다 TCP연결을 계속 새로 맺어야하는 문제가 있었는데 이를 HTTP지속연결로 해결하였다. 
        - 예를 들면 HTML,JS,CSS에 각각에 대한 요청을 모두 따로 연결을 맺고 응답을 했었는데 지속연결을 사용해 모든 요청에 응답을 해주고 연결을 종료한다.

<br>
<br>
<br>

## HTTP 메시지
> HTTP(Hypertext Transfer Protocol) 메시지는 클라이언트와 서버가 웹을 통해 서로 통신하는 수단이다. 이는 클라이언트(일반적으로 웹 브라우저)와 웹 사이트 또는 웹 애플리케이션이 호스팅되는 서버 간에 정보를 전달하는 요청 및 응답으로 구성된다.

<br>

- HTTP 요청 메시지 구조

| 구성요소        | 설명                                                    |
|----------------|---------------------------------------------------------|
| 시작 라인      | HTTP 메서드, URL 및 HTTP 버전이 포함된 요청의 첫 번째 줄입니다. |
| 헤더 라인   | 요청에 대한 메타데이터를 제공하는 키-값 쌍입니다 (예: Content-Type, User-Agent). |
| 공백 라인   CRLF   | 헤더와 메시지 본문을 구분하는 빈 줄입니다.                 |
| message body    | 클라이언트가 서버로 보내는 선택적 데이터로, 주로 POST 또는 PUT 요청에서 사용됩니다. |

<br>

- HTTP 응답 메시지 구조

| 구성요소        | 설명                                                    |
|----------------|---------------------------------------------------------|
| 시작 라인      | HTTP 버전, 상태 코드 및 상태 메시지가 포함된 응답의 첫 번째 줄입니다. |
| 헤더 라인   | 응답에 대한 메타데이터를 제공하는 키-값 쌍입니다 (예: Content-Type, Content-Length). |
| 공백 라인 CRLF     | 헤더와 메시지 본문을 구분하는 빈 줄입니다.                 |
| message body    | 서버가 클라이언트로 보내는 요청된 리소스 또는 오류 메시지가 포함된 본문입니다. |

<br>
<br>
<br>

## HTTP 메서드
> HTTP 메서드는 클라이언트가 웹 서버에 있는 리소스에 대해 수행할 수 있는 작업이다. 각 HTTP 요청에는 일반적으로 클라이언트가 지정된 리소스에 대해 수행하려는 작업 유형을 나타내는 메서드가 포함된다.

<br>

1. GET : 지정된 리소스에서 데이터를 요청합니다. GET 요청은 데이터 검색만 해야 하며 서버에 다른 영향을 주어서는 안된다. 서버에 전달하고 싶은 데이터는 쿼리를 통해서 전달한다. 
    - 메시지 전달 => 서버 도착 => 응답 데이터 순서로 진행된다.

2. POST : 처리할 데이터를 지정된 리소스에 전달한다. POST 요청으로 인해 리소스 생성 또는 업데이트와 같은 서버 상태가 변경될 수 있다. 메시지 바디를 통해 서버로 요청 데이터를 전달한다. 주로 신규 데이터 등록, 프로세스 처리 등에 사용된다. 
    - 메시지 전달 => 데이터 처리(예: 신규 리소스 생성) => 응답 데이터 순서로 진행된다.
    - 리소스 URI에 POST 요청이 오면 요청 데이터를 어떻게 처리할지 리소스마다 따로 정해야 한다.

3. PUT : 지정된 리소스를 새 데이터로 업데이트한다. 리소스가 있으면 대체하고 없으면 생성한다. 쉽게 말하면 덮어버린다. 부분적으로 수정하려면 PATCH를 사용한다.
    - 클라이언트가 리소스를 식별한다. 클라이언트가 리소스 위치를 알고 URI를 지정한다.

4. PATCH : 리소스를 부분 수정한다.

5. DELETE : 리소스를 제거한다.

<br>

### 자주 사용되는 HTTP 요청 메시지를 통한 클라이언트에서 서버로 데이터를 전달하는 방법
1. GET - 쿼리 파라미터
    - /url**?username=hello&age=20**
    - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
    - 예\) 검색, 필터, 페이징등에서 많이 사용하는 방식
2. POST - HTML Form
    - content-type: application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파리미터 형식으로 전달 username=hello&age=20
    - 예\) 회원 가입, 상품 주문, HTML Form 사용
3. HTTP message body에 데이터를 직접 담아서 요청
    - HTTP API에서 주로 사용, JSON, XML, TEXT
    - 데이터 형식은 주로 JSON 사용
    - POST, PUT, PATCH
    - Rest API


<br>

### 클라이언트에서 서버로 데이터 전송
1. 쿼리 파라미터를 통한 데이터 전송
    - GET
    - 주로 정렬 필터(검색어)
2. 메시지 바디를 통한 전송
    - POST, PUT, PATCH
    - 회원가입, 상품 주문, 리소스 등록, 리소스 변경

<BR>

### HTTP API 설계 
- 회원 관리 시스템 (API 설계 - POST기반 등록)
    - 회원 목록 /members -> GET
    - 회원 등록 /members -> POST
    - 회원 조회 /members/{id} -> GET
    - 회원 수정 /members/{id} -> PATCH, PUT, POST
    - 회원 삭제 /members/{id} -> DELETE


- POST 신규 자원 등록 특징   **[대부분 사용]**
    - 클라이언트는 등록될 리소스의 URI를 모른다.

        - 회원등록 /members => POST
        - POST /members
    - 서버가 새로 등록된 리소스 URI를 생성해준다.
        - HTTP/1.1 201 Created
        - Location: /members/100
    - 컬렉션
        - 서버가 관리하는 리소스 디렉토리이다.
        - 서버가 리소스 URI를 생성하고 관리한다.
        - 여기서 컬렉션은 /members 이다.


<br>
<br>


- 파일 관리 시스템 (API 설계 - PUT기반 등록)
    - 파일 목록 /files -> GET
    - 파일 조회 /files/{filename} -> GET
    - 파일 등록 /files/{filename} -> PUT
    - 파일 삭제 /files/{filename} -> DELETE 
    - 파일 대량 등록 /files -> POST

- PUT 신규 자원 등록 특징
    - 클라이언트가 리소스 URI를 알고 있어야 한다.
        - 파일 등록 /files/{filename} -> PUT
        - PUT /files/star.jpg

    - 클라이언트가 직접 리소스의 URI를 지정한다.
    - 스토어(Store)
        - 클라이언트가 관리하는 리소스 저장소
        - 클라이언트가 리소스의 URI를 알고 관리
        - 여기서 스토어는 /files

<br>
<br>

- HTML FORM 사용
    - 회원 목록 /members -> GET
    - 회원 등록 폼 /members/new -> GET
    - 회원 등록 /members/new, /members -> POST
    - 회원 조회 /members/{id} -> GET
    - 회원 수정 폼 /members/{id}/edit -> GET
    - 회원 수정 /members/{id}/edit, /members/{id} -> POST
    - 회원 삭제 /members/{id}/delete -> POST

- HTML FORM 사용
    - GET, POST만 지원하므로 제약이 있다.
    - 이런 제약을 해결하기 위해 동사로 된 리소스 경로 사용한다.
    - POST의 /new, /edit, /delete가 컨트롤 URI
    - HTTP 메서드로 해결하기 애매한 경우 사용(HTTP API 포함)

<br>
<br>
<br>

## HTTP API URL 설계
> HTTP API URL을 설계하려면 클라이언트와 서버 간의 효율적인 통신을 촉진하기 위해 엔드포인트와 리소스를 논리적이고 일관된 방식으로 구성해야 한다.
1. **리소스를 나타내는 명사 사용:** URL은 동작이 아닌 리소스를 나타내야 한다. 리소스를 명사로 표현하고 해당 리소스에 대한 동작을 HTTP 메서드(GET, POST, PUT, DELETE)로 지정한다.
2. **컬렉션에는 복수형 명사 사용:** 리소스의 컬렉션을 다룰 때는 복수형 명사를 사용한다. 예를 들어, /users는 사용자 리소스의 컬렉션을 나타낸다.
3. **구체적인 리소스 식별자 사용:** 개별 리소스에 대한 액세스 또는 조작을 허용하는 구체적인 식별자를 포함한다. 예를 들어, /users/{id}는 고유한 ID로 식별되는 특정 사용자를 나타낼 수 있다.
4. **계층적 데이터에 하위 리소스 사용:** 리소스가 계층적 관계를 갖는 경우 URL 구조에 이 계층 구조를 나타내기 위해 하위 리소스를 사용한다. 예를 들어, 특정 조직 내의 사용자를 나타내기 위해 /organizations/{orgId}/users를 사용할 수 있다.
5. **URL을 계층적이고 예측 가능하게 유지:** URL을 계층적으로 구성하여 데이터 구조를 반영하고 클라이언트에게 예측 가능하게 만든다. 복잡하거나 깊게 중첩된 URL을 피한다.
6. **쿼리 매개변수를 사용하여 필터링, 정렬 및 페이지네이션:** 필터링, 정렬 및 페이지네이션과 같은 작업에는 쿼리 매개변수를 사용하여 URL 경로에 포함하는 대신 사용한다. 예를 들어, /products?category=electronics&sort=price&limit=10이다.

<br>
<br>

- 예시: 도서 관리를 위한 HTTP API를 설계한다고 가정한다. URL을 구성하는 방법은 다음과 같다.

    - 모든 책 목록: GET /books
    - 특정 책 검색: GET /books/{bookId}
    - 새 책 추가: POST /books
    - 기존 책 수정: PUT /books/{bookId}
    - 책 삭제: DELETE /books/{bookId}
    - 모든 저자 목록: GET /authors
    - 특정 저자 검색: GET /authors/{authorId}
    - 특정 저자가 쓴 모든 책 목록: GET /authors/{authorId}/books

> 이 예에서는 /books가 책의 컬렉션을 나타내고, /authors가 저자의 컬렉션을 나타내며, /authors/{authorId}/books는 특정 저자가 쓴 책을 나타낸다. 각 URL은 직관적이고 RESTful하게 구조화되어 있어 클라이언트가 API를 이해하고 상호 작용하기 쉽다.

<br>
<br>
<br>


## HTTP 상태 코드
> 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능이다. 만약 클라이언트가 인식할 수 없는 상태코드를 서버가 반환하면 상위 상태코드로 해석해서 처리한다. 예를 들어 인식할 수 없는 299를 반환하면 상위 상태코드인 200으로 해석된다.

- 1xx (Informational): 요청이 수신되어 처리중
    - 거의 사용되지 않는다.

- 2xx (Successful): 요청 정상 처리했다.
    - 200 : OK , 요청성공
    - 201 : Created , 요청이 성공해서 새로운 리소스가 생성됨 
    - 202 : Accepted , 요청이 접수되었으나 아직 처리가 완료되지 않았음
    - 204 : No Content , 서버가 요청을 성공적으로 수행했지만 응답 페이로드 본문에 보낼 데이터가 없음

- 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요
    - 3xx 응답의 결과에 Location 헤더가 있으면 그 위치로 자동 이동한다.
    - 영구 리다이렉션(301, 308) : 특정 리소스의 URI가 영구적으로 변경됨. 예를 들면 로그인 후에 홈화면으로 리다이렉트 되는 경우
    - 일시 리다이렉션(302, 307, 303) : 일시적인 변경. 웹에 표시되는건 똑같지만 주소만 변경된다. 상품 주문을 하면 POST로 데이터가 서버로 넘어갔는데 여기서 새로고침을 하면 또 POST로 데이터를 보내 중복 주문을 할 수도 있다. 따라서 POST로 주문 후에 주문 결과화면을 GET메서드로 리다이렉트 하여 중복 주문을 방지한다. PRG(Post/Redirect/Get) 
    - 특수 리다이렉션(304) : 결과 대신 캐시를 사용한다. 클라이언트에게 리소스가 수정되지 않았음을 알려주어 클라이언트가 지정된 캐시를 재사용한다. 캐시로 리다이렉트 한다.

- 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
    - 400 : 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리 할 수 없다. 요청 파라미터가 잘못되거나, API 스펙에 맞지 않을 때 일어난다.
    - 401 : 클라이언트가 해당 리소스에 대한 인증이 필요하다.
    - 403 : 서버가 요청을 이해했지만 승인을 거부한다. 인증 자격 증명은 있지만 접근 권한이 없는 경우이다.
    - 404 : 요청 리소스가 서버에 없다.

- 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함
    - 500 : 서버 내부 문제 오류이다. 애매한 경우 대부분 500 오류이다.
    - 503 : 서비스 이용 불가. 서버가 일시적인 과부하 또는 작업으로 잠시 요청을 처리할 수 없다.

<br>
<br>
<br>

## HTTP 헤더
> HTTP 헤더는 HTTP 전송에 필요한 모든 부가 정보를 담고 있다. <BR>
예를 들어 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증 ,요청 클라이언트, 서버 정보, 캐시 관리 정보 등이 있다.

1. 요청 헤더(Request Header): 클라이언트(예: 웹 브라우저)가 서버에게 요청할 때 사용된다. 클라이언트가 서버로 보내는 요청의 메타데이터를 포함하며, 요청의 목적이나 요청을 처리하는 방법 등에 대한 정보를 제공한다. 몇 가지 일반적인 요청 헤더는 다음과 같다.

    ```
    Host: 요청한 호스트의 도메인 이름 또는 IP 주소를 지정합니다.
    User-Agent: 클라이언트의 소프트웨어(예: 웹 브라우저)에 대한 정보를 제공합니다.
    Accept: 클라이언트가 서버로부터 받기를 원하는 콘텐츠 유형을 지정합니다.
    ```
    ----------------------------------

2. 응답 헤더(Response Header): 서버가 클라이언트에게 응답할 때 사용된다. 서버가 클라이언트로 보내는 응답의 메타데이터를 포함하며, 응답의 상태 및 콘텐츠에 대한 정보를 제공한다. 몇 가지 일반적인 응답 헤더는 다음과 같다.

    ```
    Content-Type: 응답으로 전달되는 콘텐츠의 MIME 타입을 지정합니다.
    Content-Length: 응답으로 전달되는 콘텐츠의 길이를 바이트 단위로 지정합니다.
    Server: 서버의 소프트웨어(예: 웹 서버)에 대한 정보를 제공합니다.    
    ```
    ------------------------------------------

3. 표현 헤더(Entity Header): 표현 헤더는 실제 데이터인 표현데이터와 관련된 정보 (데이터유형, 데이터 길이, 압축 정보 등)를 포함한다. 즉, 요청 또는 응답의 본문에 대한 메타데이터를 나타낸다. 표현 헤더는 요청 본문 또는 응답 본문의 메타데이터를 제공한다. 몇 가지 일반적인 표현 헤더는 다음과 같다. 
    - 여기서 표현 데이터는 HTTP 메시지의 본문(body)에 해당한다. 즉 요청이나 응답의 실제 데이터를 나타낸다.
    - 메시지 본문을 페이로드라고도 한다.
    - 표현 데이터와 표현 헤더를 합쳐서 표현이라고 한다.
    ```
    Content-Type : 표현 데이터 형식을 지정합니다.
    Content-Encoding: 표현 데이터 본문에 적용된 인코딩 방식을 지정합니다.
    Content-Length: 표현 데이터의 길이를 지정합니다.
    Content-Language: 표현 데이터 본문의 언어를 지정합니다.
    ```
    -----------------------

<br>

- HTTP 헤더의 주요 정보들
    ```
    Host: HTTP 요청에서 사용되며, 요청을 보내는 호스트의 도메인 이름이나 IP 주소를 지정합니다. 여러 도메인이 호스트되는 서버에서 어떤 도메인에 대한 요청인지를 서버가 구분할 때 사용됩니다. HTTP1.1에서는 Host가 필수이다.

    User-Agent: 클라이언트 애플리케이션(예: 웹 브라우저)에 대한 정보를 포함합니다. 서버는 이 정보를 사용하여 클라이언트의 종류나 버전 등을 파악할 수 있습니다.

    Content-Type: HTTP 엔티티 본문의 유형을 지정합니다. 주로 MIME 타입(예: text/html, application/json)으로 표시되며, 클라이언트와 서버가 엔티티의 형식을 이해하고 적절히 처리할 수 있도록 합니다.

    Content-Length: 엔티티 본문의 길이를 바이트 단위로 지정합니다. 이 값을 통해 클라이언트나 서버는 엔티티의 크기를 파악하고 전송 및 수신을 적절하게 처리할 수 있습니다.

    Content-Encoding: 엔티티 본문에 적용된 인코딩 방식을 지정합니다. 주로 압축 알고리즘(예: gzip, deflate)을 지정하여 데이터를 압축하는 데 사용됩니다.

    Content-Disposition: 엔티티 본문의 전송 방식 및 표시 이름을 지정합니다. 주로 다운로드할 때 파일 이름을 지정하거나 인라인 표시할 때 사용됩니다.

    Accept: 클라이언트가 서버로부터 받기를 원하는 콘텐츠 유형을 지정합니다. 서버는 이 정보를 사용하여 클라이언트에게 적절한 콘텐츠를 제공할 수 있습니다.

    Server: 웹 서버의 소프트웨어에 대한 정보를 포함합니다. 주로 서버가 자신의 소프트웨어 및 버전 정보를 공개하기 위해 사용됩니다.

    Location: 리다이렉션(페이지 이동)을 위해 사용됩니다. 서버가 클라이언트에게 새로운 위치로 이동하라는 지시를 전달할 때 사용됩니다. 일반적으로 HTTP 상태 코드 3xx와 함께 사용됩니다.

    Authorization: 클라이언트가 서버에게 자격 증명(예: 사용자 이름 및 암호)을 제공하는 데 사용됩니다. 보호된 리소스에 접근하기 위해 사용됩니다
    ```

<br>
<br>
<br>

## 쿠키
> 쿠키(Cookie)는 클라이언트와 서버 간의 상태를 유지하고 세션 관리를 위해 사용되는 작은 데이터 조각이다. 주로 웹 브라우저에 저장되고 서버로부터 생성된 정보를 클라이언트에 저장하여 이후 요청 시 해당 정보를 함께 전송한다.

- 쿠키는 사용자 로그인 세션 관리, 광고 정보 트래킹, 사용자 설정 저장 등에 사용된다
- 쿠키 정보는 항상 서버에 전송된다.
    - 네트워크 트래픽 추가 유발한다.
    - 최소한의 정보만 사용한다.
    - 서버에 전송하지 않고 웹 브라우저 내부에 데이터를 저장하려면 웹 스토리지를 사용한다.
- 보안에 민감한 데이터는 저장하면 안된다. (주민번호, 비밀번호 등)
<BR>

- 쿠키 헤더
    - Set-Cookie : 서버에서 클라이언트로 쿠키 전달(응답)한다.
    - Cookie : 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달한다.

<BR>

- 쿠키의 동작 방식 
```
1. 밤낮모드 설정: 사용자가 웹 사이트에서 밤낮모드를 변경한다. 예를 들어, "밤 모드"를 선택했다고 가정한다.

2. 쿠키 생성: 사용자가 "밤 모드"를 선택했을 때, 서버는 클라이언트에게 해당 설정을 저장하는 쿠키를 생성한다. 이 쿠키에는 "밤 모드"를 나타내는 정보가 포함된다.

3. 쿠키 전송: 서버는 생성된 쿠키를 HTTP 응답 헤더에 포함하여 클라이언트에게 전송한다. 클라이언트는 받은 쿠키를 웹 브라우저에 저장한다.

4. 쿠키 저장: 클라이언트의 웹 브라우저는 받은 쿠키를 로컬 스토리지에 저장한다. 이후에 해당 웹 사이트를 다시 방문할 때마다 해당 쿠키가 함께 전송된다.

4. 쿠키 사용: 클라이언트가 다시 웹 사이트를 방문하면, 웹 브라우저는 저장된 쿠키를 함께 서버에게 보낸다. 서버는 이를 통해 사용자의 설정(밤 모드)을 파악하고 그에 맞게 웹 페이지를 렌더링한다.
```

<br>

- 쿠키 - 생명주기
    -  Set-Cookie: expires=Sat, 26-Dec-2020 04:39:21 GMT => 만료일이 되면 쿠키 삭제
    - Set-Cookie: max-age=3600 (3600초) => 0이나 음수를 지정하거나 시간이 지나면 쿠키 삭제
    - 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시 까지만 유지한다.
    - 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지한다.

<br>

- 쿠키 - 도메인
    -  domain=example.org처럼 도메인을 명시하면 해당 도메인과 서브 도메인만 쿠키를 전송할 수 있다.  example.org는 물론이고 dev.example.org도 쿠키 접근이 가능하다.
    - 명시하지 않으면 현재 문서 기준 도메인만 적용된다. 서브 도메인에서 쿠키 접근이 안된다.

<br>

- 쿠키 - 경로
    - path=/home 처럼 경로를 지정하면 이 경로를 포함한 하위 경로 페이지만 쿠키 접근 가능하다. 
    - 일반적으로 path=/ 루트로 지정한다.

<br>

- 쿠키 - 보안
    1.  Secure
        - 쿠키는 원래 http, https를 구분하지 않고 전송한다.
        - Secure를 적용하면 https인 경우에만 전송한다.
    2.  HttpOnly
        - XSS 공격 방지
        - 자바스크립트에서 접근 불가(document.cookie)
        - HTTP 전송에만 사용한다.
    3.  SameSite
        - XSRF 공격 방지
        - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송


<br>
<br>
<br>

## 캐시
> HTTP 캐시는 웹 브라우저나 프록시 서버에 저장되는 데이터의 임시 저장소이다. 이를 통해 이전에 요청한 리소스를 다시 요청할 때 서버에 다시 요청하는 대신 로컬에서 가져올 수 있어 네트워크 부하를 줄일 수 있다. 다시 말해 HTTP 캐시는 웹 페이지의 리소스들을 임시로 저장해 두어 다음에 같은 리소스가 필요할 때 서버에 재요청하지 않고 로컬에서 가져오는 기능을 제공한다.

- 캐시 사용 예시
    ```
    1. 사용자가 인터넷 브라우저를 통해 웹페이지를 방문한다고 가정한다.

    2. 첫 번째 방문 시에는 모든 리소스(이미지, 스타일시트, 자바스크립트 파일 등)가 웹 서버에서 다운로드되어 브라우저의 HTTP 캐시에 저장된다.

    3. 이후 같은 페이지를 방문할 때, 브라우저는 서버에 재요청하지 않고 이전에 받았던 리소스들을 로컬 캐시에서 가져온다.
    ```

<br>

- 캐시 관련 헤더
    ```
    1. Cache-Control: 리소스의 캐싱 동작을 지정한다. 예를 들어, "max-age" 지시자를 사용하여 리소스가 캐시에 저장될 시간을 설정할 수 있다. 
    Cache-Control: max-age=3600

    2. Expires: 리소스가 만료되는 날짜와 시간을 지정한다. 이 시간 이후에는 캐시된 리소스가 만료된다.
    Expires: Thu, 25 Feb 2024 12:00:00 GMT

    3. ETag: 리소스의 엔티티 태그를 제공한다. 클라이언트는 이를 사용하여 리소스가 변경되었는지 확인하고 새로운 버전을 가져올지 여부를 결정한다.
    ETag: "abcdef"

    4. Last-Modified: 리소스가 마지막으로 수정된 날짜와 시간을 제공한다. 클라이언트는 이를 사용하여 리소스가 변경되었는지 확인할 수 있다.
    Last-Modified: Wed, 24 Feb 2024 12:00:00 GMT

    5. Cache-Control: no-cache: 클라이언트가 항상 서버에 리소스를 다시 확인해야 함을 지정한다. 그러나 캐시는 여전히 리소스를 저장하고 서버에 검증을 요청한다.
    Cache-Control: no-cache

    6. Cache-Control: no-store : 데이터에 민감한 정보가 있으므로 저장을 하지 않는다.
    Cache-Control: no-store
    ```

<br>

- 검증 헤더
> 캐시의 유효시간이 초과되면 다시 서버에 요청해 캐시를 갱신한다. 다만 데이터가 변하지도 않았는데 서버에 똑같은 데이터를 다시 요청하면 결국 네트워크 트래픽이 증가한다. 따라서 검증 헤더를 추가해 데이터가 달라졌을 경우에만 캐시를 갱신하고 아닐 경우 원래 있던 캐시를 재사용하게 할 수 있다. 

<br>

1. Last-Modified(검증헤더) / if-modified-since(조건부 헤더)
    ```
     1. 서버는 Last-Modified헤더를 추가해 데이터가 마지막으로 변경된 날짜를 클라이언트에게 전달한다.

     2. 클라이언트는 요청할 때 if-modified-since헤더를 추가해 데이터가 수정되었는지 확인한다. (조건부 요청)

     3. 수정이 안되었다면 304 Not Modified 응답코드로 HTTP Body가 없이 응답된다.

     4. 만약 수정이 되었다면 200 OK로 모든 데이터를 전송한다.
    ```
    - 단점
        - 1초 미만 단위로 캐시 조정이 불가능하다.
        - 날짜 기반의 로직을 사용한다.
        - 데이터를 수정해서 날짜가 다르지만 같은 데이터를 수정해서 데이터 결과가 똑같은 경우
        - 서버에서 별도의 캐시 로직을 관리하고 싶은 경우

<br>

2. ETag(검증헤더) / if-None-Match(조건부 헤더)
    - 단순하게 ETag값을 비교해서 검증한다.
    ```
    1. 서버는 리소스를 클라이언트에게 제공할 때 ETag 헤더를 포함하여 전달한다.

    2. 클라이언트는 요청 할 때 if-None-Match 헤더를 추가해 전달한다.

    3. 서버는 클라이언트가 보낸 ETag 값과 현재 리소스의 ETag 값을 비교한다.

    4. 일치한다면, 서버는 "304 Not Modified" 응답 코드를 반환하고 HTTP 본문(body)를 포함시키지 않는다

    5. 만약 리소스가 변경되었다면, 서버는 "200 OK" 응답 코드를 반환하고, 변경된 리소스의 전체 내용을 함께 전송한다.
    ```

<br>

- 프록시 캐시
> 원래 데이터가 있는 서버가 다른 나라에 위치한다면 데이터를 주고 받는데 시간이 오래 걸릴 것이다. 따라서 우리 나라 어딘가에 프록시 캐시 서버를 만들어 이 프록시 캐시 서버랑 데이터를 주고 받으면 되게 빠르다. 처음 유저가 요청할 땐 느리지만 그 후부터는 프록시 캐시 서버에 저장되어 빠르게 응답받을 수 있다.
    



<br>
<br>
<br>

## HTTP API
> 데이터만 주고 받고 UI가 필요하면 클라이언트가 별도로 처리하는 것을 HTTP API라고 한다. 주로 JSON 방식으로 데이터를 준다.

- 다양한 시스템과 연동
    - HTTP API - 웹 클라이언트 to 서버
    - HTTP API - 앱 클라이언트 to 서버
    - HTTP API - 서버 to 서버

##### 
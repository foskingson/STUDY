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
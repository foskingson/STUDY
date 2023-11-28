# Next.js :green_book:   (13버전 기준)
-  React기반의 웹 프레임워크로 자바스크립트 라이브러리 중 하나이다.<br>
-  서버측 렌더링(SSR)을 지원한다.
    - 서버측에서 미리 페이지를 만들어서 클라이언트에게 보내므로 좀 더 빠른 페이지 로드를 경험할수 있다.<br>
    - 서버측 렌더링은 검색엔진에 완전히 렌더링된 페이지가 제공되므로 검색했을때 더 잘 뜬다. (향상된 SEO)<br>

-  파일 기반 라우팅을 지원한다. 
    - 예를 들어 'app' 디렉토리안에 있는 page.js 는 '/' 라는 주소의 메인페이지를 표시한다면 'app/creat' 경로의 page.js는 '/create'라는 주소의 페이지를 표시한다.<br>
    - /create 경로에 생성된 page.js는 해당 디렉토리 안에 layout.js가 있으면 그 파일과 결합하고 없다면 상위 디렉토리의 layout.js와 결합한다. <br>

-  next.js 13버전부터는 기본적으로 서버컴포넌트로 동작하므로 클라이언트 컴포넌트를 사용하고 싶다면 파일 최상단에 "use client" 를 추가한다. 
    - 두 컴포넌트를 혼합하려면 파일을 분리해서 컴포넌트를 불러와야한다.<br>
    - 웹사이트에서 글을 단순히 보여주는건 서버컴포넌트로 클라이언트와 상호작용은 클라이언트 컴포넌트로 만드는게 좋다. <br>
- 터미널에서 npm run dev로 개발자용 개발서버 열수 있고 npm run build 후에 npm run start를 통해 실제 서비서용 서버를 열수 있다.
  

<br>

## 공부 내용

<br>


### Next.js 정적인 자원 추가하기 (이미지, 동영상 등)
- Next.js를 설치하면 기본적으로 public이라는 디렉토리가 있는데 그 안에 파일을 넣고 경로를 "/파일명" 으로 해서 사용 할 수 있다.
  + 예를 들면 public안에 next.jpg를 넣고 `<img src="/next.png" />` 해당 코드를 통해 사용 할 수 있다. 
  <br>

### global.css
- app디렉토리 안에 있는 global.css를 편집하면 전체적인 디자인을 적용할 수 있다.
<br>
 

### Link
+ `import Link from 'next/link'` 파일 상단에 추가해 사용한다. <br>
- 하나의 페이지에서 모든 작업을 처리하는 앱을 구현하게 해준다 SPA(single page application)<br>
- 사용법은 a태그처럼 사용하면 된다.<br>
- 링크를 클릭했을때 매번 전체페이지를 다운로드 a태그와 다르게 Link태그는 링크를 클릭했을때 필요한 항목만 다운로드받고 방문한 페이지는 다시 다운로드 받지 않아서 더 빠르다.
    - 필요한 항목이라는게 현재 페이지와 링크페이지의 다른 부분들이다. <br>


<br>

###  useEffect

+ `import { useEffect } from "react";` 파일 상단에 추가해 사용한다 <br>
- 컴포넌트가 렌더링 될 때 마다 특정 작업을 할 수 있게 해주는 React HOOK이다.
    - 컴포넌트가 마운트,언마운트,업데이트 특정 작업을 처리할수 있다. <br>


```javascript 
   // 이 효과는 매 렌더링 이후에 실행됩니다
  useEffect(() => {
    console.log('컴포넌트가 마운트 됐을 때(처음 렌더링될때) 실행');
  },[]);


  useEffect(() => {
    console.log('컴포넌트가 렌더링 될 때 마다 실행');
  });   //배열을 생략하면 렌더링 될 때마다 실행

  useEffect(() => {
    console.log('컴포넌트가 마운트되거나 id값이 업데이트 될 때마다 실행');
  },[id]); 

  useEffect(() => {
      console.log('컴포넌트가 마운트되거나 업데이트되기 전에 실행'); // 만약 밑에 배열안에 값을 넣을 경우 업데이트 직전마다 실행
    return () => { // return 뒤에 나오는 함수로 뒷정리 함수라고 한다
      console.log('컴포넌트가 언마운트되거나 업데이트되기 전에 실행'); // 만약 밑에 배열안에 값을 넣을 경우 업데이트 직전마다 실행
    };
  }, []);  // 배열안에 값을 넣으면 그 값이 업데이트 되기 직전마다 뒷정리 함수가 실행되고 비워두면 언마운트 될때만 실행된다.


```
<br>

### useRouter
+ `import { useRouter } from "next/navigation";` 파일 상단에 추가해 사용한다 <br>
- 다양한 라우팅 기능을 제공하는 Next.js의 HOOK이다. <br>

```javascript 
  const router=useRouter(); //현재의 경로 정보를 router에 저장
  router.push('URL'); // 'URL'의 경로로 이동
  router.replace('b') // 현재 페이지의 주소를 b로 변경 (로그인페이지 같은곳에서 사용)
  router.refresh(); // 서버 컴포넌트를 서버 쪽에서 다시 렌더링해서 새로고침

```

<br>

### useParams
 + 터미널에서 `npm install react-router-dom` 를 통해 라우터를 설치하고 해야한다. <br>
 + `import { useParams } from 'next/navigation';` 파일 상단에 추가해 사용한다 <br> 
 - 동적으로 라우팅을 생성하며 URL에 포함되어있는 Key, Value 형식의 객체를 반환해주는 React HOOK이다. <br>

```javascript 
  const params=useParams(); // 현재 경로 정보를 params에 저장
  const num=params.id;       // 현재 경로에 담겨져 있는 id라는 매개변수의 값을  num에 저장
```

<br>

### JSON (JavaScript Object Notation)
-  클라이언트와 서버 사이에서 데이터를 교환하거나 데이터를 저장할때 사용되는 경량의 데이터 교환 형식이다.
  - 기본적으로 프로그래밍언어가 아닌 데이터 교환 형식이므로 함수나 실행코드를 지원해 잠재적으로 유해한 코드의 실행을 방지해 보안을 강화한다<br>
- JSON 문서 형식은 자바스크립트 객체의 형식을 기반으로 만들어졌다.
  - 자바스크립트 객체 형식과 유사하게 JSON의 데이터는 키-값 쌍으로 표시된다. 각 키는 문자열이고 값은 문자열, 숫자, 개체, 배열, 부울 또는 'null'일 수 있다.<br>
- 독립적 언어로 다양한 프로그래밍 언어와 사용할 수 있다.
  - 예를 들면 파이썬과 자바스크립트 사이에서 데이터 교환이 가능하게 만들어 준다.<br>
- 기본적으로 존재하는 db.json파일을 수정해 데이터 관리가 가능하다.
+ JSON 서버 실행하는법 : `npx json-server --port 9999 --watch db.json` 터미널에 입력해서 실행할수 있다. <br>

```javascript

  "topic": [ // 이렇게 데이터가 존재한다고 가정한다.
    {
      "id": 1,
      "title": "html",
      "body": "easy"
    },
    {
      "id": 2,
      "title": "css",
      "body": "easy"
    }]

export default async function Server(){  //fuction 앞에 async를 붙여야함
  // 서버 컴포넌트에서의 json 사용 
  const resp = await fetch(`http://localhost:9999/topics/`); // 해당경로에서 데이터를 가져와달라는 요청 후 그 데이터를 resp에 저장
  const topic = await resp.json();  // resp값에 있는 json데이터를 자바스크립트 객체로 변환해 topic 에 저장
  console.log(topic) // topic 데이터 출력
}

export default function Client(){
  // 클라이언트 컴포넌트에서의 json 사용
   fetch('http://localhost:9999/topics') // 해당경로에서 데이터를 가져와달라는 요청 후 그 데이터를 resp에 저장
            .then(resp=>resp.json()) // resp값에 있는 json데이터를 자바스크립트 객체로 변환해 result 에 저장
            .then(result=>{
                console.log(result) //topic 데이터 출력
            })
}

// 데이터의 추가,수정,제거할때 사용하는 옵션
const options={ //fetch('http://localhost:9999/topics',options) <= 이런식으로 붙여서 사용
                method: 'POST' ,  // 'POST' : 데이터베이스로 데이터를 올림, 'PATCH' : 데이터를 수정함, 'DELETE' : 데이터를 삭제함
                headers:{
                    'Content-Type': 'application/json'  // json 데이터 교환방식을 사용
                },
                body:JSON.stringify({title,body}) // 새로 올리거나 수정할 데이터를 넣는다. 데이터를 삭제할 경우에는 안써도 됨
            }

```
<br>


### 환경변수
#환경변수
- .env.local 파일을 만들어 환경변수를 로드할수 있다.
- 웹의 환경 변수는 애플리케이션의 소스 코드에 하드 코딩되지 않고 대신 애플리케이션이 실행되는 환경에서 제공되는 외부 구성 설정을 나타낸다. <br>
  - 외부 구성 설정 요소로는 API키, URL, 비밀번호 등이 있다.
- 환경 변수가 필요한 이유는 우리가 웹 애플리케이션을 만들 때 개발 모드(development mode)와 배포 모드(production mode)에 따라 다르게 코딩해야 하기 때문이다.
  - NEXT.JS에서 환경변수 쓰는 이유가 개발모드랑 배포모드의 API_URL이 다른경우들이 있는데 이럴 경우 하드코딩된 모든 값을 바꾸기 어려우니까 미리 환경변수로 설정해놓는다 <br>
- .env 파일은 민감한 정보를 가지고 있기 때문에 우리가 github에 push할때는 빼는게 좋다.
  - 가장 좋은 방법은 .env.example 이름으로 필요한 환경 변수가 템플릿 형태로 있는 파일로 만드는 것이 중요하다. <br>
  - gitnore에 github에 올리면 안되는 파일을 적어 놓으면 나중에 커밋할때 보이지 않는다. <br>


<br> <br> <br>

## 기타
-  웹 개발에서 렌더링이란 HTML, CSS 및 JavaScript 코드를 사용자가 웹 브라우저에서 볼 수 있는 시각적 표현으로 변환하는 프로세스 <br>
-  비동기처리란 데이터를 요청해서 받아오고 있는데 기다리지도 않고 다음코드를 처리한것이다. 콜백함수를 통해 해결가능하다 <br>
- 구성요소가 "마운트되었다"는 것은 해당 구성요소가 인스턴스화되어 DOM에 추가되었음을 의미합니다. 장착 단계는 구성 요소의 초기 렌더링 중에 발생한다 <br>
- 웹에서 캐싱은 성능과 효율성을 크게 향상시킬 수 있지만 서버에서 변경된 내용을 가져올 때 즉시 반영되지 않는 상황이 발생할수 있다.
  - {cache:'no-store'}를 사용해 캐시를 우회하고 서버에서 직접 리소스를 가져와 항상 최신데이터를 웹에 반영할수 있다.
-  문자열과 변수가 같이 들어갈때는 (') 작은 따옴표 사용을하면 열고 닫으며 +를 해야하기 때문에 불편하다 따라서 (') 백틱으로 감싼후 변수앞에 $붙이고 {}로 감싼다.
- jsx규칙상 무조건 하나의 태그로 묶어서 return 해줘야한다 (쓸 태그가 없다면 빈태그를 사용한다.)
- 하드코딩 : 데이터를 코드 내부에 직접 입력하는 것

              

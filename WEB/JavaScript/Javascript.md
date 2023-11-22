# JavaScript
<br>

### 데이터타입
| 데이터 타입     | 설명                                       | 예시                     |
|--------------|------------------------------------------|------------------------|
| `String`     | 텍스트 데이터                              | `"Hello"`, `'JavaScript'`  |
| `Number`     | 숫자                                       | `42`, `3.14`               |
| `Boolean`    | 참(`true`) 또는 거짓(`false`) 값을 가지는 데이터         | `true`, `false`            |
| `Null`       | 값이 존재하지 않음을 나타내는 데이터                  | `null`                   |
| `Undefined`  | 값이 할당되지 않은 상태를 나타내는 데이터               | `undefined`              |
| `Object`     | 여러 속성과 값으로 구성된 데이터 구조                 | `{ name: 'John', age: 30 }` |
| `Array`      | 여러 항목을 담는 순서가 있는 리스트 형태의 데이터         | `[1, 2, 3]`              |
| `Function`   | 기능을 수행하거나 처리하는 블록 코드를 담은 데이터         | `function greet() { return "Hello!" }` |
> 변수 앞에는 var을 붙여주는게 좋다
<br>

### onclick(클릭할때), onkeydown(키보드를 누를때) , onchange(쓰는 영역에서 벗어났을때) ···
> on으로 시작하는 자바스크립트의 이벤트태그
```javascript
<input type="text" onkeydown="alert('GG')">  텍스트박스안에서 키보드를 누를때마다 GG 이벤트출력
```
<br>



### .length
> 문자열의 문자개수를 확인가능
```javascript
alert(abc.length) => 3 출력
```
<br>



### 함수
>함수의 여러가지 생성방법

```javascript
coworkers.showAll= function(){
    coworker라는 객체 안에 showAll이라는 메소드 생성
}

var showAll=function(){
    showAll 이라는 함수 생성
}

function showAll(){
    showAll 이라는 함수 생성
}
```

### 최대한 중복을 없애서 깔끔한 코드 만들기 (리팩토링)


### 객체
> 요소(property)로 구성된 집합으로 요소는 키(key)와 값(value)으로 이루어져있다.
> 함수로 객체를 생성하기도 하며, 함수 자체가 객체이기도 하다.
```javascript
const home{
    father: aaa             // home.father로 home이라는 객체안의 father의 키값을 참조할수 있다.
    mother: bbb
    me : function(){        //객체안에 있는 함수를 메소드라고 한다
        alert('ccc');   
    }
}

const topic=[               // 이런식으로 객체배열도 생성가능하다
    {id:1 , title:'코딩 기록', body:'코딩은 어려워..'},
    {id:2 , title:'헬스 기록', body:'헬스는 어려워..'},
    {id:3 , title:'운전 기록', body:'운전은 어려워..'},
];
```

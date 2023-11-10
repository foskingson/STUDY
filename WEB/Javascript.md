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

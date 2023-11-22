# React :closed_book:
> 웹 프레임워크로 자바스크립트 라이브러리 중 하나이다.<br>
> 사용자 인터페이스(UI)를 구축하기 위한 라이브러리이다.<br>
> 선언적,효율성,유연성을 가지고 있다. <br>
1. 선언적: React는 애플리케이션에 대해 더 쉽게 추론할 수 있도록 하는 선언적 패러다임을 사용합니다.
2. 효율성: React는 DOM의 모의 표현을 사용하여 DOM과의 상호 작용을 최소화합니다.
3. 유연성: React는 이미 알고 있는 라이브러리 및 프레임워크와 함께 작동합니다.

<br>


### 컴포넌트 
> 앱을 이루는 가장 작은 조각<br>
> 무조건 이름은 대문자로 시작해야한다.<br>
> 사용자정의태그로 사용자가 함수처럼 정리해놓고 태그처럼 사용가능하다. (함수형 컴포넌트)<br><br>
>>컴포넌트의 구성요소<br>
>>1. property(props) : 상위 컴포넌트가 하위 컴포넌트에 값을 전달할때 사용한다. (프로퍼티는 컴포넌트 내부에서 값을 바꿀수 없다)
>>2. state : 컴포넌트의 내부에서 변경 가능한 데이터를 관리할때 사용한다.
>>3. context : 부모 컴포넌트에서 생성되어 모든 자식 컴포넌트에게 전달하는 데이터이다. <br> 
상위 컴포넌트가 하위 컴포넌트에 props를 사용하지 않고 필요한 데이터를 넘길수 있다. (props drilling 방지 가능) <br> 
>> * props drilling: 하위계층에 props를 전달하기 위해 props가 필요없는 계층에도 props를 전달하는것

```javascript
function Header(props){	    //상위 컴포넌트에서 받은 타이틀의 값을 props에 저장
    const [title,setTitle]=useState(props.title);   
    return <article>
		<input type="button" value="button" onClick={event=>{   // 버튼을 클릭했을때 타이틀을 출력하고 조건문에 따라 타이틀을 바꿈
			event.preventDefault();
			alert(title);
			if(title==="react"){
				setTitle('WEB');
			} else if(title==="WEB"){
				setTitle('react');
			}
		}}/>
    </article>
}
function App() {
	

	return (
		<Header title="react"></Header>	// Header컴포넌트를 불러오고 하위 컴포넌트에게 타이틀의 값 전달
	);
}
// 자바스크립트와 다르게 react에서는 onChange는 값을 입력할때마다 이벤트 발생 
```
<br>

### useState
> 리액트의 hook중 하나로 현재의 state값과 state값을 업데이트하는 함수를 반환하는 함수이다. <br>
> 일반적인 정수나 문자열은 평범하게 useState를 사용하면 되지만 배열과 객체는 다른 작업이 필요하다. 아래코드 참조 <br>
>> react는 상태관리를 단순화하고 성능을 향상시키기 위해 불변성을 권장한다. 정수나 문자열의 경우 usestate를 사용하면 새값과 이전값을 비교하여
다시 렌더링이 필요한지 결정한다. 값이 다를경우 react는 구성요소를 업데이트 한다. 그러나 배열과 객체는 불변성을 처리하는 방식이 다르다.
react는 효율적으로 처리하기 위해 새로운 배열이나 객체를 만들어 처리하도록 권장한다.

```javascript
function Header(props){	    
    const [title,setTitle]=useState(props.title);       //  문자열 state의 생성
    const [topic,setTopic]=useState([html,css,javascript]); // 함수 state 생성
    setTitle('WEB');    //setTitle 함수를 이용해 바꾸려는 값이 현재와 같은지 비교 후 다르다면 해당 컴포넌트는 값을 변경하고 화면에 다시 렌더링한다
    const newTopic=[...topic];  // 새로운 배열에 기존 topic배열 복사
    newTopic.push('WEB');       // 복사된 배열에 'WEB' 추가
    setTopic(newTopic);         // Topic과 newTopic은 다르기 때문에 해당 컴포넌트는 값을 변경하고 다시 렌더링한다.
    return <article>
		
        
    </article>
}
function App() {
	

	return (
		<Header title="react"></Header>	
	);
}

```

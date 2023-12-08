# Redux  :tophat:
- Redux는 사용자 인터페이스 구축을 위해 React와 같은 라이브러리와 함께 일반적으로 사용되는 JavaScript 앱의 예측 가능한 상태 컨테이너(저장소)이다. <br>
- 순수함수를 이용해 예측 가능한 방식으로 애플리케이션 상태를 관리하기 위한 중앙 집중식 저장소를 제공한다. <br>
    - 순수함수 : 부작용이 없는 함수, 즉 함수의 실행이 외부에 영향을 끼치지 않는 함수
- redux는 불변성을 권장한다. 따라서 상태를 직접 수정할수 없다. <br>
<br>


### redux의 구조
-  render가 getstaet를 통해 state값을 가져오고 그걸 통해 UI를 만든다. <br>
    - redux는 state값을 직접 참조할수 없다. 
-  action을 전달 받으면 dispatch가 reducer를 호출해서 state값을 변경한다. 그 후 subscribe를 이용해서 render함수를 통해 화면을 새로고침한다. <br>
    - dispatch가 reducer를 호출할때는 현재 state값과 변경되는 action값을 전달해준다. state값을 입력값으로 받고 action값을 참조해 새로운 state를 만들어 리턴해준다.<br>
        - action값에 type속성은 필수로 들어가야 한다.
    - 새로운 state가 리턴되면 dispatch에서 subscribe를 호출해 render가 getstaet를 통해 state값을 가져오고 그걸 통해 UI를 만드는 과정을 수행한다 <br>
- redux.html파일을 통해 간단한 redux의 구조를 살펴볼수 있다.

<br>

### no-redux.html과 redux.html 비교
- no-redux.html을 살펴보면 부품들이 서로가 연관되어 있어 한 부품이 바뀌면 다른 부품까지 다 수정줘야한다. 
하지만 redux.html 처럼 redux를 사용하면 store라는 저장소를 통해 중앙집중적으로 상태를 변경할수 있기 때문에 서로의 부품들에 대해 연관되어 있지 않고
각자의 기능만 충실히 할수 있다.

<br>

### redux dev tools
- redux dev tools은 redux에서 일어나는 action들의 버전관리를 위한 확장프로그램이다.
- 오류가 났을때의 상태를 다운받아 어떤문제인지 쉽게 파악할수 있다.

<br>










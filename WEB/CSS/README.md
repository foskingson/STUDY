# CSS (Cascading Style Sheets)
- HTML과 같은 언어로 작성된 스크립트를 꾸며주는 역할을 가지고 있다.
- 웹 페이지의 레이아웃, 색상, 글꼴 및 기타 시각적 측면을 제어하여 웹 콘텐츠 모양과 동작에 대한 유연성과 제어기능을 제공한다.
- 웹 페이지 전체적으로 스타일을 적용하거나 특정요소, 클래스 또는 ID를 대상으로 지정할 수 있다.

<br>
<br>

### 속성
- CSS에서 속성은 선택한 요소에 적용하려는 스타일의 지침이다. 각 속성에는 이름과 값이 있다
<br>

- 간단한 일반적인 속성
    - color: 텍스트 색상을 지정합니다.
    - font-size: 글꼴 크기를 지정합니다.
    - font-family: 글꼴 계열을 지정합니다.
    - background-color: 배경색을 지정합니다.
    - margin: 요소 주위의 여백 공간을 지정합니다.
    - padding: 내부 패딩 공간을 지정합니다.
- 웹에 있는 개발자 도구를 통해 margin이나 padding값을 확인해보며 조절할 수 있다.


## 선택자
- 스타일을 적용할 HTML 요소를 선택하는 데 사용되는 패턴이다.
    - 요소 선택자 : 태그 이름을 기준으로 HTML요소를 선택한다. 
    ``` CSS
        p {
        color: blue;    /* 모든 <P>태그에 적용 */
    }
    ```
    <br>

    - 클래스 선택자 : 특정 클래스 속성을 가진 요소를 선택한다. 클래스명 앞에 .를 붙여 선택한다.
    ```CSS
    .highlight {
        background-color: yellow;
    }
    ```
    <br>

    - ID 선택자 : 특정 ID를 속성을 가진 단일 요소를 선택한다. ID명 앞에 #을 붙여 선택한다.
        - ID는 하나의 요소에만 사용할 수 있다. 예를 들어 `<div id="myElement">...</div>` 가 있다면 다른 곳에서 myElement이라는 ID는 사용하지 못한다.
    ```CSS
    #header {
        font-size: 24px;
    }
    ```
    <br>

    - 속성 선택자 : 속성의 존재 또는 값을 기반으로 요소를 선택한다.
    ```CSS
    input[type="text"] {
        border: 1px solid black;    /* <input type="text">가 있는 곳에 모두 적용 */
    }
    ```


<br>
<br>

## 박스 모델
- 모든 HTML 요소는 박스(box) 모양으로 구성되며, 이것을 박스 모델(box model)이라고 부른다.
- CSS의 박스 모델은 웹페이지의 요소가 렌더링되는 방식과 내용, 안쪽 여백, 테두리 및 마진 영역이 요소 주위에 구성되는 방식을 설명하는 기본 개념이다.

<br>

- CSS 박스 모델의 주요 구성 요소
    - 내용(content) : 텍스트, 이미지, 기타 미디어 등 요소의 실제 콘텐츠이다. 
    - 패딩(padding) : 패딩은 내용과 요소 테두리 사이의 공간이다. 패딩은 눈에 보이지 않는다. padding-top, padding-right, padding-bottom 및 padding-left와 같은 CSS 속성을 사용하여 설정할 수 있다.
    - 테두리(border) : 요소의 내용과 패딩을 둘러싸는 선이다. border-width, border-style, border-color와 같은 속성을 사용하여 스타일을 지정할 수 있다.
    - 마진(margin) : 테두리와 이웃하는 요소 사이의 간격이다. 마진은 눈에 보이지 않는다.  'margin-top', 'margin-right', 'margin-bottom', 'margin-left'와 같은 CSS 속성을 사용하여 설정한다.
```markdown
 ___________________________________
|              Margin               |
|    ___________________________    |
|   |          Border           |   |
|   |    ___________________    |   |
|   |   |      Padding      |   |   |
|   |   |    ___________    |   |   |
|   |   |   |  Content  |   |   |   |
|   |   |   |           |   |   |   |
|   |   |   |___________|   |   |   |
|   |   |___________________|   |   |
|   |___________________________|   |
|___________________________________|

```

<br>
<br>

## 그리드
- CSS 그리드는 웹 개발자가 복잡하고 반응성이 뛰어난 2차원 그리드 기반 레이아웃을 쉽게 만들 수 있는 강력한 레이아웃 시스템이다.
    - 행과 열을 모두 지정하여 복잡한 디자인을 만들 수 있다.
<br>

- 그리드의 주요 기능
    - 그리드 컨테이너 : grid가 영향을 받는 전체 공간이다. `display: grid`를 통해 그리드 컨테이너로 지정할 수 있다.
    - 그리드 아이템 : 그리드 컨테이너의 하위 항목으로 설정된 그리드 속성에 따라 아이템들이 행과 열에 배치된다.
    - 그리드 셀 : 그리드의 한칸을 가르키며 하나 이상의 그리드 아이템을 담는 틀과 같은 역할을 한다.
    - 그리드 라인 : 그리드의 행과 열을 구분하는 가로선과 세로선을 그리드선이라고 한다.
    - 그리드 행 및 열: grid-template-rows 및 grid-template-columns와 같은 속성을 사용하여 그리드의 행과 열 수와 크기를 정의할 수 있다.
    - 그리드 간격 : 'grid-row-gap' 및 'grid-column-gap'을 사용하여 그리드 행과 열 사이에 공간을 만들 수 있다.
    - 그리드 영역 : 그리드 셀의 집합이다. grid-template-areas를 통해 각 영역에 이름을 붙여 배치 할수 있다.
<br>


``` html
<style>
    /* CSS 그리드 컨테이너 설정(grid-template-areas 사용하지 않을 경우) */
    .container {
        display: grid;
        grid-template-columns: 100px 100px 100px; /* 3개의 컬럼, 각각의 너비는 100px */
        grid-template-rows: 100px 100px; /* 2개의 로우, 각각의 높이는 100px */
        grid-gap: 10px; /* 로우와 컬럼 사이의 간격은 10px */
    }

    /* CSS 그리드 아이템 설정 */
    .item {
        background-color: #3498db;
        color: white;
        text-align: center;
        padding: 20px;
    }
</style>

<div class="container"> <!-- 부모요소 Grid Container(그리드 컨테이너) -->
  <div class="item">1</div> <!-- 자식요소 Grid Item(그리드 아이템) -->
  <div class="item">2</div>
  <div class="item">3</div>
  <div class="item">4</div>
  <div class="item">5</div>
  <div class="item">6</div>
</div>
```
<br>

``` html
<style>
    /* 그리드 영역을 지정하여 각각의 그리드 영역을 디자인 할수 있다.*/
    /* CSS 그리드 컨테이너 설정(grid-template-areas 사용할 경우) */ 
    .container {
    display: grid;
    grid-template-areas: 
        'header header header'
        'sidebar content content'
        'footer footer footer';
    grid-template-rows: 100px 300px 100px; /* 로우의 높이 설정 */
    grid-template-columns: 200px 1fr 200px; /* 컬럼의 너비 설정 */
    }

    /* 각 영역의 스타일링 */
    .header {
    grid-area: header; /* 그리드 영역 설정 */
    background-color: #3498db;
    color: white;
    padding: 20px;
    }

    .sidebar {
    grid-area: sidebar;
    background-color: #2ecc71;
    color: white;
    padding: 20px;
    }

    .content {
    grid-area: content;
    background-color: #e74c3c;
    color: white;
    padding: 20px;
    }

    .footer {
    grid-area: footer;
    background-color: #f39c12;
    color: white;
    padding: 20px;
    }
</style>
<body>
    <div class="container">
        <div class="header">Header</div>
        <div class="sidebar">Sidebar</div>
        <div class="content">Content</div>
        <div class="footer">Footer</div>
    </div>
</body>
```

<br>
<br>

## 반응형 디자인
- CSS의 반응형 디자인은 다양한 화면 크기, 방향 및 장치에 적응하고 반응하는 웹 디자인이다.
- 반응형 디자인의 목표는 웹사이트가 컴퓨터,노트북,스마트폰을 포함한 다양한 장치에서 잘 보이고 작동하도록 하는 것이다.
- 일반적으로 모바일 우선 접근 방식을 사용한다.
    - 모바일 우선 접근 방식: 먼저 모바일 장치를 대상으로 디자인한 다음 화면 크기가 커짐에 따라 레이아웃을 점진적으로 개선하는 것이 반응형 디자인의 일반적인 전략이다.
<br>

- 반응형 디자인 주요 기술
    1.  미디어 쿼리 : 반응형 디자인의 핵심 요소이다. 이를 사용하면 장치의 특성에 따라 특정 CSS스타일을 적용할 수 있다. 장치의 너비,높이,방향,해상도 등을 기준으로 CSS을 조절할 수 있다.
    ```css
    /* 768px 이하의 화면에 적용되는 미디어 쿼리 예시 */
    @media screen and (max-width: 768px) {
    /* 작은 화면에 대한 CSS 스타일 */
    }
    ```
    <br>

    2.  유연한 레이아웃: 고정 너비 레이아웃 대신 유연한 레이아웃을 사용한다. 이는 백분율이나 뷰포트 단위 (vw, vh, vmin, vmax)와 같은 상대적인 단위를 사용하여 요소의 크기를 조정하여 뷰포트의 크기에 맞게 조절 된다
    ```css
    .container {
        width: 100%; /* 컨테이너를 유동적으로 설정 */
    }

    .column {
        width: 50%; /* 두 개의 컬럼이 각각 반 너비를 차지하도록 설정 */
    }
    ```
    <br>

    3. 유동 이미지와 미디어: 웹 사이트에 삽입된 이미지와 미디어는 다양한 화면 크기에 맞게 조정되어야 한다. 이를 위해 이미지와 미디어의 max-width 속성을 100%로 설정하여 부모 컨테이너 내에서 비율에 맞게 조절된다
    ```css
    img {
        max-width: 100%; /* 이미지를 유동적으로 설정 */
        height: auto; /* 가로세로 비율 유지 */
    }
    ```
    <br>
    
    4. 뷰포트 : 뷰포트는 웹사이트가 사용자에게 보여지는 영역이다. 예를 들면 데스크탑의 뷰포트는 휴대폰의 뷰포트보다 크다.
        - 뷰포트의 메타태그를 통해 뷰포트를 확대/축소를 제어할 수 있다. 웹사이트가 다양한 장치에서 올바르게 표시되고 정확한 비율로 크기를 조정할 수 있도록 브라우저에 지시한다.
        ```html
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- width=device-width는 브라우저에게 뷰포트 너비를 기기 화면 너비로 설정하도록 지시한다. -->
        <!-- initial-scale=1.0은 페이지가 처음 로드될 때 초기 확대/축소 수준을 1.0으로 설정하여 콘텐츠가 확대/축소 없이 원래 크기로 표시된다. -->
        
        ```


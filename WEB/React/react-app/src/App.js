import logo from "./logo.svg";
import "./App.css";
import { useState } from "react";
//터미널에서 npm start 명령어를 통해 시작 가능 
function Header(props) {
  //  react의 사용자 설정 태그 컴포넌트라고 부른다
    return  <header>
      <h1>
        <a href="/" onClick={props.onChangeMode}>{props.title}</a>

      </h1>
      {/*자바스크립트 함수와 다르게 컴포넌트에서는 html 태그를 사용가능하다*/}
    </header>
  
}

function Nav(props) {

  const lis=[]
    
  for(let i=0; i<props.topic.length;i++){
    let t=props.topic[i];
    lis.push(<li key={t.id}>
      <a id={t.id} href={'/read/'+t.id} onClick={event=>{ // onclick 사용할때 {함수명()} 이런식으로 사용하면 즉시실행이 되므로 전용함수를 따로 만들어줄것
      event.preventDefault();                             // 정수값의 태그의 속성 id에 넣으면 문자열로 바뀜
      props.numCount(Number(event.target.id));            // 자바스크립트 문자를 숫자로 바꿔주는 문법 Number
    }}> {t.title} </a></li>)
  }

  return <nav>
    <ol>
    {lis}
      
    </ol>
  </nav>
}

function Article(props) {
  return <article>
    <div>{props.title}</div>
    <div>{props.body}</div>
  </article>
}


function Create(props){
  return <article>
    <h2>Create</h2>
    <form onSubmit={event=>{
      event.preventDefault();
      const title=event.target.title.value;
      const body=event.target.body.value;
      props.onCreate(title,body);
    }}>
      <p><input type="text" name="title" placeholder="title" /></p>
      <p><textarea name="body" placeholder="body "></textarea></p>
      <p><input type="submit" value="Create"/></p>
    </form>
    </article>
}

function Update(props){
  const [title,setTitle]=useState(props.title);
  const [body,setBody]=useState(props.body);
  
  return <article>
    <h2>수정</h2>
    <form onSubmit={event=>{
      event.preventDefault();
      const title=event.target.title.value;
      const body=event.target.body.value; 
      props.onUpdate(title,body);                                               
    }}> 
      <p> <input type="text" name="title" placeholder="수정후 타이틀" value={title} onChange={event=>{
        console.log(event.target.value); //이벤트를 방생시킨 로그가 무엇이야 / onchange는 값을 입력할때마다 이벤트 발생 
        setTitle(event.target.value);
      }}/></p> 
      <p><textarea name="body" placeholder="수정 후 글 " value={body} onChange={event=>{
        setBody(event.target.value);
     
      }}></textarea></p>
      <p><input type="submit" value="수정하기"/></p>
    </form>
  </article>
}

function App() {
  const [mode, setMode] = useState('welcome'); // 꼭 알아야할 상태변수 state 문법 / setMode가 사용되면 app함수가 다시 시작되면서 usestate가 mode값을 code로 바꿔줌
  const [id,setid] = useState(null);          // state 문법을 통해 컴포넌트 함수를 다시 시작시킬수 있다
  const [nextId,setnextId]=useState(4);
  const [topic,settopic]= useState([
    {id:1 , title:'코딩 기록', body:'코딩은 어려워..'},
    {id:2 , title:'헬스 기록', body:'헬스는 어려워..'},
    {id:3 , title:'운전 기록', body:'운전은 어려워..'},
  ]);
  
  let content = null;
  let contextControl =null;
  
  if(mode==='welcome'){
    content=<Article title='Welcome' body="환영한다"></Article>
  } else if(mode==='study'){
    let title,body=null;
    for(let i=0;i<topic.length;i++){
      if(topic[i].id===id){
        title=topic[i].title;
        body=topic[i].body;
      }
    }
    content=<Article title={title} body={body}></Article>
    contextControl=<>
      <li><a href={"/update/"+id} onClick={event=>{
        event.preventDefault();
        setMode('UPDATE');
      }}>Update</a></li>
      <li> <input type="button" value="삭제" onClick={event=>{
        const deltopics=[];
            for(let i=0;i<topic.length;i++){
              if(topic[i].id !== id){
                deltopics.push(topic[i]);
                
              }
            }
        settopic(deltopics);
        setMode('welcome');
    }} /></li>
    </>
        
  } else if(mode==='CREATE'){
    content=<Create onCreate={(_title,_body)=>{
      const newTopic={id:nextId, title:_title,body:_body};
      const newTopics=[...topic] ;
      newTopics.push(newTopic);
      settopic(newTopics);
      setMode('study');
      setid(nextId);
      setnextId(nextId+1);
    }}></Create>
  } else if(mode==='UPDATE'){
    let title,body=null;
    for(let i=0;i<topic.length;i++){
      if(topic[i].id===id){
        title=topic[i].title;
        body=topic[i].body;
      }
    }
    content=<Update title={title} body={body} onUpdate={(update_title,update_body)=>{
      const upTopic={id:id, title:update_title, body:update_body};
      const upTopics=[...topic];;
      for(let i=0;i<topic.length;i++){
        if(topic[i].id===id){
          upTopics[i]=upTopic;
          break;
        }
      }
      settopic(upTopics);
      setMode('study');
    }}></Update>
  }

  return (
    <div className="App">
      <Header title="React" onChangeMode={function(){
        alert('hi');
        setMode('welcome'); 
      }}></Header>
      <Nav topic={topic} numCount={(_id)=>{
        alert(_id);
        setMode('study');
        setid(_id)
      }}>
      </Nav>
      {content}
      <ul>
        <li><a href="/create" onClick={event=>{
          event.preventDefault();
          setMode('CREATE');
        }}>Create</a></li>
        {contextControl}
        
      </ul>

    </div>
  );
}

export default App;

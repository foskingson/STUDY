//다이나믹 라우팅 폴더안에서 id값을 통해 동적으로 라우팅할수 있다.



export default async function Read(props){
  const id = props.params.id;
  const resp = await fetch(`http://localhost:9999/topics/${id}`, {cache:'no-store'}); //no-store : 응답을 아예 저장안함 (보안성 좋음) 
  const topic = await resp.json(); // no-cache :응답을 저장하지만 후속요청에 사용하기 전에 다시 유효성을 검사함 (캐싱을 허용하고 싶지만 하기전에 캐시된 컨텐츠의 유효성을 다시확인하고 싶을때)                                     



  return <>
    
    <h2>{topic.title}</h2>
    {topic.body}
  </>
}
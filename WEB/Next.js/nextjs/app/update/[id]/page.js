"use client"
import { useRouter } from "next/navigation";
import { useState,useEffect } from "react";



//다이나믹 라우팅 폴더안에서 id값을 통해 동적으로 라우팅할수 있다.
export default function Update(props){
    const router= useRouter();
    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");
    const id = props.params.id;
    //const resp = await fetch(`http://localhost:9999/topics/${id}`);
    ///const topic = await resp.json();
    useEffect(() => {
        fetch(`http://localhost:9999/topics/${id}`)
          .then((resp) => resp.json())
          .then((res) => {
            setTitle(res.title);
            setBody(res.body);
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      }, [id]);
    //const [title,setTitle] = useState(topic.title);
    //const [body,setBody] = useState(topic.body);

  
    return <>
    <form onSubmit={event=>{
        event.preventDefault();
        const title=event.target.title.value;
        const body=event.target.body.value; 
        fetch(`http://localhost:9999/topics/${id}`, {
            method:'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body : JSON.stringify({title,body})
        })
        .then(resp=>resp.json())
        .then(res=>{
                    console.log(res);
                    const lastid=res.id;
                    router.push(`/read/${lastid}`);
                    router.refresh();
        })

    }}>
      <p><input type="text" name="title"  placeholder="제목" value={title} onChange={(event)=>{
        setTitle(event.target.value);
      }}/></p>
      <p><textarea name="body" placeholder="내용" value={body} onChange={(event)=>{
        setBody(event.target.value);
      }} /></p>
      
      <p><input type="submit" value="제출"/></p>
    </form>
  
      
    </>
  }
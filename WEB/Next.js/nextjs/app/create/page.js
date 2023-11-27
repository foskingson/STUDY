// create 폴더가 layout.js 파일이 있다면 그것과 결합하고 없다면 부모폴더의 layout.js파일과 결합한다.
"use client"

import { useRouter } from "next/navigation"; // next/router => next/navigation 버전땜에 오류나면 이렇게 바꾸기

export default function Create(){
    const router=useRouter();
    return (
        <form onSubmit={(event)=>{
            event.preventDefault();
            const title=event.target.title.value;
            const body=event.target.body.value;
            const options={
                method: 'POST' ,
                headers:{
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify({title,body})
            }

            fetch('http://localhost:9999/topics',options)
            .then(resp=>resp.json())
            .then(result=>{
                console.log(result);
                const lastid=result.id;
                router.push(`/read/${lastid}`);
                router.refresh();
                
            })
        }}>
            <p>
                <input type="text" name="title" placeholder="title" />
            </p>
            <p>
                <textarea  name="body" placeholder="body" />
            </p>
            <p>
                <input type="submit" value="create"/>
            </p>
        </form>
    )
}
"use client" // next13에스는 클라이언트 컴포넌트를 쓰기 위해서는 이걸 무조건 써야하고 이순간부터 이파일은 클라이언트 컴포넌트로 취급함
import Link from 'next/link';
import { useParams } from 'next/navigation';
import { useRouter } from 'next/navigation';
  
// npx json-server --port 9999 --watch db.json 데이터들어있는 서버 여는 터미널 명령어
export function Crud() {
  const params=useParams();
  const router=useRouter();
  
  const id=params.id;
  return (
    <ul>
      <li><Link href='/create'>생성</Link></li>
      {id ? <>  
        <li><Link href={`/update/${id}`}>수정</Link></li>
        <li><input type='button' value="삭제" onClick={()=>{
          fetch(`http://localhost:9999/topics/${id}`, {
            method:'DELETE'
          })
          .then(resp=>resp.json())
          .then(res=>{
            
          })
          router.push(`/`); // 페이지 리로드 없이 메인 페이지로 이동
          router.refresh(); // 서버 컴포넌트를 서버쪽에서 다시 렌더링해서 새로고침

          
        }}></input></li>
      </> : null}
      
    </ul>
  );

}

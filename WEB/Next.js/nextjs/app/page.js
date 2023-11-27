import Image from 'next/image'
// npm run dev 개발자용 개발서버 열기
// npm run build => npm run start 실제 서비서용 서버 열기
// jsx규칙상 무조건 하나의 태그로 묶어서 return 해줘야한다 (쓸 태그가 없다면 빈태그를 사용한다.)
// 라우팅을 할때는 app밑에 폴더를 만들고 그안에다가 파일을 만드는데 파일 이름은 무조건 page.js로 한다
// 웹사이트에서 글을 단순히 보여주는건 서버컴포넌트로 클라이언트와 상호작용은 클라이언트 컴포넌트로 만드는게 좋다
export default function Home() {
  return (
    <>
    <h2>Welcome</h2>
      hello
      
      <br></br><img src="/pp.jpg"></img>
      </>
  )
}

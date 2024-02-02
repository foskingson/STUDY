package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.memberRepository;

public class MemberServiceTest {
    MemoryMemberRepository repository;
    MemberService memberService;

    @BeforeEach 
    // 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService=new MemberService(repository);
    }

    
    @AfterEach // 하나의 테스트 코드가 끝날때마다 실행
    public void afterEach(){
        repository.clearStore(); //clearStore메서드 안에 있는 store.clear();를 통해 안에 데이터를 싹 비워줌
    }

    

    @Test
    void testFindMembers() {
        List<Member> result = memberService.findMembers();
        assertThat(result).isEqualTo(repository.findAll());

    }

    @Test
    void testFindOne() {

    }

    @Test
    void 회원가입() {

        //Given
        Member member1 = new Member();
        member1.setName("spring1");
        //When
        Long saveId = memberService.join(member1);
        //Then
        Member findMember = repository.findById(saveId).get();
        assertEquals(member1.getName(), findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        Member member1=new Member();
        member1.setName("spring1");

        Member member2=new Member();
        member2.setName("spring1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 중복이 아닐때 에러
        

        /*
        try{
            memberService.join(member2);
        } catch(IllegalStateException e){
            fail("예외 발생");
        }
        */
        
    }
}

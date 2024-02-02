package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.memberRepository;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다. 다만 DB연동 없는 순수자바코드에 비해 속도가 느린편이다.
@Transactional  //트랜잭션을 추가해주면 한 테스트를 마칠때마다 전으로 롤백해준다. 따라서 DB에 데이터가 남지 않아 다음테스트에 영향을 주지 않음
class MemberServiceIntTest {
    @Autowired memberRepository repository;
    @Autowired MemberService memberService;
 

    @Test
    void 회원가입() {

        //Given
        Member member1 = new Member();
        member1.setName("spring6");
        //When
        Long saveId = memberService.join(member1);
        //Then
        Member findMember = repository.findById(saveId).get();
        assertEquals(member1.getName(), findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        Member member1=new Member();
        member1.setName("spring6");

        Member member2=new Member();
        member2.setName("spring6");

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

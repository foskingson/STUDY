package foskingson.jpabook.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import foskingson.jpabook.domain.Member;
import foskingson.jpabook.repository.MemberRepository;



import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("kim");
        // When
        Long saveId = memberService.join(member);
        // Then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외(){
        // Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        // When
        memberService.join(member1);
        // Then
        Assertions.assertThatThrownBy(() -> {
            memberService.join(member2); // 예외가 발생해야 한다.
        }).isInstanceOf(IllegalStateException.class);
    }
}
package hello.hellospring.repository;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import hello.hellospring.domain.Member;

// 테스트를 먼저 만들고 나중에 실제 애플리케이션을 만드는걸 테스트 주도 개발이라고 한다. (TDD)

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 하나의 테스트 코드가 끝날때마다 실행
    public void afterEach(){
        repository.clearStore(); //clearStore메서드 안에 있는 store.clear();를 통해 안에 데이터를 싹 비워줌
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");   //ctrl+엔터를 통해 현재 명령문을 완성하고 다음줄로 이동가능

        repository.save(member);
        Member result= repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);   // member와 result가 같은지 검증
    }

    @Test
    public void findByName(){
        Member member1=new Member();    // 컨트롤 + d로 똑같은 코드 한번에 변경 가능
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test 
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result= repository.findAll();
        assertThat(result.get(0).getName()).isEqualTo("spring1");
    }
}



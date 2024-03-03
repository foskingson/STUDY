package mvcStudy.mvcStudy.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();
    
    @AfterEach
    void testClearStore() {
        memberRepository.clearStore();
    }

    @Test
    void testFindAll() {
        Member member1 = new Member("하이11", 11);
        Member member2 = new Member("하이22", 122);

        memberRepository.save(member1);
        memberRepository.save(member2);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);

        List<Member> all = memberRepository.findAll();

        assertThat(members).isEqualTo(all);
        assertThat(all.size()).isEqualTo(2);

    }

    @Test
    void testFindById() {

    }

    @Test
    void testSave() {
        Member member = new Member("하이", 11);

        Member save = memberRepository.save(member);

        Member findMember= memberRepository.findById(save.getId());

        assertThat(member).isEqualTo(findMember);
    }
}

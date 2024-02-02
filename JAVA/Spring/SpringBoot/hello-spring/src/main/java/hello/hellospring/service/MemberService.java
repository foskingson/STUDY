package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.memberRepository;
import org.springframework.transaction.annotation.Transactional;

//@Service // 이걸 추가해야 스프링이 해당 자바파일을 인식하여 스프링컨테이너에 서비스로 등록해준다.

@Transactional  //데이터를 저장하거나 변경할때는 반드시 포함하기
public class MemberService {

    private final memberRepository memberRepository;

    //@Autowired
    public MemberService(memberRepository memberRepository){ //의존성주입(DI)
        this.memberRepository = memberRepository;
    }
    
    public Long join(Member member){    
 
        validateDuplicateMember(member);    // 중복회원 검증 
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //ctrl+쉬프트+r을 통해 리턴값 자동완성 Optional<Member> result / 리펙토링 기능도 있음
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}



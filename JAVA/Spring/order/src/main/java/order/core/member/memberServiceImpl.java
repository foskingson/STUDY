package order.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class memberServiceImpl implements MemberService {   // 구현체가 하나일경우 관례적으로 뒤에 Impl을 붙여서 사용한다
    private final MemberRepository memberRepository;

   
    @Autowired
    public memberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }    
    
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

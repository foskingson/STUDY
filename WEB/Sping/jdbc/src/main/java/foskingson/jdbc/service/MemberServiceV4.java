package foskingson.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import foskingson.jdbc.domain.Member;
import foskingson.jdbc.repository.MemberRepository;
import foskingson.jdbc.repository.MemberRepositoryV2;
import foskingson.jdbc.repository.MemberRepositoryV3;
import foskingson.jdbc.repository.MemberRepositoryV4_1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceV4 {    // 예외 누수 해결

    private final MemberRepository memberRepository;

    
    public MemberServiceV4(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void accountTransfer(String fromId,String toId, int money) throws SQLException{
        bizLogic(fromId, toId, money);
 
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney()-money);
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
            
        }
        memberRepository.update(toId, toMember.getMoney()+money);
    }
}

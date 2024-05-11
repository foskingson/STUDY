package foskingson.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import foskingson.jdbc.domain.Member;
import foskingson.jdbc.repository.MemberRepositoryV2;
import foskingson.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceV3_3 {    // 트랜잭션 AOP

    private final MemberRepositoryV3 memberRepository;

    
    public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
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

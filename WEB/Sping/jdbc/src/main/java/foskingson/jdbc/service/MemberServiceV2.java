package foskingson.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import foskingson.jdbc.domain.Member;
import foskingson.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {
    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId,String toId, int money) throws SQLException{
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);
            bizLogic(con,fromId, toId, money);
            con.commit();   // 성공시 커밋
        } catch (Exception e) {
            con.rollback(); // 실패시 롤백
            throw new IllegalStateException();
        }finally{
            release(con);
      }    
    }

    private void bizLogic(Connection con,String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con,fromId);
        Member toMember = memberRepository.findById(con,toId);

        memberRepository.update(con,fromId, fromMember.getMoney()-money);
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
            
        }
        memberRepository.update(con,toId, toMember.getMoney()+money);
    }

    private void release(Connection con) {
        if (con!=null) {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e) {
                log.info("error",e);
            }
        }
    }
}

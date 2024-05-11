package foskingson.jdbc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import static foskingson.jdbc.connection.ConnectionConst.*;

import foskingson.jdbc.domain.Member;
import foskingson.jdbc.repository.MemberRepositoryV3;

public class MemberServiceV3_1Test {
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV3 memberRepository;
    private MemberServiceV3_1 memberService;

    @BeforeEach
    void before(){
        DriverManagerDataSource datasource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        memberRepository= new MemberRepositoryV3(datasource);

        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
        memberService=new MemberServiceV3_1(transactionManager, memberRepository);
    }

    @AfterEach
    void after() throws SQLException{
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }
    
    @Test
    @DisplayName("정상 이체")
    void testAccountTransfer() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());
      

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 오류")
    void testAccountTransferEX() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        Member membeEx = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(membeEx);

        assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(), membeEx.getMemberId(), 2000))
            .isInstanceOf(IllegalStateException.class);
        

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMembeEx = memberRepository.findById(membeEx.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMembeEx.getMoney()).isEqualTo(10000);
    }
}

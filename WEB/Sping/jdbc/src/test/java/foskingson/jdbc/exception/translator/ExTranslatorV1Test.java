package foskingson.jdbc.exception.translator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import foskingson.jdbc.domain.Member;
import foskingson.jdbc.repository.ex.MyDbException;
import foskingson.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import static foskingson.jdbc.connection.ConnectionConst.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ExTranslatorV1Test {

    Repository repository;
    Service service;

    @BeforeEach
    void init() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void 중복키(){
        service.create("myid");
        service.create("myid");
    }

    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;
        public void create(String memberId){
            try {
                repository.save(new Member(memberId, 0));
                log.info("saveId={}",memberId);
            } catch (MyDuplicateKeyException e) {
                log.info("키중복, 복구시도");
                String reId = generateNewId(memberId);
                log.info("re ={}", reId);
                repository.save(new Member(reId, 0));
            }catch(MyDbException e){
                log.info("데이터계층에러", e);
            }
        }
        private String generateNewId(String memberId) {
            return memberId + new Random().nextInt(10000);
        }
    }

    
    @RequiredArgsConstructor
    static class Repository{
        private final DataSource dataSource;

        public Member save(Member member){
            String sql="insert into member(member_id,money) values(?,?)";
            Connection con=null;
            PreparedStatement pstmt=null;

            try {
                con=dataSource.getConnection();
                pstmt=con.prepareStatement(sql);
                pstmt.setString(1, member.getMemberId());
                pstmt.setInt(2, member.getMoney());
                pstmt.executeUpdate();
                return member;
            } catch (SQLException e) {
                if (e.getErrorCode()==23505) {
                    throw new MyDuplicateKeyException();
                }
                throw new MyDbException(e);
            }finally{
                JdbcUtils.closeStatement(pstmt);
                JdbcUtils.closeConnection(con);
            }
        }
       
    }
}

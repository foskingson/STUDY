package foskingson.jdbc.connection;

import static foskingson.jdbc.connection.ConnectionConst.PASSWORD;
import static foskingson.jdbc.connection.ConnectionConst.URL;
import static foskingson.jdbc.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionTest {
    
    @Test
    void driverManager() throws SQLException{
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());

    }

    @Test 
    void dataSourceDriverManager() throws SQLException {
        // 항상 새로운 커넥션 획득
        DataSource datasource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(datasource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target)
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        useDataSource(dataSource);
        Thread.sleep(1000); //커넥션 풀에서 커넥션 생성 시간 대기
    }    

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

}

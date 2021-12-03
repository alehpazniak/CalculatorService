package demo.dao;

import by.calculator.dao.OperationStatDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class OperationDAOTest {

    @Autowired
    OperationStatDao operationStatDao;

    @Autowired
    DataSource dataSource;

    @Test
    void test() {

    }



}
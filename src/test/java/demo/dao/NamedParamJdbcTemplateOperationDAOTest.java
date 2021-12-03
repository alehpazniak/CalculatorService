package demo.dao;

import by.calculator.dao.IOperationDao;
import by.calculator.domain.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamedParamJdbcTemplateOperationDAOTest {

    @Autowired
    IOperationDao operationDao;

    @Test
    void testMethodSave() {
        Operation operation = new Operation();

        operation.setOperationName("addition");
        operation.setArgFirst(5.5);
        operation.setArgSecond(3.1);
        operation.setResult(8.6);

        operationDao.save(operation);
    }
}
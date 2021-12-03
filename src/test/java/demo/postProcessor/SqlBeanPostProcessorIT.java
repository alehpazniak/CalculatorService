package demo.postProcessor;

import by.calculator.postProcessor.SqlBeanPostProcessor;
import by.calculator.postProcessor.SqlDataField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SqlBeanPostProcessorIT {

    SqlBeanPostProcessor sqlBeanPostProcessor = new SqlBeanPostProcessor();

    @SqlDataField("sql/operation_saveOperation.sql")
    String actualSqlString;


    @Test
    void test() {
        Assertions.assertEquals("INSERT INTO operation_log (operation_name, arg_first, arg_second, result)\n" +
                "VALUES(:operation_name, :arg_first, :arg_second, :result)", actualSqlString);
    }

    @Test()
    void exceptionTest()throws IOException {

        TestBean testBean = new TestBean();

        Exception exception = assertThrows(BeanCreationException.class, () ->
                sqlBeanPostProcessor.postProcessBeforeInitialization(testBean, "TestBean"));
        assertTrue(exception.getMessage().startsWith("Error created bean"));
    }


    public static class TestBean {

        @SqlDataField("sql/file_not_exist.sql")
        String notExistingSql;

        public String getNotExistingSql() {
            return notExistingSql;
        }
    }



}

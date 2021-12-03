package demo.postProcessor;

import by.calculator.dao.IOperationDao;
import by.calculator.postProcessor.SqlDataField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SqlBeanPostProcessorTest {

    @Autowired
    IOperationDao operationDAO;

    @SqlDataField("sql/operation_saveOperation.sql")
    String actualSqlString;

    @Test
    void readSqlFromFileInsideJar() throws IOException {
        InputStream resource = new ClassPathResource(
                "sql/operation_saveOperation.sql").getInputStream();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource))) {
            String employees = reader.lines()
                    .collect(Collectors.joining("\n"));

            assertEquals("INSERT INTO operation_log (operation_name, arg_first, arg_second, result)\n" +
                    "VALUES(:operation_name, :arg_first, :arg_second, :result)", actualSqlString);
        }
    }
}
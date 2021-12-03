package demo.dao;

import by.calculator.dao.OperationStatDao;
import by.calculator.domain.CountOperation;
import by.calculator.domain.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OperationStatDaoTest {

    @Autowired
    OperationStatDao operationStatDao;

    @Test
    void getLastOperationTest() throws ParseException {

        ExpectedDate expectedDate = new ExpectedDate();

        Operation expectedOperation = new Operation()
                .setId(8L)
                .setOperationName("multiply")
                .setArgFirst(1.0)
                .setArgSecond(2.0)
                .setResult(2.0)
                .setCreateDate(expectedDate.expectedDate);

        Operation actualOperation = operationStatDao.getLastOperation();

        assertEquals(expectedOperation.getId(), actualOperation.getId());
        assertEquals(expectedOperation.getOperationName(), actualOperation.getOperationName());
        assertEquals(expectedOperation.getArgFirst(), expectedOperation.getArgFirst());
        assertEquals(expectedOperation.getArgSecond(), actualOperation.getArgSecond());
        assertEquals(expectedOperation.getResult(), actualOperation.getResult());
        assertEquals(expectedOperation.getCreateDate(), actualOperation.getCreateDate());
    }

    @Test
    void getCountOperationsOnDate() throws ParseException {

        ExpectedDate expectedDate = new ExpectedDate();

        CountOperation countOperation = new CountOperation();
        countOperation.setOperationName("multiply");
        countOperation.setCount(1);

        List<CountOperation> expected = Arrays.asList(countOperation);

        List<CountOperation> actual = operationStatDao.getCountOperationsOnDate(expectedDate.expectedDate);

        assertTrue(actual.size() == 1);
        assertEquals(expected.get(0).getOperationName(), actual.get(0).getOperationName());
        assertEquals(expected.get(0).getCount(), actual.get(0).getCount());
    }

    @Test
    void getCountOperationsBetweenDate() throws ParseException {
        ExpectedDate expectedDate = new ExpectedDate();

        CountOperation countOperation = new CountOperation();
        countOperation.setOperationName("multiply");
        countOperation.setCount(1);

        List<CountOperation> expected = Arrays.asList(countOperation);

        List<CountOperation> actual =
                operationStatDao.getCountOperationsBetweenDate(
                        expectedDate.expectedDate, expectedDate.expectedDate);

        assertTrue(actual.size() == 1);
        assertEquals(expected.get(0).getOperationName(), actual.get(0).getOperationName());
        assertEquals(expected.get(0).getCount(), actual.get(0).getCount());
    }

    @Test
    void getMostPopularOperationsBetweenDate() throws ParseException {

        ExpectedDate expectedDate = new ExpectedDate();

        CountOperation countOperation = new CountOperation();
        countOperation.setOperationName("multiply");
        countOperation.setCount(4);

        List<CountOperation> expected = Arrays.asList(countOperation);

        List<CountOperation> actual =
                operationStatDao.getMostPopularOperationsBetweenDate(
                        expectedDate.expectedDate1, expectedDate.expectedDate);

        assertTrue(actual.size() == 1);
        assertEquals(expected.get(0).getOperationName(), actual.get(0).getOperationName());
        assertEquals(expected.get(0).getCount(), actual.get(0).getCount());

    }

    public static class ExpectedDate {
        String beginDate = "2021-10-19";
        String endDate = "2021-10-18";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedDate = formatter.parse(beginDate);
        Date expectedDate1 = formatter.parse(endDate);

        public ExpectedDate() throws ParseException {
        }
    }
}
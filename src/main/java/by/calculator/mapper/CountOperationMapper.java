package by.calculator.mapper;

import by.calculator.domain.CountOperation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountOperationMapper implements RowMapper<CountOperation> {

    @Override
    public CountOperation mapRow(ResultSet rs, int rowNum) throws SQLException {

        CountOperation countOperation = new CountOperation();
        countOperation.setOperationName(rs.getString("operation_name"));
        countOperation.setCount(rs.getInt("count"));
        return countOperation;
    }
}

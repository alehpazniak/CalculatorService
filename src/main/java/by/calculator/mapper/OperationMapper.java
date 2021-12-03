package by.calculator.mapper;

import by.calculator.domain.Operation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationMapper implements RowMapper<Operation> {


    @Override
    public Operation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Operation operation = new Operation();

        operation.setId(rs.getLong("id"));
        operation.setOperationName(rs.getString("operation_name"));
        operation.setArgFirst(rs.getDouble("arg_first"));
        operation.setArgSecond(rs.getDouble("arg_second"));
        operation.setResult(rs.getDouble("result"));
        operation.setCreateDate(rs.getDate("create_date"));
        return operation;
    }
}

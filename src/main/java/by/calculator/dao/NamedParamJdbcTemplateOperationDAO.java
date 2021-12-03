package by.calculator.dao;

import by.calculator.domain.Operation;
import by.calculator.postProcessor.SqlDataField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class NamedParamJdbcTemplateOperationDAO implements IOperationDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NamedParamJdbcTemplateOperationDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @SqlDataField("sql/operation_saveOperation.sql")
    private String SAVE_OPERATION_SQL;

    @Override
    public void save(Operation operation) {
        Map<String, Object> param = new HashMap<>();
        param.put("operation_name", operation.getOperationName());
        param.put("arg_first", operation.getArgFirst());
        param.put("arg_second", operation.getArgSecond());
        param.put("result", operation.getResult());

        namedParameterJdbcTemplate.update(SAVE_OPERATION_SQL, param);
    }
}

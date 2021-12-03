package by.calculator.dao;

import by.calculator.domain.CountOperation;
import by.calculator.domain.Operation;
import by.calculator.mapper.CountOperationMapper;
import by.calculator.mapper.OperationMapper;
import by.calculator.postProcessor.SqlDataField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class OperationStatDao {

    private static final HashMap<String, Object> EMPTY_PARAM_MAP = new HashMap<>();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OperationStatDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @SqlDataField("sql/operation_getLastOperation.sql")
    private String LAST_OPERATION_SQL;

    @SqlDataField("sql/operation_getCountOperationsOnDate.sql")
    private String COUNT_OPERATIONS_ON_DATE;

    @SqlDataField("sql/operation_getCountOperationsBetweenDate.sql")
    private String COUNT_OPERATIONS_BETWEEN_DATE;

    @SqlDataField("sql/operation_getMostPopularOperationsBetweenDate.sql")
    private String MOST_POPULAR_OPERATIONS_BETWEEN_DATE;

    public Operation getLastOperation() {
        return namedParameterJdbcTemplate.queryForObject(LAST_OPERATION_SQL, EMPTY_PARAM_MAP, new OperationMapper());
    }

    public List<CountOperation> getCountOperationsOnDate(Date date) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("date", date, Types.DATE);

        return namedParameterJdbcTemplate.query(COUNT_OPERATIONS_ON_DATE, parameters, new CountOperationMapper());
    }

    public List<CountOperation> getCountOperationsBetweenDate(Date begin, Date end) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("begin", begin, Types.DATE);
        parameters.addValue("end", end, Types.DATE);

        return namedParameterJdbcTemplate.query(COUNT_OPERATIONS_BETWEEN_DATE, parameters, new CountOperationMapper());
    }

    public List<CountOperation> getMostPopularOperationsBetweenDate(Date begin, Date end) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("begin", begin, Types.DATE);
        parameters.addValue("end", end, Types.DATE);

        return namedParameterJdbcTemplate.query(MOST_POPULAR_OPERATIONS_BETWEEN_DATE, parameters, new CountOperationMapper());
    }
}

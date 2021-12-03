package by.calculator.postProcessor;

import org.springframework.stereotype.Component;

@Component
public class SqlRequest {

    @SqlDataField("sql/operation_getLastOperation.sql")
    private String lastOperationSql;

    @SqlDataField("sql/operation_saveOperation.sql")
    private String saveOperation;

    @SqlDataField("sql/operation_getCountOperationsBetweenDate.sql")
    private String countOperationsBetweenDate;

    @SqlDataField("sql/operation_getCountOperationsOnDate.sql")
    private String countOperationsOnDate;

    @SqlDataField("sql/operation_getMostPopularOperationsBetweenDate.sql")
    private String mostPopularOperationsBetweenDate;

}

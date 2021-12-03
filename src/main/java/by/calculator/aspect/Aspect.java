package by.calculator.aspect;

import by.calculator.dao.NamedParamJdbcTemplateOperationDAO;
import by.calculator.domain.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    private NamedParamJdbcTemplateOperationDAO namedParamJdbcTemplateOperationDAO;

    @Autowired
    public Aspect(NamedParamJdbcTemplateOperationDAO namedParamJdbcTemplateOperationDAO) {
        this.namedParamJdbcTemplateOperationDAO = namedParamJdbcTemplateOperationDAO;
    }

    @Around("@annotation(AutoSave)")
    public Operation saveOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Operation operation = (Operation) joinPoint.proceed();
        namedParamJdbcTemplateOperationDAO.save(operation);
        return operation;
    }
}


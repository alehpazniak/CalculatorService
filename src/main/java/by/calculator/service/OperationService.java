package by.calculator.service;

import by.calculator.aspect.AutoSave;
import by.calculator.dao.IOperationDao;
import by.calculator.domain.Operation;
import by.calculator.util.Calculator;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private final IOperationDao dao;

    public OperationService(IOperationDao operationDAO) {
        this.dao = operationDAO;
    }

    @AutoSave
    public Operation addition(double a, double b) {
        Operation operation = new Operation();
        double result = Calculator.add(a, b);
        operation.setOperationName("ADDITION");
        operation.setArgFirst(a);
        operation.setArgSecond(b);
        operation.setResult(result);
        return operation;
    }

    @AutoSave
    public Operation multiply(double a, double b) {
        Operation operation = new Operation();
        double result = Calculator.multiply(a, b);
        operation.setOperationName("MULTIPLY");
        operation.setArgFirst(a);
        operation.setArgSecond(b);
        operation.setResult(result);
        return operation;
    }

    @AutoSave
    public Operation subtract(double a, double b) {
        Operation operation = new Operation();
        double result = Calculator.subtract(a, b);
        operation.setOperationName("SUBTRACT");
        operation.setArgFirst(a);
        operation.setArgSecond(b);
        operation.setResult(result);
        return operation;
    }

    @AutoSave
    public Operation divide(double a, double b) {
        Operation operation = new Operation();
        double result = Calculator.divide(a, b);
        operation.setOperationName("DIVIDE");
        operation.setArgFirst(a);
        operation.setArgSecond(b);
        operation.setResult(result);
        return operation;
    }
}

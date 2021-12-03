package by.calculator.controller;

import by.calculator.domain.Operation;
import by.calculator.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/calculator")
public class CalculatorController {
    private final OperationService operationService;

    @GetMapping("/addition/{a}/{b}")
    public Operation addition(@PathVariable(value = "a") double a,
                              @PathVariable(value = "b") double b) {
        return operationService.addition(a, b);
    }

    @GetMapping(value = "/multiply/{a}/{b}")
    public Operation multiply(@PathVariable(value = "a") double a,
                              @PathVariable(value = "b") double b) {
        return operationService.multiply(a, b);
    }

    @GetMapping(value = "/divide/{a}/{b}")
    public Operation divide(@PathVariable(value = "a") double a,
                            @PathVariable(value = "b") double b) {
        return operationService.divide(a, b);
    }

    @GetMapping(value = "/subtract/{a}/{b}")
    public Operation subtract(@PathVariable(value = "a") double a,
                              @PathVariable(value = "b") double b) {
        return operationService.subtract(a, b);
    }

}

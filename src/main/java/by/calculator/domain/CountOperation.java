package by.calculator.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CountOperation {
    private String operationName;
    private Integer count;
}

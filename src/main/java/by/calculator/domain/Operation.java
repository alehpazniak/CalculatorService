package by.calculator.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class Operation {

    private Long id;

    private String operationName;  //todo create ENUM
    private Double argFirst;    //todo use bigdecimal
    private Double argSecond;
    private Double result;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}

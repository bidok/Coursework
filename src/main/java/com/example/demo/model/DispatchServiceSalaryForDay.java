package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDay
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DispatchServiceSalaryForDay extends Audit{
    @Id
    @ApiModelProperty("salary id, must be UUID")
    private String id;
    @ApiModelProperty("salary, UAH")
    private Integer salary;


    public DispatchServiceSalaryForDay( int salary) {
        this.salary = salary;
    }
}

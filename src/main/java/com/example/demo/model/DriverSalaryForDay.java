package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : bidok
 * @created : 16.03.2021, вторник
 * @className : DriverSalaryForDay
 **/

@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DriverSalaryForDay extends Audit{
    @Id
    @ApiModelProperty("driver salary id, must be UUID")
    private String id;
    @DBRef
    @ApiModelProperty("driver, must be exist")
    private Driver driver;
    @ApiModelProperty("salary, UAH")
    private Integer salary;


    public DriverSalaryForDay(Driver driver, int salary) {
        this.driver = driver;
        this.salary = salary;
    }
}

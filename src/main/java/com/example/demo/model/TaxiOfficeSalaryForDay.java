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
 * @className : TaxiOfficeSalaryForDay
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TaxiOfficeSalaryForDay extends Audit{
    @Id
    @ApiModelProperty("taxi office salary id, must be UUID")
    private String id;
    @DBRef
    @ApiModelProperty("taxi office, must be exist")
    private TaxiOffice taxiOffice;
    @ApiModelProperty("salary, UAH")
    private Integer salary;

    public TaxiOfficeSalaryForDay(TaxiOffice taxiOffice, int salary) {
        this.taxiOffice = taxiOffice;
        this.salary = salary;
    }


}

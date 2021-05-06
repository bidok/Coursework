package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : bidok
 * @created : 16.03.2021, вторник
 * @className : DriverSalaryForInterval
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DriverSalaryForInterval extends Audit{
    @Id
    private String id;
    @DBRef
    private Driver driver;
    private LocalDate from;
    private LocalDate to;
    private Integer salary;

    public DriverSalaryForInterval(Driver driver, LocalDate from, LocalDate to, int salary) {
        this.driver = driver;
        this.from = from;
        this.to = to;
        this.salary = salary;
    }
}

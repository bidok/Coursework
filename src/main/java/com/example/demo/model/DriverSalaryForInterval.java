package com.example.demo.model;

import lombok.*;

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
public class DriverSalaryForInterval extends Audit{
    private String id;
    private Driver driver;
    private LocalDate from;
    private LocalDate to;
    private int salary;

    public DriverSalaryForInterval(Driver driver, LocalDate from, LocalDate to, int salary) {
        this.driver = driver;
        this.from = from;
        this.to = to;
        this.salary = salary;
    }
}

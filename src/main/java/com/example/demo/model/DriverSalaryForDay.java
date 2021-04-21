package com.example.demo.model;

import lombok.*;

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
public class DriverSalaryForDay extends Audit{
    private String id;
    private Driver driver;
    private int salary;


    public DriverSalaryForDay(Driver driver, int salary) {
        this.driver = driver;
        this.salary = salary;
    }
}

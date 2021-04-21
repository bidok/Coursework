package com.example.demo.model;

import lombok.*;

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
public class TaxiOfficeSalaryForDay extends Audit{
    private String id;
    private TaxiOffice taxiOffice;
    private List<Check> checks;
    private int salary;

    public TaxiOfficeSalaryForDay(TaxiOffice taxiOffice, int salary) {
        this.taxiOffice = taxiOffice;
        this.salary = salary;
    }


}

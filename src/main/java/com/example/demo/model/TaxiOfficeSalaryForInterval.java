package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author : bidok
 * @created : 10.03.2021, среда
 * @className : TaxiOfficeSalaryForInterval
 **/

@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class TaxiOfficeSalaryForInterval extends Audit{
    private String id;
    private TaxiOffice taxiOffice;
    private List<TaxiOfficeSalaryForDay> salaryForDays;
    private int salary;
    private LocalDate from;
    private LocalDate to;

    public TaxiOfficeSalaryForInterval(TaxiOffice taxiOffice, int salary,LocalDate from, LocalDate to) {
        this.taxiOffice = taxiOffice;
        this.salary = salary;
        this.from = from;
        this.to = to;
    }
}

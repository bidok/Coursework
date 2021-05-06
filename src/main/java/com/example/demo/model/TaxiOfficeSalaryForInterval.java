package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class TaxiOfficeSalaryForInterval extends Audit{
    @Id
    private String id;
    @DBRef
    private TaxiOffice taxiOffice;
    private Integer salary;
    private LocalDate from;
    private LocalDate to;

    public TaxiOfficeSalaryForInterval(TaxiOffice taxiOffice, int salary,LocalDate from, LocalDate to) {
        this.taxiOffice = taxiOffice;
        this.salary = salary;
        this.from = from;
        this.to = to;
    }
}

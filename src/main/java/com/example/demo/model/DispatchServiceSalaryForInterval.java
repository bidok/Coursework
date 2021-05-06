package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForInterval
 **/
@Document
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class DispatchServiceSalaryForInterval extends Audit{
    @Id
    private String id;
    private Integer salary;
    private LocalDate from;
    private LocalDate to;

    public DispatchServiceSalaryForInterval(Integer salary, LocalDate from, LocalDate to) {
        this.salary = salary;
        this.from = from;
        this.to = to;
    }
}

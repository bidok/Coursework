package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : TimeTable
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class TimeTable extends Audit{
    private String id;
    private LocalDate date;
    private LocalTime startWork;
    private LocalTime endWork;
    private Driver driver;
    private Operator operator;

    public TimeTable(String id, LocalDate date, LocalTime startWork, LocalTime endWork, Driver driver) {
        this.id = id;
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.driver = driver;
    }

    public TimeTable(String id, LocalDate date, LocalTime startWork, LocalTime endWork, Operator operator) {
        this.id = id;
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.operator = operator;
    }
}

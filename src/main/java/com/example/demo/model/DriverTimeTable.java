package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : DriverTimeTable
 **/
@Document
@NoArgsConstructor
public class DriverTimeTable extends TimeTable<Driver>{
    public DriverTimeTable(LocalTime startWork, LocalTime endWork, Driver worker) {
        super(startWork, endWork, worker);
    }
}

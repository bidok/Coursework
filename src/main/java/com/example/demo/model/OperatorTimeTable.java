package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTable
 **/


@Document
@NoArgsConstructor
public class OperatorTimeTable extends TimeTable<Operator>{

    public OperatorTimeTable( LocalTime startWork, LocalTime endWork, Operator worker) {
        super(startWork, endWork, worker);
    }
}

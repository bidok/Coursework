package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : TimeTable
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Document
public abstract class TimeTable<O> extends Audit{
    @Id
    private String id;
    private LocalTime startWork;
    private LocalTime endWork;
    @DBRef
    private O worker;

    public TimeTable( LocalTime startWork, LocalTime endWork, O worker) {
        this.id = id;
        this.startWork = startWork;
        this.endWork = endWork;
        this.worker = worker;
    }
}

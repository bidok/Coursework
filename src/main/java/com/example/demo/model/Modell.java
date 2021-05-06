package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author : bidok
 * @created : 04.02.2021, четверг
 * @className : Model
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Modell extends Audit{
    @Id
    private String id;
    private String name;
    private Marka marka;
    private CarClass carClass;
    private LocalDate date;

    public Modell(String name, Marka marka,  LocalDate year, CarClass carClass) {
        this.name = name;
        this.marka = marka;
        this.date = year;
        this.carClass = carClass;
    }
}

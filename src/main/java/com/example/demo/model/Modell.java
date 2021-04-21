package com.example.demo.model;

import lombok.*;

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
public class Modell{
    private String id;
    private String name;
    private Marka marka;
    private CarClass carClass;
    private Integer year;

    public Modell(String name, Marka marka, CarClass carClass, Integer year) {
        this.name = name;
        this.marka = marka;
        this.carClass = carClass;
        this.year = year;
    }
}

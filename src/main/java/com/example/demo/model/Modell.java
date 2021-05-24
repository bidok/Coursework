package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("model id, must be UUID")
    private String id;
    @ApiModelProperty("model name")
    private String name;
    @ApiModelProperty("marka, must be exist on Enum")
    private Marka marka;
    @ApiModelProperty("carClass, must be exist on Enum")
    private CarClass carClass;
    @ApiModelProperty("created date")
    private LocalDate date;

    public Modell(String name, Marka marka,  LocalDate year, CarClass carClass) {
        this.name = name;
        this.marka = marka;
        this.date = year;
        this.carClass = carClass;
    }
}

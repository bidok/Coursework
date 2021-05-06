package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author : bidok
 * @created : 09.03.2021, вторник
 * @className : Car
 **/

@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Car extends Audit{
    @ApiModelProperty("car id, must be UUID")
    private String id;
    @ApiModelProperty(value = "car number", example = "XX 0000 XX")
    private String carNumber;
    @ApiModelProperty("driver work or not")
    private Boolean state = false;
    @ApiModelProperty("places where driver was last time")
    private String location;
    private String insuranceNumber;
    @DBRef
    @ApiModelProperty("taxi service witch it belong, must be exist, enter with id")
    private TaxiOffice taxiOffice;
    @DBRef
    @ApiModelProperty("driver who ride on it, must be exist, enter with id")
    private Driver driver;
    @DBRef
    @ApiModelProperty("the model of car, must be exist, enter with id")
    private Modell modell;

    public Car(String carNumber, String location,String insuranceNumber, TaxiOffice taxiOffice, Driver driver, Modell modell) {
        this.carNumber = carNumber;
        this.insuranceNumber = insuranceNumber;
        this.taxiOffice = taxiOffice;
        this.driver = driver;
        this.modell = modell;
        this.location = location;
    }
}

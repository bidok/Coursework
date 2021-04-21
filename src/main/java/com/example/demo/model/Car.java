package com.example.demo.model;

import lombok.*;

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
    private String id;
    private String carNumber;
    private boolean state;
    private String location;
    private String insuranceNumber;
    private TaxiOffice taxiOffice;
    private Driver driver;
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

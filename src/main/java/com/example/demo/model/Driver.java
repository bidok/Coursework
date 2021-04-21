package com.example.demo.model;

import lombok.*;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 04.02.2021, четверг
 * @className : driver
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends Audit{
    private String id;
    private String name;
    private String phone;
    private Integer mark;
    private String licenseNumber;
    private TaxiOffice taxiOffice;

    public Driver( String name, String phone, int mark, String licenseNumber, TaxiOffice taxiOffice) {
        this.name = name;
        this.phone = phone;
        this.mark = mark;
        this.licenseNumber = licenseNumber;
        this.taxiOffice = taxiOffice;
    }

}

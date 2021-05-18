package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class Driver extends Audit{
    @Id
    private String id;
    private String name;
    private String phone;
    private Integer mark;
    private String licenseNumber;
    @DBRef
    private TaxiOffice taxiOffice;

    public Driver( String name, String phone, int mark, String licenseNumber, TaxiOffice taxiOffice) {
        this.name = name;
        this.phone = phone;
        this.mark = mark;
        this.licenseNumber = licenseNumber;
        this.taxiOffice = taxiOffice;
    }

}

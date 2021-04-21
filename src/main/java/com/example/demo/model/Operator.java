package com.example.demo.model;

import lombok.*;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : Operator
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Operator extends Audit{
    private String id;
    private String name;
    private String phoneNumber;
    private String identificationCode;
    private String passportNumber;

    public Operator( String name, String phoneNumber, String identificationCode, String passportNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identificationCode = identificationCode;
        this.passportNumber = passportNumber;
    }
}


package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : CarForm
 **/

@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class CarForm {
    private String carNumber;
    private String state;
    private String location;
    private String insuranceNumber;
    private String taxiOffice;
    private String driver;
    private String modell;

}

package com.example.demo.form;

import com.example.demo.model.TaxiOffice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverForm
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverForm {
    private String name;
    private String phone;
    private String mark;
    private String licenseNumber;
    private String taxiOffice;
}

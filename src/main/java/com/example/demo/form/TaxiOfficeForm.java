package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : bidok
 * @created : 24.03.2021, среда
 * @className : TaxiOfficeForm
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxiOfficeForm {
    private String name;
    private String phoneNumber;
    private String ownerName;
    private String licenseNumber;
}

package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorForm
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperatorForm {
    private String name;
    private String phoneNumber;
    private String identificationCode;
    private String passportNumber;
}

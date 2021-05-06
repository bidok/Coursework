package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTableForm
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperatorTimeTableForm {
    private String startWork;
    private String endWork;
    private String operator;

}

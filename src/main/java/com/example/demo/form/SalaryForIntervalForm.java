package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : SalaryForInterval
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryForIntervalForm {
    private String entity;
    private String salary;
    private String from;
    private String to;

}

package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 09.05.2021, воскресенье
 * @className : DriverStatisticBySalaryAndOrder
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverStatisticBySalaryAndOrder {
    private Driver driver;
    private Integer earned;
    private Long numOfOrder;
}

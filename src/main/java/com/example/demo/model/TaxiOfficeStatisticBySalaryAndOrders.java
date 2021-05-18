package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 09.05.2021, воскресенье
 * @className : TaxiOfficeStatisticBySalaryAndOrders
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxiOfficeStatisticBySalaryAndOrders {
    private TaxiOffice taxiOffice;
    private Integer earned;
    private Long completedOrders;
    private Long uncompletedOrders;

}

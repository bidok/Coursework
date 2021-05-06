package com.example.demo.form;

import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.Driver;
import com.example.demo.model.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : OrderForm
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private String orderNumber;
    private String startAddress;
    private String endAddress;
    private String isOutOfCity;
    private String customer;
    private String operator;
    private String car;
   // private String driver;
    private String completed;
}

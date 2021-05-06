package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : Order
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order extends Audit{
    @Id
    private String id;
    private String orderNumber;
    private String startAddress;
    private String endAddress;
    private Boolean isOutOfCity;
    @DBRef
    private Customer customer;
    @DBRef
    private Operator operator;
    @DBRef
    private Car car;
//    @DBRef
//    private Driver driver;
    private Boolean completed = false;


    public Order(String orderNumber , String startAddress, String endAddress, Operator operator, Car car, /*Driver driver,*/ Customer customer, boolean isOu) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.operator = operator;
        this.car = car;
        this.customer = customer;
       // this.driver =driver;
        isOutOfCity = isOu;
        this.orderNumber = orderNumber;
    }
}

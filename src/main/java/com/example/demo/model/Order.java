package com.example.demo.model;

import lombok.*;

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
public class Order extends Audit{
    private String id;
    private String startAddress;
    private String endAddress;
    private boolean isOutOfCity;
    private Customer customer;
    private Operator operator;
    private Car car;
    private Driver driver;
    private boolean completed;


    public Order( String startAddress, String endAddress, Operator operator, Car car, Driver driver, Customer customer) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.operator = operator;
        this.car = car;
        this.customer = customer;
        this.driver =driver;
    }

    public Order(String startAddress, String endAddress, Operator operator, Car car, Driver driver) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.operator = operator;
        this.car = car;
        this.driver =driver;
    }
}

package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("order id, must be UUID")
    private String id;
    @ApiModelProperty("order number")
    private String orderNumber;
    @ApiModelProperty("order start address")
    private String startAddress;
    @ApiModelProperty("order end address")
    private String endAddress;
    @ApiModelProperty("end address are out of city")
    private Boolean isOutOfCity;
    @DBRef
    @ApiModelProperty("order customer, must be exist")
    private Customer customer;
    @DBRef
    @ApiModelProperty("order operator, must be exist")
    private Operator operator;
    @DBRef
    @ApiModelProperty("order car, must be exist")
    private Car car;
//    @DBRef
//    private Driver driver;
    @ApiModelProperty("order is completed, default = false, auto changing")
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

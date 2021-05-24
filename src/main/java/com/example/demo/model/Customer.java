package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : bidok
 * @created : 17.03.2021, среда
 * @className : Customer
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer extends Audit{
    @Id
    @ApiModelProperty("customer id, must be UUID")
    private String id;
    @ApiModelProperty("customer name")
    private String name;
    @ApiModelProperty("customer phone")
    private String phoneNumber;
    @DBRef
    @ApiModelProperty("customer cad number, must be exist")
    private DiscountCard discountCard;

    public Customer(String name, String phoneNumber, DiscountCard discountCard) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discountCard = discountCard;
    }
}

package com.example.demo.model;

import lombok.*;

/**
 * @author : bidok
 * @created : 17.03.2021, среда
 * @className : Customer
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private DiscountCard discountCard;

    public Customer(String name, String phoneNumber, DiscountCard discountCard) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discountCard = discountCard;
    }
}

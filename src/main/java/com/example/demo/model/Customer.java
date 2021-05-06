package com.example.demo.model;

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
    private String id;
    private String name;
    private String phoneNumber;
    @DBRef
    private DiscountCard discountCard;

    public Customer(String name, String phoneNumber, DiscountCard discountCard) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discountCard = discountCard;
    }
}

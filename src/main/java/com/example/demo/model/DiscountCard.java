package com.example.demo.model;

import lombok.*;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : DiscountCard
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCard extends Audit{
    private String id;
    private String cardNumber;
    private int discount;
    private int distance;

    public DiscountCard( String cardNumber, int discount, int distance) {
        this.cardNumber = cardNumber;
        this.discount = discount;
        this.distance = distance;
    }
}

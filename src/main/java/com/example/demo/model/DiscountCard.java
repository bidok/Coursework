package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class DiscountCard extends Audit{
    @Id
    private String id;
    private String cardNumber;
    private Integer discount;
    private Integer distance;

    public DiscountCard( String cardNumber, int discount, int distance) {
        this.cardNumber = cardNumber;
        this.discount = discount;
        this.distance = distance;
    }
}

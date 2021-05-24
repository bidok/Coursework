package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("card id, must be UUID")
    private String id;
    @ApiModelProperty("card number, can`t repeat")
    private String cardNumber;
    @ApiModelProperty("discount, percents")
    private Integer discount;
    @ApiModelProperty("distance, km")
    private Integer distance;

    public DiscountCard( String cardNumber, int discount, int distance) {
        this.cardNumber = cardNumber;
        this.discount = discount;
        this.distance = distance;
    }
}

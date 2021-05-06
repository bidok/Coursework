package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DiscountCardForm
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCardForm {
    private String cardNumber;
    private String discount;
    private String distance;
}

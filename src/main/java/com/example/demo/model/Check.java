package com.example.demo.model;

import lombok.*;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

/**
 * @author : bidok
 * @created : 10.03.2021, среда
 * @className : Check
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Check extends Audit{
    private String id;
    private Order order;
    private double distance;
    private double price;

    public Check(Order order, double price, double distance) {
        this.order = order;
        this.price = price;
        this.distance = distance;
    }
}

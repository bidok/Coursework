package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : bidok
 * @created : 10.03.2021, среда
 * @className : Check
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Check extends Audit{
    @Id
    @ApiModelProperty("check id, must be UUID")
    private String id;
    @ApiModelProperty(value = "check number, ")
    private String checkNumber;
    @DBRef
    @ApiModelProperty("order where check het information, must be exist")
    private Order order;
    @ApiModelProperty("distance which car ride, counted in kilometers")
    private Integer distance;
    @ApiModelProperty("price which car ride, formula: [distance * 5/10 - discount]")
    private Double price;

    public Check(String checkNumber,Order order, double price, int distance) {
        this.checkNumber = checkNumber;
        this.order = order;
        this.price = price;
        this.distance = distance;
    }
}

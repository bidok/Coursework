package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : bidok
 * @created : 04.02.2021, четверг
 * @className : TaxiService
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
@Schema
public class TaxiOffice extends Audit{
    @Id
    private String id;
    @Schema(description = "Taxi Office Name", defaultValue = "some taxi office")
    private String name;
    @Schema(defaultValue = "+380*********")
    private String phoneNumber;
    @Schema(description = "Full name", defaultValue = "Marty McFly")
    private String ownerName;
    @Schema(defaultValue = "XXXX-XXXX-XXXX-XXXX")
    private String licenseNumber;

    public TaxiOffice(String name, String phoneNumber, String ownerName, String licenseNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.licenseNumber = licenseNumber;
    }
}

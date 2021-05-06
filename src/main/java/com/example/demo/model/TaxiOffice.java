package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

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
//@Schema
public class TaxiOffice extends Audit{
    @Id
    @ApiModelProperty("id of taxi service, must be UUID")
    private String id;
    @ApiModelProperty(value = "Name of taxi service", example = "some taxi")
    private String name;
    @ApiModelProperty(value = "Phone number of taxi service for quick access", example = "+380-XX-XXX-XXXX")
    private String phoneNumber;
    @ApiModelProperty(value = "Owner name of taxi service, consist of firs and last name",example = "Marty McFly")
    private String ownerName;
    @ApiModelProperty(value = "license number of taxi service", example = "XXXX-XXXX-XXXX-XXXX")
    private String licenseNumber;

    public TaxiOffice(String name, String phoneNumber, String ownerName, String licenseNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.licenseNumber = licenseNumber;
    }
}

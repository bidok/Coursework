package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author : bidok
 * @created : 18.02.2021, четверг
 * @className : Audit
 **/
@Data
public class Audit {
    private LocalDate createTime;
    private LocalDate updateTime;

    public Audit() {
        this.createTime = LocalDateTime.now(ZoneId.of("Europe/Kiev")).toLocalDate();
        this.updateTime = LocalDateTime.now(ZoneId.of("Europe/Kiev")).toLocalDate();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = LocalDateTime.now(ZoneId.of("Europe/Kiev")).toLocalDate();
    }
}

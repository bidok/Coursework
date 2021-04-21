package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : bidok
 * @created : 18.02.2021, четверг
 * @className : Audit
 **/
@Data
public class Audit {
    private final LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Audit() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}

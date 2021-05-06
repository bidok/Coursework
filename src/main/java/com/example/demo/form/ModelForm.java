package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : ModelForm
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelForm {
   private String name;
   private String marka;
   private String carClass;
   private String date;

}

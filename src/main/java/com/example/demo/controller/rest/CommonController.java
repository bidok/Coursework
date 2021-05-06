package com.example.demo.controller.rest;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Hidden
@RestController
public class CommonController {

    @RequestMapping(value = "/titular")
    public String showMainTitluar() throws FileNotFoundException {
        String str = "";
        Scanner scanner = new Scanner(new FileReader("src/main/resources/static/titular.html"));
        while (scanner.hasNextLine()){
            str += scanner.nextLine();
        }
        return str.replace("name", "Василь Бідяк Віталійович");
    }

    @RequestMapping(value = "/titular/{link}")
    public String showSecondaryTitular(@PathVariable(value = "link") String link) throws FileNotFoundException {
        String str = "";
        Scanner scanner = new Scanner(new FileReader("src/main/resources/static/titular.html"));
        while (scanner.hasNextLine()){
            str += scanner.nextLine();
        }
        return str.replace("name", link);
    }

}

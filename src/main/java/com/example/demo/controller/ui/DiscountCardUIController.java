package com.example.demo.controller.ui;

import com.example.demo.form.DiscountCardForm;
import com.example.demo.form.TaxiOfficeForm;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.discountCard.impls.DiscountCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DiscountCardUIController
 **/
@Controller
@RequestMapping("ui/discountcard")
@RequiredArgsConstructor
public class DiscountCardUIController {
    private  final DiscountCardServiceImpl service;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("discountCard", service.getAll());
        return "discountCard/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("discountCard", service.getById(id));
        return "discountCard/showById";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/discountcard/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("discountCardForm", new DiscountCardForm());
        return "discountCard/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("discountCardForm") DiscountCardForm discountCardForm) {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setCardNumber(discountCardForm.getCardNumber());
        discountCard.setDiscount(Integer.parseInt(discountCardForm.getDiscount()));
        discountCard.setDistance(Integer.parseInt(discountCardForm.getDistance()));
        service.save(discountCard);
        return "redirect:/ui/discountcard/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DiscountCard discountCard = service.getById(id);
        DiscountCardForm discountCardForm = new DiscountCardForm(
                discountCard.getCardNumber(),
                discountCard.getDiscount().toString(),
                discountCard.getDistance().toString()
        );
        model.addAttribute("discountCardForm", discountCardForm);
        return "discountCard/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("discountCardForm") DiscountCardForm discountCardForm){
        DiscountCard discountCard = service.getById(id);
        discountCard.setCardNumber(discountCardForm.getCardNumber());
        discountCard.setDistance(Integer.parseInt(discountCardForm.getDistance()));
        discountCard.setDiscount(Integer.parseInt(discountCardForm.getDiscount()));
        service.save(discountCard);
        return "redirect:/ui/discountcard/get/all";
    }

}

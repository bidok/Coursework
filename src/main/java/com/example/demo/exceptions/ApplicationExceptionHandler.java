package com.example.demo.exceptions;

import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ApplicationExceptionHandler
 **/


@ControllerAdvice("com.example.demo.controller.ui")
public class ApplicationExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("errors/ObjectNotFound", HttpStatus.NOT_FOUND);
        modelAndView.addObject("number", 404);
        modelAndView.addObject("info", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(InvalidDataException.class)
    public ModelAndView invalidDataException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("errors/InvalidData", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("number", 400);
        modelAndView.addObject("info", exception.getMessage());
        return modelAndView;
    }

}

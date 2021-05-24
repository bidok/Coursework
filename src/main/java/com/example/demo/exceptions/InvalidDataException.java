package com.example.demo.exceptions;

import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : InvalidDataException
 **/

public class InvalidDataException extends RuntimeException{
    private static  final Logger LOGGER = LoggerFactory.getLogger(InvalidDataException.class);
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
        LOGGER.error(message);
    }
}

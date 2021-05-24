package com.example.demo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ObjectNotFoundException
 **/

public class ObjectNotFoundException extends RuntimeException{
    private static  final Logger LOGGER = LoggerFactory.getLogger(ObjectNotFoundException.class);
    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
        LOGGER.error(message);
    }
}

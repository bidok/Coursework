package com.example.demo.exceptions;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ObjectNotFoundException
 **/

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}

package com.example.demo.exceptions;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : InvalidDataException
 **/

public class InvalidDataException extends RuntimeException{
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }
}

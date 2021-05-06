package com.example.demo.service;

import com.example.demo.model.Driver;

import java.util.List;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : IGenericService
 **/

public interface IGenericService <E>{
    E getById(String id);
    List<E> getAll();;
    E save(E type);
    E deleteById(String id);
}

package com.example.demo.dao;

import com.example.demo.model.TaxiOffice;

import java.util.List;

/**
 * @author : bidok
 * @created : 18.02.2021, четверг
 * @className : IGenericDAO
 **/

public interface IGenericDAO<E> {
    List<E> getAll();
    E getById(String id);
    E create(E e);
    E update(E e);
    E delete(String id);
}

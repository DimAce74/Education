package ru.itis.services;

import java.util.List;

public interface BaseService<T> {
    T find (int id);
    int save (T t);
    void delete (int id);
    List<T> findAll ();
}

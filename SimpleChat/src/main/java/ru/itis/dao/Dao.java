package ru.itis.dao;


import java.util.List;

public interface Dao<T> {
    T find (int id);
    int save (T t);
    void delete (int id);
    List<T> findAll ();
}

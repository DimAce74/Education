package ru.itis.dao;

import ru.itis.models.Auto;

import java.util.List;

public interface AutoDao {
    Auto find(int id);
    boolean save(Auto auto);
    boolean update(Auto auto);
    boolean delete(int id);
    List<Auto> findAll();
}

package ru.itis.dao;

import ru.itis.models.Auto;

import java.util.List;

public interface AutoDao {
    Auto find(int id);
    int save(Auto auto);
    int update(Auto auto);
    int delete(int id);
    List<Auto> findAll();
}

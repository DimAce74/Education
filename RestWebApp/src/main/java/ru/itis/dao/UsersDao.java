package ru.itis.dao;

import ru.itis.models.User;

import java.util.List;


public interface UsersDao {
    User find(int id);
    int save(User user);
    int update(User user);
    int delete(int id);
    List<User> findAll();
}

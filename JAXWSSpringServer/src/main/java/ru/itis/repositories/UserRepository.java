package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}

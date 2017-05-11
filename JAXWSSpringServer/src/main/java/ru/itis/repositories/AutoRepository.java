package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Auto;

public interface AutoRepository extends CrudRepository<Auto, Integer> {
}

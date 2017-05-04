package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.itis.services.UserService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUser(int id) {
        return userRepository.findOne(id);
    }

    public boolean deleteUser(int id) {
        userRepository.delete(id);
        return true;
    }

    public boolean saveUser(User user) {
        userRepository.save(user);
        return true;
    }
}

package ru.itis.services;

import ru.itis.models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface UserService {
    @WebMethod
    List<User> findAllUsers();

    @WebMethod
    User findUser (int id);

    @WebMethod
    boolean deleteUser (int id);

    @WebMethod
    boolean saveUser (User user);

}

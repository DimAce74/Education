package ru.itis.factories;


import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UsersServiceFactoryException;
import ru.itis.services.UsersService;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class UsersServiceFactory {
    private static UsersServiceFactory instance;
    private UsersService usersService;
    static {
        instance = new UsersServiceFactory();
    }

    public static UsersServiceFactory getInstance() {
        return instance;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    private UsersServiceFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Development\\Education\\SimpleWebApp\\src\\main\\resources\\application.properties"));
            String usersServiceClassName = properties.getProperty("users.service.class");
            Class<UsersService> usersServiceClass = (Class<UsersService>)Class.forName(usersServiceClassName);
            Constructor<UsersService> usersServiceConstructor = usersServiceClass.getConstructor(UsersDao.class);
            usersService=usersServiceConstructor.newInstance(UserDaoFactory.getInstance().getUsersDao());
        }catch (Exception e) {
            throw new UsersServiceFactoryException();
        }
    }
}

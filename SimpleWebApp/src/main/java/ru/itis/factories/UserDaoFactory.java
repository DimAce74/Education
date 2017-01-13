package ru.itis.factories;


import org.hibernate.Session;
import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UserDaoTypeException;
import ru.itis.exceptions.UsersDaoFactoryException;
import ru.itis.hibernate.HibernateConnector;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDaoFactory instance;
    private UsersDao usersDao;
    static {
        instance = new UserDaoFactory();
    }

    public static UserDaoFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    private UserDaoFactory () {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Development\\Education\\SimpleWebApp\\src\\main\\resources\\application.properties"));
            String userDaoType = properties.getProperty("users.dao.type");
            if (userDaoType.equals("jdbc")) {
                String userDaoClassName = properties.getProperty("user.dao.jdbc");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(userDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(DataSource.class);
                usersDao = usersDaoConstructor.newInstance(DataSourceFactory.getInstance().getDataSource());

            }else{
                throw new UserDaoTypeException();
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new UsersDaoFactoryException();
        }
    }
}

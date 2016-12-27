package ru.itis.factories;


import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UserDaoTypeException;
import ru.itis.exceptions.UsersDaoFactoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
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
            properties.load(new FileInputStream("D:\\Development\\Education\\users-dao-project-best\\src\\main\\resources\\application.properties"));
            String userDaoType = properties.getProperty("users.dao.type");
            if (userDaoType.equals("jdbc")) {
                String url = properties.getProperty("url");
                String login = properties.getProperty("user");
                String password = properties.getProperty("password");
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, login, password);
                String userDaoClassName = properties.getProperty("jdbc.dao");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(userDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(Connection.class);
                usersDao = usersDaoConstructor.newInstance(connection);
            } else if (userDaoType.equals("file")){
                String autoFilePath = properties.getProperty("auto.file");
                String userFilePath = properties.getProperty("user.file");
                File autoFile = new File(autoFilePath);
                File userFile = new File(userFilePath);
                String userDaoClassName = properties.getProperty("file.dao");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(userDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(File.class,File.class);
                usersDao = usersDaoConstructor.newInstance(userFile, autoFile);

            }else{
                throw new UserDaoTypeException();
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException | SQLException e) {
            throw new UsersDaoFactoryException();
        }
    }
}

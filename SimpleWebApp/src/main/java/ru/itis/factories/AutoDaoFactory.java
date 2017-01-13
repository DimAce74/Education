package ru.itis.factories;

import ru.itis.dao.AutoDao;
import ru.itis.exceptions.AutoDaoFactoryException;
import ru.itis.exceptions.AutoDaoTypeException;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by dimac on 12.01.2017.
 */
public class AutoDaoFactory {
    private static AutoDaoFactory instance;
    private AutoDao autoDao;
    static {
        instance = new AutoDaoFactory();
    }

    public static AutoDaoFactory getInstance() {
        return instance;
    }

    public AutoDao getAutoDao() {
        return autoDao;
    }

    private AutoDaoFactory () {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Development\\Education\\SimpleWebApp\\src\\main\\resources\\application.properties"));
            String autoDaoType = properties.getProperty("auto.dao.type");
            if (autoDaoType.equals("jdbc")) {
                String autoDaoClassName = properties.getProperty("auto.dao.jdbc");
                Class<AutoDao> autoDaoClass = (Class<AutoDao>) Class.forName(autoDaoClassName);
                Constructor<AutoDao> autoDaoConstructor = autoDaoClass.getConstructor(DataSource.class);
                autoDao = autoDaoConstructor.newInstance(DataSourceFactory.getInstance().getDataSource());

            }else{
                throw new AutoDaoTypeException();
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new AutoDaoFactoryException();
        }
    }
}

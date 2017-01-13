package ru.itis.factories;

import ru.itis.dao.AutoDao;
import ru.itis.exceptions.AutoServiceFactoryException;
import ru.itis.services.AutoService;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class AutoServiceFactory {
    private static AutoServiceFactory instance;
    private AutoService autoService;
    static {
        instance = new AutoServiceFactory();
    }

    public static AutoServiceFactory getInstance() {
        return instance;
    }

    public AutoService getAutoService() {
        return autoService;
    }

    private AutoServiceFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Development\\Education\\SimpleWebApp\\src\\main\\resources\\application.properties"));
            String autoServiceClassName = properties.getProperty("auto.service.class");
            Class<AutoService> autoServiceClass = (Class<AutoService>)Class.forName(autoServiceClassName);
            Constructor<AutoService> autoServiceConstructor = autoServiceClass.getConstructor(AutoDao.class);
            autoService=autoServiceConstructor.newInstance(AutoDaoFactory.getInstance().getAutoDao());
        }catch (Exception e) {
            throw new AutoServiceFactoryException();
        }
    }
}

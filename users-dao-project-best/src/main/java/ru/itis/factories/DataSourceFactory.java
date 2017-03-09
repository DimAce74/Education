package ru.itis.factories;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.exceptions.DataSourceFactoryException;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by dimac on 29.12.2016.
 */
public class DataSourceFactory {
    private static DataSourceFactory instance;
    private DataSource dataSource;
    static {
        instance=new DataSourceFactory();
    }

    private DataSourceFactory () {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("F:\\JavaLes\\Education\\users-dao-project-best\\src\\main\\resources\\application.properties"));
            String userDaoType = properties.getProperty("users.dao.type");
            if (userDaoType.equals("jdbc")) {
                String url = properties.getProperty("url");
                String login = properties.getProperty("user");
                String password = properties.getProperty("password");
                String driverClassName = properties.getProperty("db.driver");
                DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url, login, password);
                driverManagerDataSource.setDriverClassName(driverClassName);
                dataSource = driverManagerDataSource;
            }
        } catch (Exception e) {
            throw new DataSourceFactoryException();
        }
    }

    public static DataSourceFactory getInstance() {
        return instance;
    }

    public DataSource getDataSource(){
        return this.dataSource;
    }
}

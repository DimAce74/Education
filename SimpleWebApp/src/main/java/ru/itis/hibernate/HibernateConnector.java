package ru.itis.hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.exceptions.HiberException;

import java.io.FileInputStream;
import java.util.Properties;

public class HibernateConnector {
    private static HibernateConnector connector;
    private Configuration configuration;
    private SessionFactory sessionFactory;

    private HibernateConnector() {
        configuration = new Configuration();

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Development\\Education\\SimpleWebApp\\src\\main\\resources\\application.properties"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driverClassName = properties.getProperty("db.driver");
            String showSql = properties.getProperty("hibernate.show_sql");
            String dialect = properties.getProperty("hibernate.dialect");
            String hbm2dll = properties.getProperty("hibernate.hbm2ddl.auto");
            String userHbm = properties.getProperty("user.hbm.file");
            String autoHbm = properties.getProperty("auto.hbm.file");

            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.driver_class", driverClassName);
            configuration.setProperty("hibernate.connection.username", login);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.show_sql", showSql);
            configuration.setProperty("hibernate.dialect", dialect);

            configuration.setProperty("hibernate.hbm2ddl.auto", hbm2dll);

            configuration.addResource(userHbm);
            configuration.addResource(autoHbm);

            sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
            throw new HiberException();
            }
    }

    static {
        connector = new HibernateConnector();
    }

    public static HibernateConnector getConnector() {
        return connector;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() throws HibernateException {
        this.sessionFactory = configuration.buildSessionFactory();
    }
}

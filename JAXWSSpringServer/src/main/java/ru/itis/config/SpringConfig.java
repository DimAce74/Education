package ru.itis.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan("ru.itis")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] {"ru.itis.models"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/auto_user");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("1234");
        return driverManagerDataSource;
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put ("hibernate.show_sql", "true");
        properties.put ("hibernate.format_sql", "true");
        properties.put ("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager txManager(SessionFactory sessionFactory) throws Exception {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}

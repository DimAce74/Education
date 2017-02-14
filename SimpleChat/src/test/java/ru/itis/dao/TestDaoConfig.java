package ru.itis.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.itis.dao.hibernate.ChatDaoHibernateImpl;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestDaoConfig {

    @Bean
    ChatDaoHibernateImpl chatDao() {
        return new ChatDaoHibernateImpl(sessionFactory().getObject());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] {"ru.itis.models"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("resources/sql/schema.sql")
                .addScript("resources/sql/data.sql")
                .build();
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put ("hibernate.show_sql", "true");
        properties.put ("hibernate.format_sql", "true");
        properties.put ("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }


}

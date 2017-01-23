package ru.itis.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;
import ru.itis.models.Message;
import ru.itis.models.ChatSession;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("ru.itis")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.addAnnotatedClass(Chat.class);
        builder.addAnnotatedClass(ChatUser.class);
        builder.addAnnotatedClass(Message.class);
        builder.addAnnotatedClass(ChatSession.class);
        builder.setProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    private DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/chat_bd");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("1234");
        return driverManagerDataSource;
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put ("hibernate.show_sql", "true");
        properties.put ("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put ("hibernate.max fetch depth", 3);
        properties.put ("hibernate.jdЬc.fetch_size", 50);
        properties.put ("hibernate.jdЬc.batch_size", 10);
        return properties;
    }

}

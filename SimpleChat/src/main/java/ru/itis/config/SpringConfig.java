package ru.itis.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;
import ru.itis.models.Message;
import ru.itis.models.ChatSession;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ru.itis")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(new DriverManagerDataSource());
        builder.addAnnotatedClass(Chat.class);
        builder.addAnnotatedClass(ChatUser.class);
        builder.addAnnotatedClass(Message.class);
        builder.addAnnotatedClass(ChatSession.class);
        builder.setProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }
/**
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/chat_bd");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("1234");
        return driverManagerDataSource;
    }
*/
    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put ("hibernate.show_sql", "true");
        properties.put ("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put ("hibernate.max fetch depth", 3);
        properties.put ("hibernate.jdЬc.fetch_size", 50);
        properties.put ("hibernate.jdЬc.batch_size", 10);
        properties.put ("hibernate.c3p0.min_size", 5);
        properties.put ("hibernate.c3p0.max_size", 20);
        properties.put ("hibernate.c3p0.timeout", 300);
        properties.put ("hibernate.c3p0.max_statements", 50);
        properties.put ("hibernate.c3p0.idle_test_period", 3000);
        properties.put ("hibernate.transaction.flush_before_completion", true);
        properties.put ("hibernate.transaction.auto_close_session", true);
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");

        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/chat_bd");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "1234");

        return properties;
    }

    @Bean
    public PlatformTransactionManager txManager() throws Exception {
        HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory());
        txManager.setNestedTransactionAllowed(true);
        //txManager.setSessionFactory(sessionFactory());
        return txManager;
    }

}

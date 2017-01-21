package ru.itis.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@EnableAutoConfiguration
@Configuration
@EnableWebMvc
@ComponentScan("ru.itis")
public class SpringAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean asFactoryBean = new LocalSessionFactoryBean();
        asFactoryBean.setHibernateProperties(getHibernateProperties());
        asFactoryBean.setMappingResources("ru.itis.hibernate/Auto.hbm.xml", "ru.itis.hibernate/User.hbm.xml");
        return asFactoryBean.getObject();
    }
    private static Properties getHibernateProperties () {
        Properties properties = new Properties();
        properties.put ("hibernate.show_sql", "true");
        properties.put ("hibernate.hbm2ddl.auto", "update");
        properties.put ("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put ("hibernate.c3p0.min_size", 5);
        properties.put ("hibernate.c3p0.max_size", 20);
        properties.put ("hibernate.c3p0.timeout", 300);
        properties.put ("hibernate.c3p0.max_statements", 50);
        properties.put ("hibernate.c3p0.idle_test_period", 3000);
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/auto_user");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "1234");
        return properties;
    }

}

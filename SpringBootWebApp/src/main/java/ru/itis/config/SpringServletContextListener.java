package ru.itis.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SpringServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ru.itis.context\\application-context.xml");
        sce.getServletContext().setAttribute("hibernateSpringContext", applicationContext);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

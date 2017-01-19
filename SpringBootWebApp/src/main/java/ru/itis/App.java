package ru.itis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.models.Auto;
import ru.itis.models.User;
import ru.itis.services.AutoService;
import ru.itis.services.UsersService;
@Component
public class App {

    public static void main(String[] args) {
/**
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "ru.itis.context\\application-context.xml"
        );
        UsersService usersService = (UsersService)context.getBean("usersService");
        AutoService autoService = (AutoService) context.getBean("autoService");
        User user = usersService.findUser(6);
        Auto auto = autoService.findAuto(1);
        System.out.println(user.getName() + " " + auto.getModel());

*/
    }


}

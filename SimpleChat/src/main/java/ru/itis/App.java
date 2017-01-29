package ru.itis;

import org.postgresql.util.PSQLException;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatDao;
import ru.itis.dao.hibernate.ChatDaoHibernateImpl;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;
import ru.itis.services.ChatService;
import ru.itis.services.ChatUserService;

import java.util.List;
/**
@Component
public class App {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(SpringConfig.class);
        context.refresh();
        ChatUserService chatUserService =context.getBean("ChatUserService", ChatUserService.class);

        chatUserService.saveUserToChat(1,2);
    }
}
*/
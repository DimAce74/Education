package ru.itis;

import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatDao;
import ru.itis.dao.hibernate.ChatDaoHibernateImpl;
import ru.itis.models.Chat;

import java.util.List;

@Component
public class App {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(SpringConfig.class);
        context.refresh();
        ChatDao chatDao=context.getBean("ChatDao", ChatDao.class);

        Chat chat = new Chat.Builder().id(3).name("4 chat").build();
        //chatDao.save(chat);
        /**
        System.out.println(chatDao.find(1));
        System.out.println(chatDao.findAll().get(2));


        List<Chat> chats = chatDao.findAll();
        for(Chat chat2 : chats){
            System.out.println(chat2.getName());
        }
        //Chat chat = chatDao.find(3);
         */
        chatDao.delete(3);

        List<Chat> chats1 = chatDao.findAll();
        for(Chat chat3 : chats1){
            System.out.println(chat3.getName());
        }
    }
}

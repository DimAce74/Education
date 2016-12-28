package ru.itis;


import ru.itis.factories.UserDaoFactory;
import ru.itis.services.UsersService;
import ru.itis.dao.UsersDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//TODO: Сделать добавление пользователя

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UsersDao usersDao = UserDaoFactory.getInstance().getUsersDao();
        UsersService usersService = new UsersService(usersDao);

        System.out.println("Что хочешь найти?");
        System.out.println("1) Имя пользователя по ID.");
        System.out.println("2) Модели машин пользователя по его ID.");
        System.out.println("Введи 1 или 2:");
        int choice1=0;
        try {
            choice1 = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ID пользователя:");
        int choice2=0;
        try {
            choice2 = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(choice1==1){
            System.out.println(usersService.ShowNameUsersById(choice2));
            System.out.println("До свидания!");
        }else if (choice1==2){
            System.out.println(usersService.ShowModelAllUsersAutoById(choice2));
            System.out.println("До свидания!");
        }else{
            System.out.println("Такого пункта нет в меню! До свидания!");
        }

        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

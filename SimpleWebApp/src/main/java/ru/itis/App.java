package ru.itis;


import ru.itis.dao.UsersDao;
import ru.itis.factories.UserDaoFactory;
import ru.itis.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UsersDao usersDao = UserDaoFactory.getInstance().getUsersDao();
        UsersService usersService = new UsersService(usersDao);

        //System.out.println(usersService.ShowAllUsersNames());
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
            System.out.println(usersService.showNameUsersById(choice2));
            System.out.println(usersDao.find(choice2).getListAuto().size());
            System.out.println("До свидания!");
        }else if (choice1==2){
            System.out.println(usersService.showModelAllUsersAutoById(choice2));
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

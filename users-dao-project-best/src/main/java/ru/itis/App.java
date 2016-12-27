package ru.itis;


import ru.itis.Services.UsersService;
import ru.itis.dao.UsersDao;
import ru.itis.dao.jdbc.UserDaoJDBCImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/auto_user";
        String login = "postgres";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter password please:");

        String pass = null;

        try {
            pass = reader.readLine();
        } catch (IOException e) {
            throw new IllegalAccessError("Invalid password!");
        }

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, pass);
        }catch (Exception e) {
            throw new IllegalAccessError("Invalid password!");
        }

        UsersDao usersDao = new UserDaoJDBCImpl(connection);
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
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

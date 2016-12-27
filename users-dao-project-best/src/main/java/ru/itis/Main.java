package ru.itis;

import ru.itis.dao.AutoDao;
import ru.itis.dao.UsersDao;
import ru.itis.dao.jdbc.AutoDaoJDBCImpl;
import ru.itis.dao.jdbc.UserDaoJDBCImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/auto_user";
        String login = "postgres";
        String pass = "1234";
/**
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter password please:");

        String pass = null;

        try {
            pass = reader.readLine();
        } catch (IOException e) {
            throw new IllegalAccessError("Invalid password!");
        }
        */
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, pass);
        }catch (Exception e) {
            e.printStackTrace();
        }

        UsersDao usersDao = new UserDaoJDBCImpl(connection);
        AutoDao autoDao = new AutoDaoJDBCImpl(connection);

        //User user = usersDao.find(1);
        //System.out.println(user.getAge());
        //user.setAge(33);
        //user.setName("Katya");
        //usersDao.update(user);
        //System.out.println(user.getAge());

        User user1 = new User ("Vitya", 28);
        usersDao.save(user1);
        //System.out.println(usersDao.find(5).getName());
        //Auto auto = new Auto ("Nissan", "red", 3);
        //autoDao.save(auto);
        //System.out.println(usersDao.findAllUsersAuto(4).get(0).getModel());

        //usersDao.delete(4);
    }
}

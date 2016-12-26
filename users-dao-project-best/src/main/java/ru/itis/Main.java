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
            e.printStackTrace();
        }

        UsersDao usersDao = new UserDaoJDBCImpl(connection);
        AutoDao autoDao = new AutoDaoJDBCImpl(connection);

        //User user = usersDao.find(1);
        //user.setAge(44);
        //usersDao.update(user);
        //User user = new User (4, "Pasha", 45);
        //usersDao.save(user);
        //Auto auto = new Auto (4, "Nissan", "red", 4);
        //autoDao.save(auto);
        //usersDao.delete(4);




        //ru.itis.dao.UsersDao usersDao = new ru.itis.dao.files.UsersDaoFileBasedImpl(new File("users.txt"), new File("auto.txt"));
        //ru.itis.dao.AutoDao autoDao = new ru.itis.dao.files.AutoDaoFileBasedImpl();

        //System.out.println(usersDao.find(2).getName());

        //ru.itis.User user = new ru.itis.User ( "Sanya", 55);
        //usersDao.save(user);
        //for (User user1 : usersDao.findAll()) {
        //System.out.println(user1.getName() + " ");
        //}
        //User user = usersDao.find(1);
        //List<Auto> autoList = user.getListAuto();
        //for (Auto auto : usersDao.findAllUsersAuto(1)) {
        //    System.out.println(auto.getModel());
        //}
        //user.setAge(122);
        //usersDao.update(user);
        //usersDao.delete(3);
        //for (ru.itis.User user1 : usersDao.findAll()) {
        //    System.out.print(user1.getName() + " ");
        //}
        /**
        Auto auto = autoDao.find(3);
        System.out.println(auto.getModel());
        Auto auto1 = new Auto (4, "Renault", "red", 1);
        //autoDao.save(auto1);
        System.out.println(autoDao.find(4).getModel());
        //System.out.println(auto.getUser().getName());
        auto1.setColor("green");
        autoDao.update(auto1);
        System.out.println(autoDao.find(4).getColor());
        autoDao.delete(4);
        List<Auto> autoList = autoDao.findAll();
        for( Auto auto2: autoList){
            System.out.println(auto2.getModel());
        }
        //System.out.println(auto.getUser().getName());
        //ru.itis.User user = new ru.itis.User ("Petya", 37);
        //System.out.println(user.getId());
        //System.out.println(user.getListAuto());
        //usersDao.save(user);
        //System.out.println(user.getId());
        //System.out.println(user.getListAuto().get(0).getModel());
*/
    }
}

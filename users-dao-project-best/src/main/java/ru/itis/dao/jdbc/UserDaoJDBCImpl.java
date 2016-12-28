package ru.itis.dao.jdbc;


import ru.itis.Auto;
import ru.itis.exceptions.SavingUserException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.User;
import ru.itis.dao.UsersDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UsersDao{

        private Connection connection;

        public UserDaoJDBCImpl(Connection connection) {
              this.connection = connection;
        }

    @Override
    public User find(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM group_user WHERE id="+id);
            resultSet.next();
            int userId = resultSet.getInt("id");
            String userName = resultSet.getString("user_name");
            int userAge = resultSet.getInt("user_age");
            User user = new User(userId, userName, userAge);
            resultSet = statement.executeQuery("SELECT * FROM auto WHERE user_id="+id);
            List<Auto> autoList = new ArrayList<>();
            while (resultSet.next()) {
                int autoId = resultSet.getInt("auto_id");
                String model = resultSet.getString("auto_model");
                String color = resultSet.getString("auto_color");
                int userId1 = resultSet.getInt("user_id");
                Auto auto = new Auto(autoId, model, color, userId1);
                autoList.add(auto);
            }
            user.setListAuto(autoList);

            return user;

        } catch (SQLException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO group_user(user_name, user_age) VALUES (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new SavingUserException();
        }
        return true;
    }

    @Override
    public boolean update(User user) {

        try {
            find(user.getId());
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE group_user SET user_name='" +
                    user.getName()+"', user_age="+user.getAge()+" WHERE id="+user.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("User not updated!");
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            find(id);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM group_user WHERE id="+id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalAccessError("User not deleted!");
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM group_user");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                int userAge = resultSet.getInt("user_age");
                User user = new User(userId, userName, userAge);
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            throw new IllegalAccessError("UserList do not ctreated!");
        }
    }

    public List<Auto> findAllUsersAuto(int id) {
        try {
            find(id);
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT auto_id, auto_model, auto_color FROM auto WHERE user_id="+id);
            List<Auto> autoList = new ArrayList<>();
            while (resultSet.next()) {
                int autoId = resultSet.getInt("auto_id");
                String model = resultSet.getString("auto_model");
                String color = resultSet.getString("auto_color");
                Auto auto = new Auto(autoId, model, color, id);
                autoList.add(auto);
            }
            return autoList;

        } catch (SQLException e) {
            throw new IllegalAccessError("AutoList do not created!");
        }
     }
}


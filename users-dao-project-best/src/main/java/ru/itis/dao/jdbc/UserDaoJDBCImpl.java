package ru.itis.dao.jdbc;


import ru.itis.Auto;
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
            String userName = resultSet.getString("name");
            int userAge = resultSet.getInt("age");
            User user = new User(userId, userName, userAge);
            return user;

        } catch (SQLException e) {
            throw new IllegalAccessError("User with id="+id+" not found!");
        }
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO group_user(id, name, age) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("User not saved!");
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE group_user SET name='" +
                    user.getName()+"', age="+user.getAge()+" WHERE id="+user.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("User not updated!");
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("WITH t AS (DELETE FROM group_user WHERE id="+id+") "+
                    "DELETE FROM auto WHERE user_id="+id);
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
                String userName = resultSet.getString("name");
                int userAge = resultSet.getInt("age");
                User user = new User(userId, userName, userAge);
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            throw new IllegalAccessError("UserList not ctreated!");
        }
    }

    @Override
    public List<Auto> findAllUsersAuto(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT id,model,color FROM auto WHERE user_id="+id);
            List<Auto> autoList = new ArrayList<>();
            while (resultSet.next()) {
                int autoId = resultSet.getInt("id");
                String model = resultSet.getString("model");
                String color = resultSet.getString("color");
                Auto auto = new Auto(autoId, model, color, id);
                autoList.add(auto);
            }
            return autoList;

        } catch (SQLException e) {
            throw new IllegalAccessError("AutoList not ctreated!");
        }
     }
}


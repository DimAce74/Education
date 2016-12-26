package ru.itis.dao.jdbc;

import ru.itis.Auto;
import ru.itis.dao.AutoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AutoDaoJDBCImpl implements AutoDao {
    private Connection connection;

    public AutoDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auto find(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM auto WHERE id="+id);

            resultSet.next();
            int autoId = resultSet.getInt("id");
            String model = resultSet.getString("model");
            String color = resultSet.getString("color");
            int userId = resultSet.getInt("user_id");

            Auto auto = new Auto(autoId, model, color, userId);
            return auto;

        } catch (SQLException e) {
            throw new IllegalAccessError("Auto with id="+id+" not found!");
        }
    }

    @Override
    public boolean save(Auto auto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO auto(id, model, color, user_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, auto.getId());
            preparedStatement.setString(2, auto.getModel());
            preparedStatement.setString(3, auto.getColor());
            preparedStatement.setInt(4, auto.getUserId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("Auto not saved!");
        }
        return true;
    }

    @Override
    public boolean update(Auto auto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE auto SET model='" +
                    auto.getModel()+"', color='"+auto.getColor()+"', user_id="+auto.getUserId()+" WHERE id="+auto.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("Auto not updated!");
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM auto WHERE id="+id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalAccessError("Auto not deleted!");
        }
        return true;
    }

    @Override
    public List<Auto> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM auto");
            List<Auto> autoList = new ArrayList<>();
            while (resultSet.next()) {
                int autoId = resultSet.getInt("id");
                String model = resultSet.getString("model");
                String color = resultSet.getString("color");
                int userId = resultSet.getInt("user_id");
                Auto auto = new Auto(autoId, model, color, userId);
                autoList.add(auto);
            }
            return autoList;

        } catch (SQLException e) {
            throw new IllegalAccessError("AutoList not ctreated!");
        }
    }
}

package ru.itis.dao.jdbc;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.Auto;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoJDBCImpl implements UsersDao {

    // language=SQL
    private static final String SQL_SELECT_USER_BY_ID ="SELECT * FROM group_user WHERE id = ?";
    // language=SQL
    private static final String SQL_SELECT_AUTO_BY_USER_ID ="SELECT auto_id, auto_model, auto_color FROM auto WHERE user_id = ?";
    // language=SQL
    private static final String SQL_INSERT_NEW_USER_BY_NAME_AND_AGE ="INSERT INTO group_user(user_name, user_age) VALUES (?, ?)";
    // language=SQL
    private static final String SQL_UPDATE_USER ="UPDATE group_user SET user_name=?, user_age=?  WHERE id=?";
    // language=SQL
    private static final String SQL_FIND_ALL_USERS ="SELECT * FROM group_user";
    // language=SQL
    private static final String SQL_DELETE_USER_BY_ID ="DELETE FROM group_user WHERE id=?";
    // language=SQL
    private static final String SQL_FIND_ALL_AUTO ="SELECT * FROM auto";

    private JdbcTemplate template;

    public UserDaoJDBCImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getInt("user_age"), new ArrayList<>());
        }
    };
    private RowMapper<Auto> autoRowMapper = new RowMapper<Auto>() {
        public Auto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Auto(resultSet.getInt("auto_id"), resultSet.getString("auto_model"), resultSet.getString("auto_color"));
        }
    };
    private RowMapper<AutoInternal> autoInternalRowMapper = new RowMapper<AutoInternal>() {
        public AutoInternal mapRow(ResultSet resultSet, int i) throws SQLException {
            return new AutoInternal(resultSet.getInt("auto_id"), resultSet.getString("auto_model"), resultSet.getString("auto_color"), resultSet.getInt("user_id"));
        }
    };


    @Override
    public User find(int id) {
            User user;

            try {
                user = template.queryForObject(SQL_SELECT_USER_BY_ID,
                        new Object[]{id}, userRowMapper);
            } catch (EmptyResultDataAccessException e) {
                throw new UserNotFoundException();
            }
            List<Auto> autoList= template.query(SQL_SELECT_AUTO_BY_USER_ID,
                    new Object[]{id}, autoRowMapper);
            user.setListAuto(autoList);
            for (Auto auto :user.getListAuto()){
                auto.setUser(user);
            }
            return user;
    }

    @Override
    public boolean save(User user) {
        Object[] params = new Object[] {user.getName(), user.getAge()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR};
        int rows = template.update(SQL_INSERT_NEW_USER_BY_NAME_AND_AGE, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) {

        Object[] params = new Object[] {user.getName(), user.getAge(), user.getId()};
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.BIGINT};
        int rows = template.update(SQL_UPDATE_USER, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.BIGINT};
        int rows = template.update(SQL_DELETE_USER_BY_ID, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = template.query(SQL_FIND_ALL_USERS, userRowMapper);
        Map<Integer, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }
        List<AutoInternal> autoIntList = template.query(SQL_FIND_ALL_AUTO, autoInternalRowMapper);
        for (AutoInternal autoInt : autoIntList){
            autoInt.setUser(userMap.get(autoInt.getUserId()));
            autoInt.getUser().addAuto(autoInt.getAutoFromAutoInt());
        }
        return userList;
    }

    class AutoInternal extends Auto {
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public AutoInternal(int id, String model, String color, int userId) {
            super(id, model, color);
            this.userId = userId;
        }
        Auto getAutoFromAutoInt() {
            return new Auto(this.getId(), this.getModel(), this.getColor());
        }

    }
}


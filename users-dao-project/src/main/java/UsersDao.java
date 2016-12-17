import java.util.List;


public interface UsersDao {
    User find(int id);
    boolean save(User user);
    boolean update(User user);
    boolean delete(int id);
    List<User> findAll();
    List<Auto> findAllUsersAuto(int id);
}

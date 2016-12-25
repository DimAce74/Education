import java.io.File;
import java.util.List;


public interface UsersDao {
    User find(int id);
    boolean save(User user);
    boolean update(User user);
    boolean delete(int id);
    File getUserFile();
    File getAutoFile();
    List<User> findAll();
    List<Auto> findAllUsersAuto(int id);
}

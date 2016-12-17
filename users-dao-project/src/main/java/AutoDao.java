import java.util.List;

public interface AutoDao {
    Auto find(int id);
    boolean save(Auto auto);
    boolean update(Auto auto);
    boolean delete(int id);
    List<Auto> findAll();
    User findUserFromId(int id);
}

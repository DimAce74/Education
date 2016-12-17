import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class UsersDaoFileBasedImpl implements UsersDao {

    static final File USERS_FILE = new File ("users.txt");


    @Override
    public User find(int id) {
        List<User> userList = ReadWriteFiles.readUserFile();
        User user1 = null;
        for (User user : userList) {
            if (user.getId() == id) {
                user1 =  user;
            }
        }
        return user1;
    }

    @Override
    public boolean save(User user) {
        List<User> userList = ReadWriteFiles.readUserFile();
        userList.add(user);
        ReadWriteFiles.writeUsersFile(userList);
        return true;
    }

    @Override
    public boolean update(User user) {
        User userToUpdate = null;
        List<User> userList = findAll();
        for (User user1 : userList) {
            if (user1.getId() == user.getId()) {
                userToUpdate = user1;
            }
        }
        userList.remove(userToUpdate);
        userList.add(user);
        ReadWriteFiles.writeUsersFile(userList);
        return true;
    }

    @Override
    public boolean delete(int id) {
        List<User> userList = findAll();
        User user = null;
        for (User user1 : userList) {
            if (user1.getId() == id) {
                user = user1;
            }
        }
        userList.remove(user);

        ReadWriteFiles.writeUsersFile(userList);
        return true;
    }

    @Override
    public List<User> findAll() {

        return ReadWriteFiles.readUserFile();
    }

    @Override
    public List<Auto> findAllUsersAuto(int id) {
        List<Auto> usersAuto = new ArrayList<>();
        AutoDao autoDao = new AutoDaoFileBasedImpl();
        List<Auto> autos = autoDao.findAll();
        for (Auto auto : autos) {
            if (auto.getUser().getId() == id) {
                usersAuto.add(auto);
            }
        }
        return usersAuto;
    }
}

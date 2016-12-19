import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFiles {

    private static final String SEPARATOR = "\t";

    public static List<User> readUserFile () {
        List<User> userList = new ArrayList<>();
        String userAsString;
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersDaoFileBasedImpl.USERS_FILE))){
                while ((userAsString=reader.readLine())!=null) {
                String[] userParams = userAsString.split(SEPARATOR);
                User user = new User (userParams[1], Integer.parseInt(userParams[2]));
                user.setId(Integer.parseInt(userParams[0]));
                userList.add(user);
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return userList;
    }

    //Auto(int id, String model, String color, User user)
    public static List<Auto> readAutoFile () {
        List<Auto> autoList = new ArrayList<>();
        String autoAsString;
        try (BufferedReader reader = new BufferedReader(new FileReader(AutoDaoFileBasedImpl.AUTO_FILE))){
                while ((autoAsString=reader.readLine())!=null) {
                String[] autoParams = autoAsString.split(SEPARATOR);
                Auto auto = new Auto (autoParams[1], autoParams[2],Integer.parseInt(autoParams[3]));
                    auto.setId(Integer.parseInt(autoParams[0]));
                autoList.add(auto);
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return autoList;

    }

    public static void writeUsersFile(List<User> userList){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UsersDaoFileBasedImpl.USERS_FILE))){
            for (User user : userList) {
                String userAsString = user.getId()+SEPARATOR + user.getName() + SEPARATOR + user.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
            } catch (Exception e) {
                throw new IllegalAccessError();
        }
    }
    public static void writeAutosFile(List<Auto> autos){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AutoDaoFileBasedImpl.AUTO_FILE))){
            for (Auto auto : autos) {
                String autoAsString = auto.getId() + SEPARATOR + auto.getModel() +SEPARATOR+ auto.getColor() + SEPARATOR+ auto.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
            } catch (Exception e) {
                throw new IllegalAccessError();
        }
    }



/** Test Main
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Auto> autos = new ArrayList<>();
        users = readUserFile(new File ("users.txt"));
        autos = readAutoFile(new File("auto.txt"));
        for (User user : users) {
            System.out.println(user.getId() + user.getName() + user.getAge());
        }
        for (Auto auto : autos) {
            System.out.println(auto.getId() + auto.getModel() + auto.getColor() + auto.getUser().getId());
        }

        users.add(new User(6, "Kostya", 30));
        writeUsersFile(users);
        autos.add(new Auto(6, "Nissan", "black", User.getUserFromId(users, 6)));
        writeAutosFile(autos);

    }
*/
}

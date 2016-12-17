import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Auto> listAuto;
    UsersDao usersDao = new UsersDaoFileBasedImpl();

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.listAuto = null;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setListAuto(List<Auto> listAuto) {
        this.listAuto = listAuto;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Auto> getListAuto() {

        return usersDao.findAllUsersAuto(id);
    }


}

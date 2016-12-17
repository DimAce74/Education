import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersDao usersDao = new UsersDaoFileBasedImpl();
        AutoDao autoDao = new AutoDaoFileBasedImpl();

        //System.out.println(usersDao.find(2).getName());

        //User user = new User (7, "Vika", 18);
        //usersDao.save(user);
        //for (User user1 : usersDao.findAll()) {
        //    System.out.println(user1.getName() + " ");
        //}
        User user = usersDao.find(4);
        //List<Auto> autoList = user.getListAuto();
        //for (Auto auto : autoList) {
        //    System.out.println(auto.getModel());
        //}
        //user.setAge(122);
        //usersDao.update(user);
        //usersDao.delete(3);
        //for (User user1 : usersDao.findAll()) {
        //    System.out.print(user1.getName() + " ");
        //}
        Auto auto = autoDao.find(3);
        //System.out.println(auto.getModel());

        //Auto auto1 = new Auto (0, "Kia", "red", user);
        //autoDao.save(auto1);
        //System.out.println(autoDao.findAll().get(0).getModel());
        //System.out.println(auto.getUser().getName());
        //auto.setUser(user);
        //autoDao.update(auto);
        //System.out.println(auto.getUser().getName());


    }
}

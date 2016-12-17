
public class Auto {
    private int id;
    private String model;
    private String color;
    private User user;

    public Auto(int id, String model, String color, User user) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

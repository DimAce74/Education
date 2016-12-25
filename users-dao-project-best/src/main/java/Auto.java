import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auto {
    private int id;
    private String model;
    private String color;
    private int userId;

    public Auto(int id, String model, String color, int userId) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.userId = userId;
    }

    public Auto(String model, String color, int userId) {
        this.model = model;

        this.color = color;
        this.userId = userId;
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


    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Auto> listAuto;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    void setId (int id) {
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

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Auto> getListAuto() {
        return listAuto;
    }


}

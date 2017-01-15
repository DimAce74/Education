package ru.itis.models;

import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Auto> listAuto;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(int id, String name, int age, List<Auto> listAuto) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.listAuto = listAuto;
    }

    private void setId (int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void setListAuto(List<Auto> listAuto) {
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

    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() == obj.getClass()) {
            User user = (User) obj;
            if (this.getId() == user.getId() && (this.getName().equals(user.getName()) && this.getAge() == (user.getAge()) && (this.getListAuto().equals(user.getListAuto())))) {
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        int hash=1;
        hash = hash * 31 + Integer.valueOf(id).hashCode();
        hash = hash * 31 + (name == null ? 0 : name.hashCode());
        hash = hash * 31 + (age == 0 ? 0 : Integer.valueOf(age).hashCode());
        return hash;
    }

}

package ru.itis.models;

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

    public Auto(String model, String color, User user) {
        this.model = model;
        this.color = color;
        this.user = user;
    }

    public Auto(int id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    public Auto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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


    private void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() == obj.getClass()) {
            Auto auto = (Auto) obj;
            if (this.getId() == auto.getId() && (this.getModel().equals(auto.getModel())) &&
                    (this.getColor().equals(auto.getColor())) && this.getUser().equals(auto.getUser())) {
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        int hash=1;
        hash = hash * 31 + Integer.valueOf(id).hashCode();
        hash = hash * 31 + (model == null ? 0 : model.hashCode());
        hash = hash * 31 + (color == null ? 0 : color.hashCode());
        hash = hash * 31 + user.hashCode();
        return hash;
    }
}

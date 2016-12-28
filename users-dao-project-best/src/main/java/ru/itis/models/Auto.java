package ru.itis.models;
//TODO: сделать Builder

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

    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() == obj.getClass()) {
            Auto auto = (Auto) obj;
            if (this.getId() == auto.getId() && (this.getModel().equals(auto.getModel())) && (this.getColor().equals(auto.getColor())) && this.getUserId() == (auto.getUserId())) {
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
        hash = hash * 31 + (userId == 0 ? 0 : Integer.valueOf(userId).hashCode());
        return hash;
    }
}

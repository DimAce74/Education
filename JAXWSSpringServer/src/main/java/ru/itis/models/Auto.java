package ru.itis.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auto")
public class Auto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private int id;

    @Column(name = "auto_model")
    private String model;

    @Column(name = "auto_color")
    private String color;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    public Auto() {
    }

    public Auto(Builder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.color = builder.color;
        this.user = builder.user;
    }


    public static class Builder {
        private int id;
        private String model;
        private String color;
        private User user;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Auto build() {
            return new Auto(this);
        }
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
}

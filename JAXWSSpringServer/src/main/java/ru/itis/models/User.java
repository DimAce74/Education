package ru.itis.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "group_user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_age")
    private int age;

    @OneToMany
    @JoinTable(name = "auto",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Auto> listAuto;

    public User() {
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.listAuto = builder.listAuto;
    }


    public static class Builder {
        private int id;
        private String name;
        private int age;
        private List<Auto> listAuto;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder listAuto(List<Auto> listAuto) {
            this.listAuto = listAuto;
            return this;
        }

        public User build() {
            return new User(this);
        }
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

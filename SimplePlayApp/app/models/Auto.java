package models;

import javax.persistence.*;

/**
 * Created by db2admin on 02.05.2017.
 */
@Entity
@Table(name = "autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private int id;

    @Column(name = "model")
    private String model;

    @ManyToOne(targetEntity = User.class)
    private User user;


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

    private void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() == obj.getClass()) {
            Auto auto = (Auto) obj;
            if (this.getId() == auto.getId() && (this.getModel().equals(auto.getModel())) &&
                    (this.getUser().equals(auto.getUser()))) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + Integer.valueOf(id).hashCode();
        hash = hash * 31 + (model == null ? 0 : model.hashCode());
        hash = hash * 31 + user.hashCode();
        return hash;
    }
}

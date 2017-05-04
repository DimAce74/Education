package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by db2admin on 02.05.2017.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Auto> listAuto;

    public User() {
    }

    private void setId (int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Auto> getListAuto() {
        return listAuto;
    }

    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() == obj.getClass()) {
            User user = (User) obj;
            if (this.getId() == user.getId() && (this.getName().equals(user.getName()) && (this.getListAuto().equals(user.getListAuto())))) {
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        int hash=1;
        hash = hash * 31 + Integer.valueOf(id).hashCode();
        hash = hash * 31 + (name == null ? 0 : name.hashCode());
        return hash;
    }

}

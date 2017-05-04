package models;

import be.objectify.deadbolt.java.models.Role;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by db2admin on 04.05.2017.
 */
@Entity
public class SecurityRole extends Model implements Role {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    public String roleName;

    public static final Find<Long, SecurityRole> find = new Find<Long, SecurityRole>(){};

    @Override
    public String getName() {
        return roleName;
    }

    public static SecurityRole findByRoleName(String roleName) {
        return find.where().eq("roleName", roleName).findUnique();
    }
}

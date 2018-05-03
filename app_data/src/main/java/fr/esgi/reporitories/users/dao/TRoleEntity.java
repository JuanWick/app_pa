package fr.esgi.reporitories.users.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_role", schema = "pa_data", catalog = "pa_data")
public class TRoleEntity {
    private int id;
    private String name;
    private Collection<RUserRoleEntity> rUserRolesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoleEntity that = (TRoleEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "tRoleByRoleId")
    public Collection<RUserRoleEntity> getrUserRolesById() {
        return rUserRolesById;
    }

    public void setrUserRolesById(Collection<RUserRoleEntity> rUserRolesById) {
        this.rUserRolesById = rUserRolesById;
    }
}

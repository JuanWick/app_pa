package fr.esgi.reporitories.users.dao;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_role")
public class TRoleEntity {
    private int id;
    private String name;
    private Collection<TUserEntity> usersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "roles")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TUserEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<TUserEntity> usersById) {
        this.usersById = usersById;
    }
}

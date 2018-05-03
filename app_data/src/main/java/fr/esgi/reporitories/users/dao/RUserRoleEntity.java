package fr.esgi.reporitories.users.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_user_role", schema = "pa_data", catalog = "pa_data")
public class RUserRoleEntity {
    private int id;
    private TUserEntity tUserByUserId;
    private TRoleEntity tRoleByRoleId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RUserRoleEntity that = (RUserRoleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public TUserEntity gettUserByUserId() {
        return tUserByUserId;
    }

    public void settUserByUserId(TUserEntity tUserByUserId) {
        this.tUserByUserId = tUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public TRoleEntity gettRoleByRoleId() {
        return tRoleByRoleId;
    }

    public void settRoleByRoleId(TRoleEntity tRoleByRoleId) {
        this.tRoleByRoleId = tRoleByRoleId;
    }
}

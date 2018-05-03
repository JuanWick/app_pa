package fr.esgi.reporitories.carts.dao;

import fr.esgi.reporitories.users.dao.TUserEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_shared_cart", schema = "pa_data", catalog = "pa_data")
public class RSharedCartEntity {
    private int id;
    private TCartEntity tCartByCartId;
    private TUserEntity tUserByUserId;

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
        RSharedCartEntity that = (RSharedCartEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    public TCartEntity gettCartByCartId() {
        return tCartByCartId;
    }

    public void settCartByCartId(TCartEntity tCartByCartId) {
        this.tCartByCartId = tCartByCartId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public TUserEntity gettUserByUserId() {
        return tUserByUserId;
    }

    public void settUserByUserId(TUserEntity tUserByUserId) {
        this.tUserByUserId = tUserByUserId;
    }
}

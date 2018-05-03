package fr.esgi.reporitories.carts.dao;

import fr.esgi.reporitories.users.dao.TUserEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_cart", schema = "pa_data", catalog = "pa_data")
public class TCartEntity {
    private int id;
    private Collection<RCartProductEntity> rCartProductsById;
    private Collection<RSharedCartEntity> rSharedCartsById;
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
        TCartEntity that = (TCartEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @OneToMany(mappedBy = "tCartByCartId")
    public Collection<RCartProductEntity> getrCartProductsById() {
        return rCartProductsById;
    }

    public void setrCartProductsById(Collection<RCartProductEntity> rCartProductsById) {
        this.rCartProductsById = rCartProductsById;
    }

    @OneToMany(mappedBy = "tCartByCartId")
    public Collection<RSharedCartEntity> getrSharedCartsById() {
        return rSharedCartsById;
    }

    public void setrSharedCartsById(Collection<RSharedCartEntity> rSharedCartsById) {
        this.rSharedCartsById = rSharedCartsById;
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

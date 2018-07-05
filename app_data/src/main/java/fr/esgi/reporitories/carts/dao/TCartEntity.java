package fr.esgi.reporitories.carts.dao;

import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_cart")
public class TCartEntity implements Serializable {
    private int id;
    private Collection<TProductEntity> products;
    private Collection<TUserEntity> sharedUsers;
    private TUserEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "r_users_carts",
            joinColumns = @JoinColumn(name = "cart_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    public Collection<TUserEntity> getSharedUsers() {
        return sharedUsers;
    }

    public void setSharedUsers(Collection<TUserEntity> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public TUserEntity getUser() {
        return user;
    }

    public void setUser(TUserEntity user) {
        this.user = user;
    }

    @ManyToMany
    @JoinTable(name = "r_Products_Carts",
            joinColumns = @JoinColumn(name = "cart_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"))
    public Collection<TProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<TProductEntity> products) {
        this.products = products;
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
}

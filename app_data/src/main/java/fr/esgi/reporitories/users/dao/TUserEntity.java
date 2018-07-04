package fr.esgi.reporitories.users.dao;

import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_user")
public class TUserEntity {
    private int id;
    private String name;
    private String firstName;
    private Collection<TStoreEntity> loyaltyStores;
    private Collection<TCartEntity> sharedCarts;
    private Collection<TRoleEntity> roles;
    private Collection<TCartEntity> carts;
    private Collection<TStoreEntity> stores;

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

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserEntity that = (TUserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, firstName);
    }

    @ManyToMany
    @JoinTable(name = "r_users_stores",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "store_id",referencedColumnName = "id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TStoreEntity> getLoyaltyStores() {
        return loyaltyStores;
    }

    public void setLoyaltyStores(Collection<TStoreEntity> loyaltyStores) {
        this.loyaltyStores = loyaltyStores;
    }

    @ManyToMany(mappedBy="sharedUsers")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TCartEntity> getSharedCarts() {
        return sharedCarts;
    }

    public void setSharedCarts(Collection<TCartEntity> sharedCarts) {
        this.sharedCarts = sharedCarts;
    }

    @ManyToMany
    @JoinTable(name = "r_users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<TRoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "user")
    public Collection<TCartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Collection<TCartEntity> carts) {
        this.carts = carts;
    }

    @OneToMany(mappedBy = "user")
    public Collection<TStoreEntity> getStores() {
        return stores;
    }

    public void setStores(Collection<TStoreEntity> stores) {
        this.stores = stores;
    }
}

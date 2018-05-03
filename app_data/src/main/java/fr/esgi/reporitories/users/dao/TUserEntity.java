package fr.esgi.reporitories.users.dao;

import fr.esgi.reporitories.carts.dao.RSharedCartEntity;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.loyalties.dao.RLoyaltyCardEntity;
import fr.esgi.reporitories.stores.dao.TStoreEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_user", schema = "pa_data", catalog = "pa_data")
public class TUserEntity {
    private int id;
    private String name;
    private String firstname;
    private Collection<RLoyaltyCardEntity> rLoyaltyCardsById;
    private Collection<RSharedCartEntity> rSharedCartsById;
    private Collection<RUserRoleEntity> rUserRolesById;
    private Collection<TCartEntity> tCartsById;
    private Collection<TStoreEntity> tStoresById;

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

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserEntity that = (TUserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, firstname);
    }

    @OneToMany(mappedBy = "tUserByUserId")
    public Collection<RLoyaltyCardEntity> getrLoyaltyCardsById() {
        return rLoyaltyCardsById;
    }

    public void setrLoyaltyCardsById(Collection<RLoyaltyCardEntity> rLoyaltyCardsById) {
        this.rLoyaltyCardsById = rLoyaltyCardsById;
    }

    @OneToMany(mappedBy = "tUserByUserId")
    public Collection<RSharedCartEntity> getrSharedCartsById() {
        return rSharedCartsById;
    }

    public void setrSharedCartsById(Collection<RSharedCartEntity> rSharedCartsById) {
        this.rSharedCartsById = rSharedCartsById;
    }

    @OneToMany(mappedBy = "tUserByUserId")
    public Collection<RUserRoleEntity> getrUserRolesById() {
        return rUserRolesById;
    }

    public void setrUserRolesById(Collection<RUserRoleEntity> rUserRolesById) {
        this.rUserRolesById = rUserRolesById;
    }

    @OneToMany(mappedBy = "tUserByUserId")
    public Collection<TCartEntity> gettCartsById() {
        return tCartsById;
    }

    public void settCartsById(Collection<TCartEntity> tCartsById) {
        this.tCartsById = tCartsById;
    }

    @OneToMany(mappedBy = "tUserByUserId")
    public Collection<TStoreEntity> gettStoresById() {
        return tStoresById;
    }

    public void settStoresById(Collection<TStoreEntity> tStoresById) {
        this.tStoresById = tStoresById;
    }
}

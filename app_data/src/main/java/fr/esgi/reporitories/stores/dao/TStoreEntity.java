package fr.esgi.reporitories.stores.dao;

import fr.esgi.reporitories.loyalties.dao.RLoyaltyCardEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_store", schema = "pa_data", catalog = "pa_data")
public class TStoreEntity {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Collection<RLoyaltyCardEntity> rLoyaltyCardsById;
    private Collection<RStoreProductEntity> rStoreProductsById;
    private TUserEntity tUserByUserId;

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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TStoreEntity that = (TStoreEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, zipcode, city, country);
    }

    @OneToMany(mappedBy = "tStoreByStoreId")
    public Collection<RLoyaltyCardEntity> getrLoyaltyCardsById() {
        return rLoyaltyCardsById;
    }

    public void setrLoyaltyCardsById(Collection<RLoyaltyCardEntity> rLoyaltyCardsById) {
        this.rLoyaltyCardsById = rLoyaltyCardsById;
    }

    @OneToMany(mappedBy = "tStoreByStoreId")
    public Collection<RStoreProductEntity> getrStoreProductsById() {
        return rStoreProductsById;
    }

    public void setrStoreProductsById(Collection<RStoreProductEntity> rStoreProductsById) {
        this.rStoreProductsById = rStoreProductsById;
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

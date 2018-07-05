package fr.esgi.reporitories.stores.dao;

import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_store")
public class TStoreEntity {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private Collection<TUserEntity> loyaltyUsers;
    private Collection<TProductEntity> products;
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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    @ManyToMany(mappedBy = "loyaltyStores")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TUserEntity> getLoyaltyUsers() {
        return loyaltyUsers;
    }

    public void setLoyaltyUsers(Collection<TUserEntity> loyaltyUsers) {
        this.loyaltyUsers = loyaltyUsers;
    }

    @ManyToMany
    @JoinTable(name = "r_stores_products",
            joinColumns = @JoinColumn(name = "store_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "product_id"))
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<TProductEntity> products) {
        this.products = products;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public TUserEntity getUser() {
        return user;
    }

    public void setUser(TUserEntity user) {
        this.user = user;
    }
}

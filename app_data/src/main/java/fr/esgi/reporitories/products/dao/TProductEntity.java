package fr.esgi.reporitories.products.dao;

import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_product")
public class TProductEntity implements Serializable {
    private int productId;
    private String name;
    private String info;
    private String barreCode;
    private Double price;
    private Collection<TCartEntity> carts;
    private Collection<TCategoryEntity> categories;
    private Collection<TStoreEntity> stores;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "barre_code")
    public String getBarreCode() {
        return barreCode;
    }

    public void setBarreCode(String barreCode) {
        this.barreCode = barreCode;
    }

    @ManyToMany(mappedBy = "products")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Collection<TCartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Collection<TCartEntity> carts) {
        this.carts = carts;
    }

    @ManyToMany
    @JoinTable(name = "r_products_categories",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    public Collection<TCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Collection<TCategoryEntity> categories) {
        this.categories = categories;
    }

    @ManyToMany(mappedBy = "products")
    public Collection<TStoreEntity> getStores() {
        return stores;
    }

    public void setStores(Collection<TStoreEntity> stores) {
        this.stores = stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TProductEntity that = (TProductEntity) o;
        return productId == that.productId &&
                Objects.equals(name, that.name) &&
                Objects.equals(info, that.info) &&
                Objects.equals(barreCode, that.barreCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, name, info, barreCode);
    }
}

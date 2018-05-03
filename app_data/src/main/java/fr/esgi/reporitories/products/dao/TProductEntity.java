package fr.esgi.reporitories.products.dao;

import fr.esgi.reporitories.stores.dao.RStoreProductEntity;
import fr.esgi.reporitories.carts.dao.RCartProductEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_product", schema = "pa_data", catalog = "pa_data")
public class TProductEntity {
    private int id;
    private String name;
    private String info;
    private String barreCode;
    private Collection<RCartProductEntity> rCartProductsById;
    private Collection<RProductCategoryEntity> rProductCategoriesById;
    private Collection<RStoreProductEntity> rStoreProductsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TProductEntity that = (TProductEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(info, that.info) &&
                Objects.equals(barreCode, that.barreCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, info, barreCode);
    }

    @OneToMany(mappedBy = "tProductByProductId")
    public Collection<RCartProductEntity> getrCartProductsById() {
        return rCartProductsById;
    }

    public void setrCartProductsById(Collection<RCartProductEntity> rCartProductsById) {
        this.rCartProductsById = rCartProductsById;
    }

    @OneToMany(mappedBy = "tProductByProductId")
    public Collection<RProductCategoryEntity> getrProductCategoriesById() {
        return rProductCategoriesById;
    }

    public void setrProductCategoriesById(Collection<RProductCategoryEntity> rProductCategoriesById) {
        this.rProductCategoriesById = rProductCategoriesById;
    }

    @OneToMany(mappedBy = "tProductByProductId")
    public Collection<RStoreProductEntity> getrStoreProductsById() {
        return rStoreProductsById;
    }

    public void setrStoreProductsById(Collection<RStoreProductEntity> rStoreProductsById) {
        this.rStoreProductsById = rStoreProductsById;
    }
}

package fr.esgi.reporitories.products.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "t_category", schema = "pa_data", catalog = "pa_data")
public class TCategoryEntity {
    private int id;
    private String name;
    private Collection<RProductCategoryEntity> rProductCategoriesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCategoryEntity that = (TCategoryEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "tCategoryByCategoryId")
    public Collection<RProductCategoryEntity> getrProductCategoriesById() {
        return rProductCategoriesById;
    }

    public void setrProductCategoriesById(Collection<RProductCategoryEntity> rProductCategoriesById) {
        this.rProductCategoriesById = rProductCategoriesById;
    }
}
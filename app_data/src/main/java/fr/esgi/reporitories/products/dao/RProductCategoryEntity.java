package fr.esgi.reporitories.products.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_product_category", schema = "pa_data", catalog = "pa_data")
public class RProductCategoryEntity {
    private int id;
    private TProductEntity tProductByProductId;
    private TCategoryEntity tCategoryByCategoryId;

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
        RProductCategoryEntity that = (RProductCategoryEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public TProductEntity gettProductByProductId() {
        return tProductByProductId;
    }

    public void settProductByProductId(TProductEntity tProductByProductId) {
        this.tProductByProductId = tProductByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public TCategoryEntity gettCategoryByCategoryId() {
        return tCategoryByCategoryId;
    }

    public void settCategoryByCategoryId(TCategoryEntity tCategoryByCategoryId) {
        this.tCategoryByCategoryId = tCategoryByCategoryId;
    }
}

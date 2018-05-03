package fr.esgi.reporitories.stores.dao;

import fr.esgi.reporitories.products.dao.TProductEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_store_product", schema = "pa_data", catalog = "pa_data")
public class RStoreProductEntity {
    private int id;
    private TStoreEntity tStoreByStoreId;
    private TProductEntity tProductByProductId;

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
        RStoreProductEntity that = (RStoreProductEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
    public TStoreEntity gettStoreByStoreId() {
        return tStoreByStoreId;
    }

    public void settStoreByStoreId(TStoreEntity tStoreByStoreId) {
        this.tStoreByStoreId = tStoreByStoreId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public TProductEntity gettProductByProductId() {
        return tProductByProductId;
    }

    public void settProductByProductId(TProductEntity tProductByProductId) {
        this.tProductByProductId = tProductByProductId;
    }
}

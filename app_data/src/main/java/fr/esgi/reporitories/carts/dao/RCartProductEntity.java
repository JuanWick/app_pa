package fr.esgi.reporitories.carts.dao;

import fr.esgi.reporitories.products.dao.TProductEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_cart_product", schema = "pa_data", catalog = "pa_data")
public class RCartProductEntity {
    private int id;
    private TCartEntity tCartByCartId;
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
        RCartProductEntity that = (RCartProductEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    public TCartEntity gettCartByCartId() {
        return tCartByCartId;
    }

    public void settCartByCartId(TCartEntity tCartByCartId) {
        this.tCartByCartId = tCartByCartId;
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

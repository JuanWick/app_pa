package fr.esgi.reporitories.loyalties.dao;

import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_loyalty_card", schema = "pa_data", catalog = "pa_data")
public class RLoyaltyCardEntity {
    private int id;
    private TStoreEntity tStoreByStoreId;
    private TUserEntity tUserByUserId;

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
        RLoyaltyCardEntity that = (RLoyaltyCardEntity) o;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public TUserEntity gettUserByUserId() {
        return tUserByUserId;
    }

    public void settUserByUserId(TUserEntity tUserByUserId) {
        this.tUserByUserId = tUserByUserId;
    }
}

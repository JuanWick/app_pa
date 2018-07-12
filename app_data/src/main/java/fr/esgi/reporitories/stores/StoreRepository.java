package fr.esgi.reporitories.stores;

import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<TStoreEntity, Integer> {
    List<TStoreEntity> getByUser_Id(Integer userId);
}

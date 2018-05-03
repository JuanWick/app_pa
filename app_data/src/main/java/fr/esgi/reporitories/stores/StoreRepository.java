package fr.esgi.reporitories.stores;

import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<TStoreEntity, Integer> {}

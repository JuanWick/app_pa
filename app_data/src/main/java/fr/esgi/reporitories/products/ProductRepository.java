package fr.esgi.reporitories.products;

import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<TProductEntity, Integer> {
}

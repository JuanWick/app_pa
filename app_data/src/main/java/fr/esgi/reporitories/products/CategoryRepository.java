package fr.esgi.reporitories.products;

import fr.esgi.reporitories.products.dao.TCategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository  extends CrudRepository<TCategoryEntity, Integer> {
    TCategoryEntity getByName(String name);
}

package fr.esgi.reporitories.carts;

import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<TCartEntity, Integer> {
}

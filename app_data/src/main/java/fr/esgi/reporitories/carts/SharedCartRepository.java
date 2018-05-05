package fr.esgi.reporitories.carts;

import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface SharedCartRepository extends CrudRepository<TUserEntity, Integer> {
}

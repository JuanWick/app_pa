package fr.esgi.reporitories.carts;

import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<TCartEntity, Integer> {
    List<TCartEntity> getAllByUser_Id(Integer user_id);
}

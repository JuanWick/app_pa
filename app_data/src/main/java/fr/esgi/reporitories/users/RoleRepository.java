package fr.esgi.reporitories.users;

import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<TRoleEntity, Integer> {
}

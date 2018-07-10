package fr.esgi.reporitories.users;

import fr.esgi.reporitories.products.services.IStoreProjection;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserAuthenticator;
import fr.esgi.reporitories.users.services.IRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAuthenticatorRepository extends CrudRepository<TUserAuthenticator, Integer> {
    TUserAuthenticator findByEmail(String email);
    TUserAuthenticator findByLogin(String login);
    TUserAuthenticator findByUser_Id(Integer id);

    @Query(
            value = "SELECT DISTINCT r.* FROM pa_data.t_role r\n" +
                    "INNER JOIN pa_data.r_users_roles rur ON rur.role_id = r.id\n" +
                    "INNER JOIN pa_data.t_user_authenticator tua ON tua.user_id = rur.user_id\n" +
                    "WHERE tua.id = :param",nativeQuery = true)
    List<IRole> getRolesByUserAuthenticator(@Param("param") Integer param);
}
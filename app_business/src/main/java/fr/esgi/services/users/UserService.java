package fr.esgi.services.users;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {
    Integer create(UserData userData, User user, Integer roleid);
    Object[] getById(UserData userData, UserAuthenticatorData userAuthenticatorData, int id) throws UserNotFoundException;
    User save(UserData userData, User user);
    List<Role> getRoles(UserData userData);
    Role getRoleById(UserData userData, int id) throws RoleNotFoundException;
    void delete(UserData userData, int id);
}

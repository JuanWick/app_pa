package fr.esgi.services.users;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.exception.RoleNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public Integer create(UserData userData, User user, Integer roleId) {
        Role role = userData.getRoleById(roleId);
        user.getRoles().add(role);
        user = userData.save(user);

        return user.getId();
    }

    @Override
    public Object[] getById(UserData userData, UserAuthenticatorData userAuthenticatorData, int id) throws UserNotFoundException {
        Object[] result = new Object[2];

        User user = userData.getById(id);
        UserAuthenticator userAuthenticator = userAuthenticatorData.findByUserId(id);

        result[0] = user;
        result[1] = userAuthenticator;
        if(null != user && null != userAuthenticator){
            return  result;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User save(UserData userData, User user) {
        user.setName(user.getName().toUpperCase());
        return userData.save(user);
    }

    @Override
    public List<Role> getRoles(UserData userData) {
        return userData.getRoles();
    }

    @Override
    public Role getRoleById(UserData userData, int id) throws RoleNotFoundException {
        Role role = userData.getRoleById(id);
        if(null == role){throw new RoleNotFoundException();
        }
        return role;
    }

    @Override
    public void delete(UserData userData, int id) {
        userData.delete(id);
    }
}

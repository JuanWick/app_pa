package fr.esgi.services.users;

import entities.Role;
import entities.User;
import fr.esgi.reporitories.users.services.UserData;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public Integer create(UserData userData, User user, Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        user.getRoles().add(role);
        user = userData.save(user);

        return user.getId();
    }

    @Override
    public User getById(UserData userData, int id) {
        return userData.getById(id);
    }

    @Override
    public User save(UserData userData, User user) {
        return userData.save(user);
    }

    @Override
    public List<Role> getRoles(UserData userData) {
        return userData.getRoles();
    }

    @Override
    public void delete(UserData userData, int id) {
        userData.delete(id);
    }
}

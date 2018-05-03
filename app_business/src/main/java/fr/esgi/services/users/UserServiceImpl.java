package fr.esgi.services.users;

import entities.User;
import fr.esgi.reporitories.users.services.UserData;

public class UserServiceImpl implements UserService {

    @Override
    public User getById(UserData userData, int id) {
        return userData.getById(id);
    }

    @Override
    public User save(UserData userData, User user) {
        return userData.save(user);
    }
}

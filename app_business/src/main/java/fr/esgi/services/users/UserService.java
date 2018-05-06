package fr.esgi.services.users;

import entities.Role;
import entities.Store;
import entities.User;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;

import java.util.List;

public interface UserService {
    Integer create(UserData userData, User user, Integer roleid);
    User getById(UserData userData, int id);
    User save(UserData userData, User user);
    List<Role> getRoles(UserData userData);
}

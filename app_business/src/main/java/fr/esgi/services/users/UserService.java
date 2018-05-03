package fr.esgi.services.users;

import entities.Store;
import entities.User;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;

public interface UserService {
    User getById(UserData userData, int id);
    User save(UserData userData, User user);
}

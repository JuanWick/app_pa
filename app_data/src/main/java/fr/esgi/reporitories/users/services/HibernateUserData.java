package fr.esgi.reporitories.users.services;

import entities.User;
import fr.esgi.reporitories.stores.StoreRepository;
import fr.esgi.reporitories.stores.adapter.StoreAdapter;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateUserData implements UserData {

    @Autowired
    UserRepository userRepository;

    UserAdapter userAdapter = new UserAdapter();

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return userAdapter.adapt(userRepository.findById(id).get());
    }

    @Override
    public User save(User user) {
        TUserEntity userEntity = userRepository.save(userAdapter.convert(user));
        return userAdapter.adapt(userEntity);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
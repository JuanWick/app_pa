package fr.esgi.reporitories.users.services;

import entities.User;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateUserData implements UserData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAdapter userAdapter;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        User user = null;
        if(userRepository.findById(id).isPresent()){
            TUserEntity  tUserEntity = userRepository.findById(id).get();
            user = userAdapter.entityToModel(tUserEntity);
        }
        return user;
    }

    @Override
    public User save(User user) {
        TUserEntity userEntity = userRepository.save(userAdapter.modelToEntity(user));
        return userAdapter.entityToModel(userEntity);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

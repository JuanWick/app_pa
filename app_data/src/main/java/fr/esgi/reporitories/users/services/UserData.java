package fr.esgi.reporitories.users.services;

import entities.User;

import java.util.List;

public interface UserData {
    List<User> getAll();
    User getById(int id);
    User save(User user);
    User update(User user);
    void delete(int id);
}

package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.User;

import java.util.List;

public interface UserData {
    Integer create(User user);
    User getById(int id);
    User save(User user);
    List<Role> getRoles();
    User update(User user);
    Role getRoleById(int id);
    void delete(int id);
}

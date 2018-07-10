package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class InMemotyUserData implements UserData{

    private List<User> users;

    public InMemotyUserData() {
        users = new ArrayList<>();
    }

    @Override
    public Integer create(User user) {
        return null;
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Role getRoleById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

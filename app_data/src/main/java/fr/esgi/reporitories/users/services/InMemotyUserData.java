package fr.esgi.reporitories.users.services;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class InMemotyUserData implements UserData{

    private List<User> users;

    public InMemotyUserData() {
        users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Bob");
        user1.setName("EPONGE");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Bart");
        user2.setName("SIMPSON");

        users.add(user1);
        users.add(user2);
    }

    @Override
    public List<User> getAll() {
        return users;
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
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

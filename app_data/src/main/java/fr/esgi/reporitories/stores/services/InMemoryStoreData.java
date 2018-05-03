package fr.esgi.reporitories.stores.services;

import entities.Store;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreData implements StoreData {
    private final Store store3;
    private final User user1;
    private final User user2;
    private final Store store1;
    private final Store store2;

    public InMemoryStoreData(){
        user1 = new User();
        user1.setFirstName("Bob");
        user1.setName("L'éponge");
        user1.setId(1);

        user2 = new User();
        user2.setFirstName("Benjamin");
        user2.setName("Button");
        user2.setId(2);

        store1 = new Store();
        store1.setName("Store 1");
        store1.setAddress("23 avenue de la république");
        store1.setZipcode("94700");
        store1.setCity("Créteil");
        store1.setCountry("France");
        store1.setUser(user1);
        store1.setId(1);

        store2 = new Store();
        store2.setName("Store 2");
        store2.setAddress("9 rue Erard");
        store2.setZipcode("75012");
        store2.setCity("Paris");
        store2.setCountry("France");
        store2.setUser(user1);
        store2.setId(2);

        store3 = new Store();
        store3.setName("Store 3");
        store3.setAddress("21 rue Erard");
        store3.setZipcode("75012");
        store3.setCity("Paris");
        store3.setCountry("France");
        store3.setUser(user2);
        store3.setId(3);
    }

    @Override
    public List<Store> getAll() {
        List<Store> result = new ArrayList<>();
        result.add(store1);
        result.add(store2);
        result.add(store3);
        return result;
    }

    @Override
    public Store getById(int id) {
        if(id == 1){
            return store1;
        } else if(id == 2){
            return store2;
        } else if(id == 3){
            return store3;
        } else {
            return null;
        }
    }

    @Override
    public Store save(Store place) {
        return place;
    }

    @Override
    public Store update(Store place) {
        return place;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Store getByShortName(String shortName) {
        return null;
    }
}

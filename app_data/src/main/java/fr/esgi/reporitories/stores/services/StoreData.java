package fr.esgi.reporitories.stores.services;

import entities.Store;

import java.util.List;

public interface StoreData {
    List<Store> getAll();
    Store getById(int id);
    Store save(Store place);
    Store update(Store place);
    void delete(int id);
    Store getByShortName(String shortName);
}

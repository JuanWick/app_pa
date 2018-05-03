package fr.esgi.services.stores;

import entities.Store;
import fr.esgi.reporitories.stores.services.StoreData;

import java.util.List;

public interface StoreService {
    public List<Store> getAll(StoreData storeData);
    public Store getById(StoreData storeData, int id);
}

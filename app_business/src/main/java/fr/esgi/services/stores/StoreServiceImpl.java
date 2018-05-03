package fr.esgi.services.stores;

import entities.Store;
import fr.esgi.reporitories.stores.services.StoreData;
import lombok.Data;

import java.util.List;

@Data
public class StoreServiceImpl implements StoreService {

    @Override
    public List<Store> getAll(StoreData storeData) {
        return storeData.getAll();
    }

    @Override
    public Store getById(StoreData storeData, int id) {
        return storeData.getById(id);
    }
}

package fr.esgi.reporitories.stores.adapter;

import entities.Store;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreAdapter {
    @Autowired
    UserAdapter userAdapter;

    public Store entityToModel(TStoreEntity storeEntity){
        Store store = new Store();
        store.setId(storeEntity.getId());
        store.setAddress(storeEntity.getAddress());
        store.setCity(storeEntity.getCity());
        store.setCountry(storeEntity.getCountry());
        store.setName(storeEntity.getName());
        store.setUser(userAdapter.entityToModel(storeEntity.getUser(), false));
        store.setZipcode(storeEntity.getZipcode());

        return store;
    }

    public TStoreEntity modelToEntity(Store store){
        TStoreEntity storeEntity = new TStoreEntity();
        storeEntity.setId(store.getId());
        storeEntity.setAddress(store.getAddress());
        storeEntity.setCity(store.getCity());
        storeEntity.setCountry(store.getCountry());
        storeEntity.setName(store.getName());
        storeEntity.setUser(userAdapter.modelToEntity(store.getUser(), false));
        storeEntity.setZipcode(store.getZipcode());
        return storeEntity;
    }
}

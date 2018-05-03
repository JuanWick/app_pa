package fr.esgi.reporitories.stores.services;

import entities.Store;
import fr.esgi.reporitories.stores.StoreRepository;
import fr.esgi.reporitories.stores.adapter.StoreAdapter;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HibernateStoreData implements StoreData {
    @Autowired
    StoreRepository storeRepository;

    StoreAdapter storeAdapter = new StoreAdapter();

    @Override
    public List<Store> getAll() {
        List<TStoreEntity> list = (List<TStoreEntity>) storeRepository.findAll();
        List<Store> listStore = new ArrayList<>();

        for(TStoreEntity entity:list){
            listStore.add(storeAdapter.adapt(entity));
        }
        return listStore;
    }

    @Override
    public Store getById(int id) {
        return storeAdapter.adapt(storeRepository.findById(id).get());
    }

    @Override
    public Store save(Store store) {
        TStoreEntity storeEntity = storeRepository.save(storeAdapter.convert(store));
        return storeAdapter.adapt(storeEntity);
    }

    @Override
    public Store update(Store place) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Store getByShortName(String shortName) {
        return null;
    }
}

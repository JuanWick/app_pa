package fr.esgi.reporitories.stores.services;

import entities.Product;
import entities.Store;
import fr.esgi.reporitories.stores.StoreRepository;
import fr.esgi.reporitories.stores.adapter.StoreDataAdapter;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
public class HibernateStoreData implements StoreData {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreDataAdapter storeDataAdapter;

    @Override
    public Store getById(int id) {
        Store store = null;
        if(storeRepository.findById(id).isPresent()){
            store = storeDataAdapter.entityToModel(storeRepository.findById(id).get(), true);
        }
        return store;
    }

    @Override
    public List<Store> getByUserId(Integer userId) {
        return storeDataAdapter.entitiesToModels(storeRepository.getByUser_Id(userId));
    }

    @Override
    public Store saveOrUpdate(Store store) {
        TStoreEntity storeEntity = storeDataAdapter.modelToEntity(store,true);
        return storeDataAdapter.entityToModel(storeRepository.save(storeEntity),true);
    }

    @Override
    public void deleteProduct(Integer storeId, Integer productId) {

    }

    @Override
    public List<Product> getProducts(int storeId) {
        Store store = getById(storeId);
        return store.getProducts();
    }

    @Override
    public void delete(int id) {
        if(storeRepository.findById(id).isPresent()){
            TStoreEntity storeEntity = storeRepository.findById(id).get();
            storeRepository.delete(storeEntity);
        }
    }

}

package fr.esgi.reporitories.stores.services;

import entities.Product;
import entities.Store;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreData implements StoreData {

    @Override
    public Store getById(int id) {
        return null;
    }

    @Override
    public List<Store> getByUserId(Integer userId) {
        return null;
    }

    @Override
    public Store saveOrUpdate(Store store) {
        return null;
    }

    @Override
    public void deleteProduct(Integer storeId, Integer productId) {

    }

    @Override
    public List<Product> getProducts(int storeId) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

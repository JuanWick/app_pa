package fr.esgi.reporitories.stores.services;

import entities.Product;
import entities.Store;

import java.util.List;

public interface StoreData {
    Store getById(int id);
    List<Store> getByUserId(Integer userId);
    Store saveOrUpdate(Store store);
    void deleteProduct(Integer storeId,Integer productId);
    List<Product> getProducts(int storeId);
    void delete(int id);
}

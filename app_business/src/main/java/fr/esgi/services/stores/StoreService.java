package fr.esgi.services.stores;

import com.google.maps.errors.ApiException;
import entities.Product;
import entities.Store;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;

import java.io.IOException;
import java.util.List;

public interface StoreService {

    Integer saveOrUpdate(StoreData storeData, Store store) throws InterruptedException, ApiException, IOException;

    List<Product> getProducts(StoreData storeData, int id);

    void addProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId);

    void addProducts(StoreData storeData, ProductData productData, Integer storeId, List<Product> products);

    void removeProduct(StoreData storeData, Integer storeId, Integer productId);

    void delete(StoreData storeData, int id);
}

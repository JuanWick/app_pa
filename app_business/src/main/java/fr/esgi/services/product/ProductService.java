package fr.esgi.services.product;

import entities.Product;
import entities.Store;
import fr.esgi.exception.ExistingProductException;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;

import java.util.List;

public interface ProductService {
    Product save(ProductData productData, Product product) throws ExistingProductException;
    Product getById(ProductData productData, Integer id) throws ProductNotFoundException;
    Product getByName(ProductData productData, String name) throws ProductNotFoundException;
    Product getByBarreCode(ProductData productData, String barreCode) throws ProductNotFoundException;
    void delete(ProductData productData, int productId) throws ProductNotFoundException;
    List<Store> searchByValue(StoreData storeData, ProductData productData, String searchValue, Double latitude, Double longitude, Double perimeter);

    List<Store> searchByCategorie(StoreData storeData, ProductData productData, String categorie, Double latitude, Double longitude, Double perimeter);
}

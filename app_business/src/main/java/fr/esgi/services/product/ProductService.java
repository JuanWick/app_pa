package fr.esgi.services.product;

import entities.Product;
import entities.Store;
import fr.esgi.reporitories.products.services.ProductData;

import java.util.List;

public interface ProductService {
    Product save(ProductData productData, Product product);
    void delete(ProductData productData, int productId);
    List<Store> searchByValue(ProductData productData, String searchValue, Double latitude, Double longitude, Double perimeter);
    List<Store> searchByCategorie(ProductData productData, String categorie, Double latitude, Double longitude, Double perimeter);
}

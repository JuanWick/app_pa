package fr.esgi.reporitories.products.services;

import entities.Product;
import entities.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductData {
    Product getById(int id);
    Product saveOrUpdate(Product product);
    void delete(int id);
   List<Store> getStoresCoordinatesWithProductCategory(String categorie);
   List<Store> getStoresCoordiantesWithProductValue(String searchValue);
}

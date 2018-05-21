package fr.esgi.reporitories.products.services;

import entities.Product;
import entities.Store;

import java.util.List;

public interface ProductData {
    Product getById(int id);
    Product saveOrUpdate(Product product);
    void delete(int id);
}

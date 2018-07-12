package fr.esgi.reporitories.products.services;

import entities.Category;
import entities.Product;

import java.util.List;

public interface ProductData {
    Product getById(int id);
    Product getByName(String name);
    Product getByBarreCode(String name);
    Product saveOrUpdate(Product product);
    void delete(int id);
   List<Object[]> getStoresWithProductCategory(String categorie);
   List<Object[]> getStoresWithProductValue(String searchValue);
    Category getCategoryById(Integer categoryId);
    void deleteCategory(Integer categoryId);
    Integer saveOrUpdateCategory(Category category);
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(Integer categoryId);

    Category getCategoryByName(String name);
}

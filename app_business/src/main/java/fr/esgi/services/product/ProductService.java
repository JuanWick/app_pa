package fr.esgi.services.product;

import entities.Category;
import entities.Product;
import fr.esgi.exception.*;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;

import java.util.List;

public interface ProductService {
    Product save(ProductData productData, Product product) throws ExistingProductException;
    Product getById(ProductData productData, Integer id) throws ProductNotFoundException;
    Product getByName(ProductData productData, String name) throws ProductNotFoundException;
    Product getByBarreCode(ProductData productData, String barreCode) throws ProductNotFoundException;
    void delete(ProductData productData, int productId) throws ProductNotFoundException;
    List<Object[]> searchByValue(StoreData storeData, ProductData productData, String searchValue, Double latitude, Double longitude, Double perimeter);
    List<Object[]> searchByCategorie(StoreData storeData, ProductData productData, String categorie, Double latitude, Double longitude, Double perimeter);
    List<Category> getAllCategorie(ProductData productData);
    Integer saveCategory(ProductData productData, Category category) throws CategoryAlreadyExistException, CategoryCreationException;
    Category getCategoryById(ProductData productData, Integer id) throws CategoryNotFoundException;
    void deleteCategory(ProductData productData, Integer categoryId) throws CategoryNotFoundException, CategoryUsedException;
    void addCategoryToProduct(ProductData productData, int productId, int categoryId);
    void removeCategoryToProduct(ProductData productData, int productId, int categoryId);

}

package fr.esgi.services.product;

import entities.Category;
import entities.Product;
import entities.Store;
import fr.esgi.exception.*;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.services.util.GpsUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product save(ProductData productData, Product product) throws ExistingProductException {
//        //On verifie qu'un produit de meme nom n'existe pas déjà
//        if(null != productData.getByName(product.getName())){
//            throw new ExistingProductException();
//        }
//        //On vérifie qu'un produit de meme codebarre n'existe pas déjà
//        if(null != productData.getByBarreCode(product.getBarreCode())){
//            throw new ExistingProductException();
//        }
        return productData.saveOrUpdate(product);
    }

    @Override
    public Product getById(ProductData productData, Integer id) throws ProductNotFoundException {
        Product product = productData.getById(id);
        if (null != product) {
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product getByName(ProductData productData, String name) throws ProductNotFoundException {
        Product product = productData.getByName(name);
        if (null != product) {
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product getByBarreCode(ProductData productData, String barreCode) throws ProductNotFoundException {
        Product product = productData.getByBarreCode(barreCode);
        if (null != product) {
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void delete(ProductData productData, int productId) throws ProductNotFoundException {
        if (null != productData.getById(productId)) {
            productData.delete(productId);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Object[]> searchByValue(StoreData storeData, ProductData productData, String searchValue, Double latitude, Double longitude, Double perimeter) {
        /* On récupère les stores dans le périmètre **/
        List<Object[]> stores = productData.getStoresWithProductValue(searchValue);
        GpsUtil gpsUtil = new GpsUtil();
        /* On isole ceux qui sont dans le périmètre **/
        List<Object[]> storesWithProduct = new ArrayList<>();

        EvaluateDistance(storeData, productData, latitude, longitude, perimeter, stores, gpsUtil, storesWithProduct);
        return storesWithProduct;
    }

    @Override
    public List<Object[]> searchByCategorie(StoreData storeData, ProductData productData, String categorie, Double latitude, Double longitude, Double perimeter) {
        /* On récupère les magasins qui ont des produit de la catégorie **/
        List<Object[]> stores = productData.getStoresWithProductCategory(categorie);
        GpsUtil gpsUtil = new GpsUtil();
        /* On isole ceux qui sont dans le périmètre **/
        List<Object[]> storesWithProduct = new ArrayList<>();

        EvaluateDistance(storeData, productData, latitude, longitude, perimeter, stores, gpsUtil, storesWithProduct);
        return storesWithProduct;
    }

    private void EvaluateDistance(StoreData storeData, ProductData productData, Double latitude, Double longitude, Double perimeter, List<Object[]> stores, GpsUtil gpsUtil, List<Object[]> storesWithProduct) {
        for (Object[] store : stores) {
            if (perimeter >= gpsUtil.getDistanceBetweenCoordinates(latitude, longitude, ((Store) store[0]).getLatitude(), ((Store) store[0]).getLongitude())) {
                Store storeComplete = storeData.getById(((Store) store[0]).getId());
                storeComplete.setDistance(gpsUtil.getDistanceBetweenCoordinates(latitude, longitude, storeComplete.getLatitude(), storeComplete.getLongitude()));
                store[0] = storeComplete;
                Product product = productData.getById(((Product) store[1]).getId());
                store[1] = product;
                storesWithProduct.add(store);
            }
        }
    }

    @Override
    public List<Category> getAllCategorie(ProductData productData) {
        return productData.getAllCategories();
    }

    @Override
    public Integer saveCategory(ProductData productData, Category category) throws CategoryAlreadyExistException, CategoryCreationException {
        if(null != category
                && null != category.getName()
                && !category.getName().isEmpty()){
            category.setName(category.getName().toUpperCase());
            System.out.println("category >> " +category.getName());
            System.out.println("xx >> "+productData.getCategoryByName(category.getName()));
            if(null == productData.getCategoryByName(category.getName())){
                return productData.saveOrUpdateCategory(category);
            } else {
                throw new CategoryAlreadyExistException();
            }
        } else {
            throw new CategoryCreationException();
        }
    }

    @Override
    public Category getCategoryById(ProductData productData, Integer categoryId) throws CategoryNotFoundException {
        if (null != productData.getCategoryById(categoryId)) {
            return productData.getCategoryById(categoryId);
        } else {
            throw new CategoryNotFoundException();
        }    }

    @Override
    public void deleteCategory(ProductData productData, Integer categoryId) throws CategoryNotFoundException, CategoryUsedException {
        if (null != productData.getCategoryById(categoryId) ) {
            if(productData.getProductsByCategory(categoryId).isEmpty()) {
                productData.deleteCategory(categoryId);
            } else {
                throw new CategoryUsedException();
            }
        } else {
            throw new CategoryNotFoundException();
        }

    }

}

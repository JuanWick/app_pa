package fr.esgi.services.product;

import entities.Product;
import entities.Store;
import fr.esgi.exception.ExistingProductException;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.services.util.GpsUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product save(ProductData productData, Product product) throws ExistingProductException {
        //On verifie qu'un produit de meme nom n'existe pas déjà
        if(null != productData.getByName(product.getName())){
            throw new ExistingProductException();
        }
        //On vérifie qu'un produit de meme codebarre n'existe pas déjà
        if(null != productData.getByBarreCode(product.getBarreCode())){
            throw new ExistingProductException();
        }
        return productData.saveOrUpdate(product);
    }

    @Override
    public Product getById(ProductData productData, Integer id) throws ProductNotFoundException {
        Product product = productData.getById(id);
        if(null != product){
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product getByName(ProductData productData, String name) throws ProductNotFoundException {
        Product product = productData.getByName(name);
        if(null != product){
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product getByBarreCode(ProductData productData, String barreCode) throws ProductNotFoundException {
        Product product = productData.getByBarreCode(barreCode);
        if(null != product){
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void delete(ProductData productData, int productId) throws ProductNotFoundException {
        if(null != productData.getById(productId)){
            productData.delete(productId);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Store> searchByValue(ProductData productData, String searchValue, Double latitude, Double longitude, Double perimeter) {
        /* On récupère les stores dans le périmètre **/
        List<Store> stores = productData.getStoresCoordiantesWithProductValue(searchValue);

        /* On isole ceux qui sont dans le périmètre **/
        List<Store> storesWithProduct = new ArrayList<>();

        for(Store store : stores){
            if(perimeter <= GpsUtil.getDistanceBetweenCoordinates(latitude,longitude,store.getLatitude(),store.getLongitude())){
                storesWithProduct.add(store);
            }
        }
        return storesWithProduct;
    }

    @Override
    public List<Store> searchByCategorie(ProductData productData, String categorie, Double latitude, Double longitude, Double perimeter) {
        /* On récupère les magasins qui ont des produit de la catégorie **/
        List<Store> stores = productData.getStoresCoordinatesWithProductCategory(categorie);

        /* On isole ceux qui sont dans le périmètre **/
        List<Store> storesWithProduct = new ArrayList<>();

        for(Store store : stores){
            if(perimeter <= GpsUtil.getDistanceBetweenCoordinates(latitude,longitude,store.getLatitude(),store.getLongitude())){
                storesWithProduct.add(store);
            }
        }
        return storesWithProduct;
    }

}

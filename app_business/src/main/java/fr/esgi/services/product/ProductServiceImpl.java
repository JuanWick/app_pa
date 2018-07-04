package fr.esgi.services.product;

import entities.Product;
import entities.Store;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.services.util.GpsUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product save(ProductData productData, Product product) {

        return productData.saveOrUpdate(product);
    }

    @Override
    public void delete(ProductData productData, int productId) {
        productData.delete(productId);
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

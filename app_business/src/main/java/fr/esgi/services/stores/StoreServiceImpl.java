package fr.esgi.services.stores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.GeocodingResult;
import entities.Product;
import entities.Store;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.services.util.GoogleApiUtil;
import lombok.Data;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class StoreServiceImpl implements StoreService {

    private String GoogleApiKey;

    @Override
    public Integer saveOrUpdate(StoreData storeData, Store store) throws InterruptedException, ApiException, IOException {
        GoogleApiUtil googleApiUtil = new GoogleApiUtil();

        if(null == store.getLatitude() || null == store.getLongitude()) {
            GeoApiContext geoApiContext = new GeoApiContext.Builder()
                    .apiKey(googleApiUtil.getGoogleApiKeyValue())
                    .build();

            GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext,
                    store.getAddress() + " " + store.getZipcode() + " " + store.getCountry() ).await();
            if(null != results[0]){
                store.setLatitude(results[0].geometry.location.lat);
                store.setLongitude(results[0].geometry.location.lng);

            }
        }
        return storeData.saveOrUpdate(store).getId();
    }

    @Override
    public List<Product> getProducts(StoreData storeData, int id) {
        return storeData.getProducts(id);
    }

    @Override
    public void addProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId) {
        if(null != storeData.getById(storeId) && null != productData.getById(productId)){
            Store store = storeData.getById(storeId);
            Product product = productData.getById(productId);
            List<Product> products = new ArrayList<>();
            products.add(product);

            if(null != store.getProducts()){
                products.addAll(store.getProducts());
            }
            store.setProducts(products);
            storeData.saveOrUpdate(store);
        } else {
            //TODO renvoyer une erreur
        }
    }

    @Override
    public void addProducts(StoreData storeData, ProductData productData, Integer storeId, List<Product> products) {
        List<Product> savedProducts = new ArrayList<>();

        for(Product product : products){
            Product savedProduct = productData.saveOrUpdate(product);
            savedProducts.add(savedProduct);
        }

        Store store = storeData.getById(storeId);

        if(null != store){
            List<Product> currentProducts = store.getProducts();
            if(null != currentProducts){
                currentProducts.addAll(savedProducts);
            } else {
                store.setProducts(savedProducts);
            }
            storeData.saveOrUpdate(store);
        }
    }

    @Override
    public void removeProduct(StoreData storeData, Integer storeId, Integer productId) {
        if(null != storeData.getById(storeId)){
            Store store = storeData.getById(storeId);
            List<Product> products = new ArrayList<>();

            if(null != store.getProducts()){
                for(Product product:store.getProducts()){
                    if(!product.getId().equals(productId)){
                        products.add(product);
                    }
                }
            }
            store.setProducts(products);
            storeData.saveOrUpdate(store);
        }
    }

    @Override
    public void delete(StoreData storeData,int id) {
        storeData.delete(id);
    }
}

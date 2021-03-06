package fr.esgi.services.stores;

import com.google.maps.errors.ApiException;
import entities.Product;
import entities.Store;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.util.List;

public interface StoreService {


    Integer saveOrUpdate(StoreData storeData, UserData userData, Store store) throws UserNotFoundException, IOException, InterruptedException, ApiException;

    List<Product> getProducts(StoreData storeData, int id);

    void addProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId);

    void addProducts(StoreData storeData, ProductData productData, Integer storeId, List<Product> products);

    void removeProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId);

    void delete(StoreData storeData, int id);

    Store getById(StoreData storeData, int id);

    boolean importProductsFromExcelFile(StoreData storeData,  ProductData productData, int storeId, HSSFWorkbook hssfWorkbook );

    List<Store> getByUserId(StoreData storeData, int userId);
}

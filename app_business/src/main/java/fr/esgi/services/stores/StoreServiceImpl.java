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
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.exception.StoreNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.util.GoogleApiUtil;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
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
    public Integer saveOrUpdate(StoreData storeData, UserData userData, Store store) throws UserNotFoundException, IOException, InterruptedException, ApiException, StoreNotFoundException {
        //Si un id de magasin est spécifié, on vérifie son existence
        if(null != store.getId() && null == storeData.getById(store.getId())){
            throw new StoreNotFoundException();
        }

        //On vérifie que l'utilisateur associé au magasin est valide
        if(null == userData.getById(store.getUser().getId())){
            throw new UserNotFoundException();
        }

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
        //On vérifie que le magasin est valide
        if(null == storeData.getById(id)){
            throw new StoreNotFoundException();
        }
        return storeData.getProducts(id);
    }

    @Override
    public void addProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId) {
        if(null != storeId && null == storeData.getById(storeId)){
            throw new StoreNotFoundException();
        }

        if(null != productId && null == productData.getById(productId)){
            throw new ProductNotFoundException();
        }

            Store store = storeData.getById(storeId);
            Product product = productData.getById(productId);
            List<Product> products = new ArrayList<>();
            products.add(product);

            if(null != store.getProducts()){
                products.addAll(store.getProducts());
            }
            store.setProducts(products);
            storeData.saveOrUpdate(store);
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
    public void removeProduct(StoreData storeData, ProductData productData, Integer storeId, Integer productId) {
        if(null != storeId && null == storeData.getById(storeId)) {
        throw new StoreNotFoundException();
        }

        if(null != productId && null == productData.getById(productId)) {
            throw new ProductNotFoundException();
        }
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

    @Override
    public void delete(StoreData storeData,int id) {
        Store store = storeData.getById(id);
        if(null == store){
            throw new StoreNotFoundException();
        }
        storeData.delete(id);
    }

    @Override
    public Store getById(StoreData storeData, int id) {
        Store store = storeData.getById(id);

        if(null == store){
           throw new StoreNotFoundException();
        }
        return store;
    }

    @Override
    public boolean importProductsFromExcelFile(StoreData storeData, ProductData productData, int storeId, HSSFWorkbook hssfWorkbook) {
        if(null == storeData.getById(storeId)){
            throw new StoreNotFoundException();
        }

            List<Product> products = new ArrayList<>();

            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            boolean sucess = true;
            int count = 0;
            /** Parcours et importation des données **/
            for (Row row : sheet) {
                String name = null;
                String info = null;
                String barreCode = null;
                Double price = null;

                for (Cell cell : row) {
                    if(0 != row.getRowNum()){
                        if(0 == cell.getColumnIndex() ) {
                            if(cell.getCellTypeEnum() == CellType.STRING) {
                                name=cell.getStringCellValue();
                            } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                                name=String.valueOf(cell.getNumericCellValue());
                            }
                        }
                        if(1 == cell.getColumnIndex()){
                            if(cell.getCellTypeEnum() == CellType.STRING) {
                                info = cell.getStringCellValue();
                            } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                                info = String.valueOf(cell.getNumericCellValue());
                            }
                        }
                    }if(2 == cell.getColumnIndex()){
                        if(cell.getCellTypeEnum() == CellType.STRING) {
                            barreCode = cell.getStringCellValue();
                        } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                            barreCode = String.valueOf(cell.getNumericCellValue());
                        }
                    }if(3 == cell.getColumnIndex()){
                        if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                            price = cell.getNumericCellValue();
                        }
                    }
                }
                if(null != name
                        && !name.isEmpty()
                        && null != price){
                    products.add(Product.builder().name(name).info(info).barreCode(barreCode).price(price).build());
                } else {
                    if(count>0){sucess= false;}
                }
                count++;
            }
        if(sucess && products.size()>0) {
            addProducts(storeData, productData, storeId, products);
        }
        return sucess;
    }
}

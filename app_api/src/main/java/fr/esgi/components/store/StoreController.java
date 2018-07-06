package fr.esgi.components.store;

import com.google.maps.errors.ApiException;
import entities.Product;
import entities.Store;
import entities.User;
import fr.esgi.components.product.adapter.ProductApiAdapter;
import fr.esgi.components.store.adapter.StoreApiAdapter;
import fr.esgi.components.store.dto.StoreAddDto;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.exception.*;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.product.ProductService;
import fr.esgi.services.stores.StoreService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductData productData;
    @Autowired
    StoreData storeData;
    @Autowired
    UserData userData;
    @Autowired
    StoreApiAdapter storeApiAdapter;
    @Autowired
    ProductApiAdapter productApiAdapter;

    public StoreController() {}

    /**
     * Permet la creation ou la modification d'un magasin
     * @param storeAddDto détail du magasin à créer, si l'id du store est null = Ajout, sinon modification
     * @return ID du magasin
     */
    @PostMapping("")
    public Integer saveOrUpdate(@Validated @RequestBody final StoreAddDto storeAddDto)  {
        User user = new User();
        user.setId(storeAddDto.getUserId());

        Store store = Store.builder()
                .id(storeAddDto.getId())
                .zipcode(storeAddDto.getZipcode())
                .name(storeAddDto.getName())
                .country(storeAddDto.getCountry())
                .city(storeAddDto.getCity())
                .address(storeAddDto.getAddress())
                .latitude(storeAddDto.getLatitude())
                .longitude(storeAddDto.getLongitude())
                .user(user).build();
        try {
            return storeService.saveOrUpdate(storeData, userData, store);
        } catch (UserNotFoundException u){
            throw new UserNotFoundExceptionApi(u.getMessage());
        } catch (InterruptedException | ApiException | IOException ex) {
            throw new LocalizationExceptionApi(ex.getMessage());
        } catch (StoreNotFoundException s) {
            throw new StoreNotFoundExceptionApi(s.getMessage());
        }
    }

    /**
     * Permet la récupération d'un magasin par id
     * @param storeId ID du magasin
     * @return les détails du magasin
     */
    @GetMapping("/{storeId}")
    public StoreDto getById(@PathVariable(value="storeId") int storeId) {
        try{
            return storeApiAdapter.convertToDto(storeService.getById(storeData,storeId));
        } catch (StoreNotFoundException ex){
            throw new StoreNotFoundExceptionApi(ex.getMessage());
        }
    }

    /**
     * Permet la suppression d'un magasin
     * @param storeId ID du magasin à supprimer
     */
    @DeleteMapping("/{storeId}")
    public void delete(@PathVariable(value="storeId") int storeId){
        try{
            storeService.delete(storeData,storeId);
        } catch (StoreNotFoundException ex){
            throw new StoreNotFoundExceptionApi(ex.getMessage());
        }
    }

    /**
     * Permet l'ajout d'un produit à un magasin
     * @param storeId id du magasin
     * @param productId id d'un produit
     */
    @PostMapping("/{storeId}/products/{productId}")
    public void addProduct(@PathVariable(value="storeId") int storeId,@PathVariable(value="productId") int productId){
        try {
            storeService.addProduct(storeData, productData, storeId, productId);
        } catch (StoreNotFoundException s){
            throw  new StoreNotFoundExceptionApi(s.getMessage());
        } catch (ProductNotFoundException p){
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet la récupération des produits proposés par un magasin
     * @param storeId ID du magasin
     * @return Une liste des id des produits du magasin
     */
    @GetMapping("/{storeId}/products")
    public List<Integer> getProductsByStoreId(@PathVariable(value="storeId") int storeId) {
        try {
            List<Product> products = storeService.getProducts(storeData, storeId);
            List<Integer> productIds = new ArrayList<>();
            for(Product product : products){
                productIds.add(product.getId());
            }
            return productIds;
        } catch(StoreNotFoundException s){
            throw new StoreNotFoundExceptionApi(s.getMessage());
        }

    }

    /**
     * Permet de supprimer un produit d'un magasin
     * @param storeId id du magasin
     * @param productId id d'un produit
     */
    @DeleteMapping("/{storeId}/products/{productId}")
    public void removeProduct(@PathVariable(value="storeId") int storeId,@PathVariable(value="productId") int productId){
        try{
            storeService.removeProduct(storeData, productData, storeId, productId);
        } catch (StoreNotFoundException s){
            throw  new StoreNotFoundExceptionApi(s.getMessage());
        } catch (ProductNotFoundException p){
            throw  new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    @PostMapping("/{storeId}/products")
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable(value="storeId") int storeId) {
        try {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
            if(!storeService.importProductsFromExcelFile(storeData, productData, storeId,new HSSFWorkbook(poifsFileSystem))){
                throw new ImportFileExceptionApi("Erreur : Le fichier semble incorrect");
            }
        } catch (IOException e) {
            throw new IOExceptionApi(e.getMessage());
        } catch (StoreNotFoundException s){
            throw new StoreNotFoundExceptionApi(s.getMessage());
        }

    }
}

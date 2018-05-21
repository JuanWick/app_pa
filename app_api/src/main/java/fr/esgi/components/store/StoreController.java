package fr.esgi.components.store;

import com.google.maps.errors.ApiException;
import entities.Product;
import entities.Store;
import entities.User;
import fr.esgi.components.product.adapter.ProductApiAdapter;
import fr.esgi.components.store.adapter.StoreApiAdapter;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.store.dto.StoreAddDto;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.services.product.ProductService;
import fr.esgi.services.stores.StoreService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    public Integer saveOrUpdate(@Validated @RequestBody final StoreAddDto storeAddDto) throws InterruptedException, ApiException, IOException {
        User user = new User();
        user.setId(storeAddDto.getUserId());

        Store store = Store.builder()
                .id(null != storeAddDto.getId()?storeAddDto.getId():null)
                .zipcode(storeAddDto.getZipcode())
                .name(storeAddDto.getName())
                .country(storeAddDto.getCountry())
                .city(storeAddDto.getCity())
                .address(storeAddDto.getAddress())
                .latitude(null != storeAddDto.getLatitude()?storeAddDto.getLatitude():null)
                .longitude(null != storeAddDto.getLongitude()?storeAddDto.getLongitude():null)
                .user(user).build();
        return storeService.saveOrUpdate(storeData,store);
    }

    /**
     * Permet la récupération d'un magasin par id
     * @param storeId ID du magasin
     * @return les détails du magasin
     */
    @GetMapping("/{storeId}")
    public StoreDto getById(@PathVariable(value="storeId") int storeId) {
        return storeApiAdapter.convertToDto(storeData.getById(storeId));
    }

    /**
     * Permet la suppression d'un magasin
     * @param storeId ID du magasin à supprimer
     */
    @DeleteMapping("/{storeId}")
    public void delete(@PathVariable(value="storeId") int storeId){
        storeService.delete(storeData,storeId);
    }

    /**
     * Permet l'ajout d'un produit à un magasin
     * @param storeId id du magasin
     * @param productId id d'un produit
     */
    @PostMapping("/{storeId}/products/{productId}")
    public void addProduct(@PathVariable(value="storeId") int storeId,@PathVariable(value="productId") int productId){
        storeService.addProduct(storeData, productData, storeId, productId);
    }

    /**
     * Permet la récupération des produits proposés par un magasin
     * @param storeId ID du magasin
     * @return Une liste des id des produits du magasin
     */
    @GetMapping("/{storeId}/products")
    public List<Integer> getProductsByStoreId(@PathVariable(value="storeId") int storeId) {
        List<Product> products = storeService.getProducts(storeData, storeId);
        List<Integer> productIds = new ArrayList<>();
        for(Product product : products){
            productIds.add(product.getId());
        }
        return productIds;
    }

    /**
     * Permet de supprimer un produit d'un magasin
     * @param storeId id du magasin
     * @param productId id d'un produit
     */
    @DeleteMapping("/{storeId}/products/{productId}")
    public void removeProduct(@PathVariable(value="storeId") int storeId,@PathVariable(value="productId") int productId){
        storeService.removeProduct(storeData, storeId, productId);
    }

    @PostMapping("/{storeId}/products")
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable(value="storeId") int storeId) {
        if(null != storeData.getById(storeId)){
            try {
                List<Product> products = new ArrayList<>();

                POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);

                HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
                /** Parcours et importation des données **/
                for (Row row : sheet) {
                    ProductCompletDto productDto = new ProductCompletDto();

                    for (Cell cell : row) {
                        if(0 != row.getRowNum()){
                            if(0 == cell.getColumnIndex() ) {
                                if(cell.getCellTypeEnum() == CellType.STRING) {
                                    productDto.setName(cell.getStringCellValue());
                                } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    productDto.setName(String.valueOf(cell.getNumericCellValue()));
                                }
                            }
                            if(1 == cell.getColumnIndex()){
                                if(cell.getCellTypeEnum() == CellType.STRING) {
                                    productDto.setInfo(cell.getStringCellValue());
                                } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    productDto.setInfo(String.valueOf(cell.getNumericCellValue()));
                                }
                            }
                            if(2 == cell.getColumnIndex()){
                                if(cell.getCellTypeEnum() == CellType.STRING) {
                                    productDto.setBarreCode(cell.getStringCellValue());
                                } else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    productDto.setBarreCode(String.valueOf(cell.getNumericCellValue()));
                                }
                            }
                        }
                    }
                    if(null != productDto.getName()){
                        products.add(productApiAdapter.convertToModel(productDto));
                    }
                }

                if(products.size()>0){
                    storeService.addProducts(storeData,productData, storeId, products);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

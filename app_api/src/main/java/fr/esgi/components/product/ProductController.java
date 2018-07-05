package fr.esgi.components.product;

import entities.Product;
import fr.esgi.components.product.adapter.ProductApiAdapter;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductSearchRequestDto;
import fr.esgi.components.product.dto.ProductSearchResultDto;
import fr.esgi.exception.ExistingProductException;
import fr.esgi.exception.ExistingProductExceptionApi;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.exception.ProductNotFoundExceptionApi;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final
    ProductData productData;

    private final
    StoreData storeData;

    private final
    ProductService productService;

    private final
    ProductApiAdapter productApiAdapter;

    @Autowired
    public ProductController(StoreData storeData, ProductData productData, ProductService productService, ProductApiAdapter productApiAdapter) {
        this.productData = productData;
        this.storeData = storeData;
        this.productService = productService;
        this.productApiAdapter = productApiAdapter;
    }

    @PostMapping("")
    public Integer saveOrUpdate(@RequestBody ProductCompletDto productCompletDto){
        try{
            Product product = productService.save(productData, productApiAdapter.convertToModel(productCompletDto));
            return product.getId();
        } catch (ExistingProductException e){
            throw new ExistingProductExceptionApi(e.getMessage());
        }
    }

    /**
     * Permet de récupérer le détail d'un produit
     * @param productId id du produit
     * @return le détail d'un produit
     */
    @GetMapping("/{productId}")
    public ProductCompletDto getById(@PathVariable(value="productId") int productId) {
        try {
            return productApiAdapter.convertToDto(productService.getById(productData,productId));
        } catch (ProductNotFoundException p) {
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet de récupérer le détail d'un produit
     * @param name nom du produit
     * @return le détail d'un produit
     */
    @GetMapping("/name")
    public ProductCompletDto getByName(@RequestParam(value="value", defaultValue="") String name) {
        try {
            return productApiAdapter.convertToDto(productService.getByName(productData,name));
        } catch (ProductNotFoundException p) {
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet de récupérer le détail d'un produit
     * @param value nom du produit
     * @return le détail d'un produit
     */
    @GetMapping("/barrecode")
    public ProductCompletDto getByBarreCode(@RequestParam(value="value", defaultValue="") String value) {
        try {
            return productApiAdapter.convertToDto(productService.getByBarreCode(productData,value));
        } catch (ProductNotFoundException p) {
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet la suppression d'un produit
     * @param productId, id du produit
     */
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable(value="productId") int productId){
        try{
            productService.delete(productData,productId);
        } catch (ProductNotFoundException p){
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet la recherche d'un produit dans l'ensemble des magasins en se basant sur une chaine de carectère
     * pouvant être dans le nom ou le codebar
     * @return ProductSearchResultDto
     */
    @PostMapping("/locateByValue")
    public ProductSearchResultDto searchProductByValue(@RequestBody ProductSearchRequestDto productSearchRequestDto){
            return productApiAdapter.convertListToProductSearchResultDto(
                    productService.searchByValue(
                        storeData,
                        productData,
                        productSearchRequestDto.getSearchValue(),
                        productSearchRequestDto.getLatitude(),
                        productSearchRequestDto.getLongitude(),
                        productSearchRequestDto.getPerimeter()));
    }

    /**
    * Permet la recherche d'un produit dans l'ensemble des magasins en se basant sur une categorie
     * @return ProductSearchResultDto
     */
    @PostMapping("/locateByCategory")
    public ProductSearchResultDto searchProductByCategory(@RequestBody ProductSearchRequestDto productSearchRequestDto){
        return productApiAdapter.convertListToProductSearchResultDto(
                productService.searchByCategorie(
                        storeData,
                        productData,
                        productSearchRequestDto.getSearchValue(),
                        productSearchRequestDto.getLatitude(),
                        productSearchRequestDto.getLongitude(),
                        productSearchRequestDto.getPerimeter()));
    }
}

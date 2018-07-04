package fr.esgi.components.product;

import entities.Product;
import fr.esgi.components.product.adapter.ProductApiAdapter;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductSearchRequestDto;
import fr.esgi.components.product.dto.ProductSearchResultDto;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final
    ProductData productData;

    private final
    ProductService productService;

    private final
    ProductApiAdapter productApiAdapter;

    @Autowired
    public ProductController(ProductData productData, ProductService productService, ProductApiAdapter productApiAdapter) {
        this.productData = productData;
        this.productService = productService;
        this.productApiAdapter = productApiAdapter;
    }

    @PostMapping("")
    public Integer saveOrUpdate(@RequestBody ProductCompletDto productCompletDto){
        Product product = productService.save(productData, productApiAdapter.convertToModel(productCompletDto));

        return product.getId();
    }

    /**
     * Permet de récupérer le détail d'un produit
     * @param productId id du produit
     * @return le détail d'un produit
     */
    @GetMapping("/{productId}")
    public ProductCompletDto getById(@PathVariable(value="productId") int productId) {
        return productApiAdapter.convertToDto(productData.getById(productId));
    }
    //Creation/update d'un produit

    /**
     * Permet la suppression d'un produit
     * @param productId, id du produit
     */
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable(value="productId") int productId){
        productService.delete(productData,productId);
    }

    /**
     * Permet la recherche d'un produit dans l'ensemble des magasins en se basant sur une chaine de carectère
     * pouvant être dans le nom ou le codebar
     * @return ProductSearchResultDto
     */
    @GetMapping("/searchByValue")
    public ProductSearchResultDto searchProductByValue(@RequestBody ProductSearchRequestDto productSearchRequestDto){
            return productApiAdapter.convertListToProductSearchResultDto(
                    productService.searchByValue(
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
    @GetMapping("/searchByCategory")
    public ProductSearchResultDto searchProductByCategory(@RequestBody ProductSearchRequestDto productSearchRequestDto){
        return productApiAdapter.convertListToProductSearchResultDto(
                productService.searchByCategorie(
                        productData,
                        productSearchRequestDto.getSearchValue(),
                        productSearchRequestDto.getLatitude(),
                        productSearchRequestDto.getLongitude(),
                        productSearchRequestDto.getPerimeter()));
    }
}

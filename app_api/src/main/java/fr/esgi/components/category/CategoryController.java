package fr.esgi.components.category;

import fr.esgi.components.category.adapter.CategoryApiAdapter;
import fr.esgi.components.category.dto.CategoryApiDto;
import fr.esgi.exception.*;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/categories")
public class CategoryController {

    private final
    ProductService productService;

    private final
    ProductData productData;

    private final
    CategoryApiAdapter categoryAdapter;

    @Autowired
    public CategoryController(ProductService productService, ProductData productData, CategoryApiAdapter categoryAdapter) {
        this.productService = productService;
        this.productData = productData;
        this.categoryAdapter = categoryAdapter;
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Integer saveOrUpdate(@RequestBody CategoryApiDto categoryApiDto){

        try {
            return productService.saveCategory(productData,categoryAdapter.dtoToModel(categoryApiDto));
        } catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundExceptionApi(e.getMessage());
        } catch (CategoryAlreadyExistException e) {
            throw new CategoryAlreadyExistExceptionApi(e.getMessage());
        } catch (CategoryCreationException e) {
            throw new CategoryCreationExceptionApi(e.getMessage());
        }
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryApiDto getById(@PathVariable(value="categoryId") int categoryId) {
        try {
            return categoryAdapter.modelToDto(productService.getCategoryById(productData,categoryId));
        }catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundExceptionApi(e.getMessage());
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<CategoryApiDto> getAll() {
        try {
            return categoryAdapter.modelsToDtos(productService.getAllCategorie(productData));
        }catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundExceptionApi(e.getMessage());
        }
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void deleteById(@PathVariable(value="categoryId") int categoryId) {
        try {
            productService.deleteCategory(productData,categoryId);
        }catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundExceptionApi(e.getMessage());
        } catch (CategoryUsedException e) {
            throw new CategoryUsedExceptionApi(e.getMessage());
        }
    }

}

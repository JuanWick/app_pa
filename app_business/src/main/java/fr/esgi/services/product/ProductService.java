package fr.esgi.services.product;

import entities.Product;
import fr.esgi.reporitories.products.services.ProductData;

public interface ProductService {
    Product save(ProductData productData, Product product);
    void delete(ProductData productData, int productId);
}

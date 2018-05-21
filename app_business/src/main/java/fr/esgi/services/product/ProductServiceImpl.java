package fr.esgi.services.product;

import entities.Product;
import fr.esgi.reporitories.products.services.ProductData;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product save(ProductData productData, Product product) {

        return productData.saveOrUpdate(product);
    }

    @Override
    public void delete(ProductData productData, int productId) {
        productData.delete(productId);
    }
}

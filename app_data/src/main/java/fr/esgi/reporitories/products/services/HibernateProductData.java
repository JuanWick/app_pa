package fr.esgi.reporitories.products.services;

import entities.Product;
import entities.Store;
import fr.esgi.reporitories.products.ProductRepository;
import fr.esgi.reporitories.products.adapter.ProductDataAdapter;
import fr.esgi.reporitories.products.dao.TProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class HibernateProductData implements ProductData{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDataAdapter productDataAdapter;

    @Override
    public Product getById(int productId) {
        Product product = null;

        if(productRepository.findById(productId).isPresent()){
            product = productDataAdapter.entityToModel(productRepository.findById(productId).get(),true);
        }


        return product;
    }

    @Override
    public Product saveOrUpdate(Product product) {

        TProductEntity productEntity = productRepository.save(productDataAdapter.modelToEntity(product,true));
        return productDataAdapter.entityToModel(productEntity,true);
    }

    @Override
    public void delete(int id) {
        if(productRepository.findById(id).isPresent()){
            TProductEntity productEntity = productRepository.findById(id).get();
            productRepository.delete(productEntity);
        }
    }

    @Override
    public List<Store> getStoresCoordinatesWithProductCategory(String categorie) {
        return productRepository.getStoresCoordinatesWithProductCategory(categorie);
    }

    @Override
    public List<Store> getStoresCoordiantesWithProductValue(String searchValue) {
        return productRepository.getStoresCoordiantesWithProductValue(searchValue);
    }
}

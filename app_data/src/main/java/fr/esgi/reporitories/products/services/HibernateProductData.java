package fr.esgi.reporitories.products.services;

import entities.Product;
import fr.esgi.reporitories.products.ProductRepository;
import fr.esgi.reporitories.products.adapter.ProductDataAdapter;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.adapter.StoreDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class HibernateProductData implements ProductData{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDataAdapter productDataAdapter;

    @Autowired
    StoreDataAdapter storeDataAdapter;

    @Override
    public Product getById(int productId) {
        Product product = null;

        if(productRepository.findById(productId).isPresent()){
            product = productDataAdapter.entityToModel(productRepository.findById(productId).get(),true);
        }

        return product;
    }

    @Override
    public Product getByName(String name) {
        List<TProductEntity> productEntities = productRepository.getTProductEntityByName(name);

        if(null != productEntities && !productEntities.isEmpty()){
           return productDataAdapter.entityToModel(productEntities.get(0),true);
        }

        return null;
    }

    @Override
    public Product getByBarreCode(String name) {
        List<TProductEntity> productEntities = productRepository.getTProductEntityByBarreCode(name);

        if(null != productEntities  && !productEntities.isEmpty()){
            return productDataAdapter.entityToModel(productEntities.get(0),true);
        }

        return null;
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
    public List<Object[]> getStoresWithProductCategory(String categorie) {
        return storeDataAdapter.entitiesToModels(productRepository.getStoresWithProductInCategory("%"+categorie+"%"));
    }

    @Override
    public List<Object[]> getStoresWithProductValue(String searchValue) {
        return storeDataAdapter.entitiesToModels(productRepository.getStoresWithProductWithValue("%"+searchValue+"%"));
    }
}

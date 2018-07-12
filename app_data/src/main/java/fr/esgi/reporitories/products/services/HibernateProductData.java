package fr.esgi.reporitories.products.services;

import entities.Category;
import entities.Product;
import fr.esgi.reporitories.products.ProductRepository;
import fr.esgi.reporitories.products.adapter.CategoryDataAdapter;
import fr.esgi.reporitories.products.adapter.ProductDataAdapter;
import fr.esgi.reporitories.products.CategoryRepository;
import fr.esgi.reporitories.products.dao.TCategoryEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.adapter.StoreDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HibernateProductData implements ProductData{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductDataAdapter productDataAdapter;

    @Autowired
    StoreDataAdapter storeDataAdapter;

    @Autowired
    CategoryDataAdapter categoryDataAdapter;

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
        return storeDataAdapter.entitiesProjectionToModels(productRepository.getStoresWithProductInCategory("%"+categorie+"%"));
    }

    @Override
    public List<Object[]> getStoresWithProductValue(String searchValue) {
        return storeDataAdapter.entitiesProjectionToModels(productRepository.getStoresWithProductWithValue("%"+searchValue+"%"));
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        if(categoryRepository.existsById(categoryId)){
            return categoryDataAdapter.entityToModel(categoryRepository.findById(categoryId).get());
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()){
            TCategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
            categoryRepository.delete(categoryEntity);
        }
    }

    @Override
    public Integer saveOrUpdateCategory(Category category) {
        TCategoryEntity categoryEntity = categoryRepository.save(categoryDataAdapter.modelToEntity(category));
        return categoryEntity.getId();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDataAdapter.entitiesToModel(categoryRepository.findAll());
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        List<Product> products = new ArrayList<>();
        if(null != categoryId && categoryRepository.existsById(categoryId)){
            TCategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
            products.addAll(productDataAdapter.entitiesToModels(productRepository.getTProductEntityByCategoriesContains(categoryEntity)));
        }
        return products;
    }

    @Override
    public Category getCategoryByName(String name) {
        if(null != name && !name.isEmpty() && null != categoryRepository.getByName(name)){
            System.out.println("ddd >> "+categoryRepository.getByName(name));
            return categoryDataAdapter.entityToModel(categoryRepository.getByName(name));
        }
        return null;
    }
}

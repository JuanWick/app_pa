package fr.esgi.reporitories.products.adapter;

import entities.Category;
import entities.Product;
import fr.esgi.reporitories.products.dao.TCategoryEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataAdapter {

    @Autowired
    ProductDataAdapter productDataAdapter;

    Category entityToModel(TCategoryEntity categoryEntity){
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        if(null != categoryEntity.getProducts()){
            List<Product> products = new ArrayList<>();
            for(TProductEntity productEntity:categoryEntity.getProducts()){
                Product product = productDataAdapter.entityToModel(productEntity, false);
                products.add(product);
            }
            category.setProducts(products);
        }
        return category;
    }

    TCategoryEntity modelToEntity(Category category){
        TCategoryEntity categoryEntity = new TCategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        if(null != category.getProducts()){
            ArrayList<TProductEntity> products = new ArrayList<>();
            for(Product product:category.getProducts()){
                TProductEntity productEntity = productDataAdapter.modelToEntity(product, false);
                products.add(productEntity);
            }
            categoryEntity.setProducts(products);
        }
        return categoryEntity;
    }
}

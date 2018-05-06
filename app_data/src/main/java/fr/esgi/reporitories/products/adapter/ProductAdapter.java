package fr.esgi.reporitories.products.adapter;

import entities.Cart;
import entities.Category;
import entities.Product;
import entities.Store;
import fr.esgi.reporitories.carts.adapter.CartAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TCategoryEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.adapter.StoreAdapter;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    CartAdapter cartAdapter;

    @Autowired
    CategoryAdapter categoryAdapter;

    @Autowired
    StoreAdapter storeAdapter;

    public Product entityToModel(TProductEntity productEntity,boolean all){
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setBarreCode(productEntity.getBarreCode());
        product.setInfo(productEntity.getInfo());
        product.setName(productEntity.getName());

        if(all && null != productEntity.getCarts()){
            List<Cart> carts = new ArrayList<>();
            for(TCartEntity cartEntity:productEntity.getCarts()){
                Cart cart = cartAdapter.entityToModel(cartEntity, false);
                carts.add(cart);
            }
            product.setCarts(carts);
        }

        if(all && null != productEntity.getCategories()){
            List<Category> categories = new ArrayList<>();
            for(TCategoryEntity categoryEntity:productEntity.getCategories()){
                Category category = categoryAdapter.entityToModel(categoryEntity);
                categories.add(category);
            }
            product.setCategories(categories);
        }

        if(all && null != productEntity.getStores()){
            List<Store> stores = new ArrayList<>();
            for(TStoreEntity storeEntity:productEntity.getStores()){
                Store store = storeAdapter.entityToModel(storeEntity);
                stores.add(store);
            }
            product.setStores(stores);
        }

        return product;
    }

    public TProductEntity modelToEntity(Product product, boolean all){
        TProductEntity productEntity = new TProductEntity();
        if(null != product.getId()){
            productEntity.setId(product.getId());
        }
        productEntity.setBarreCode(product.getBarreCode());
        productEntity.setInfo(product.getInfo());
        productEntity.setName(product.getName());
        if(all && null != product.getCarts()){
            ArrayList cartEntities = new ArrayList();
            for(Cart cart : product.getCarts()){
                TCartEntity cartEntity = cartAdapter.modelToEntity(cart, false);
                cartEntities.add(cartEntity);
            }
            productEntity.setCarts(cartEntities);
        }
        if(all && null != product.getCategories()){
            List<TCategoryEntity> categories = new ArrayList<>();
            for(Category category : product.getCategories()){
                TCategoryEntity categoryEntity = categoryAdapter.modelToEntity(category);
                categories.add(categoryEntity);
            }
            productEntity.setCategories(categories);
        }
        if(all && null != product.getStores()){
            List<TStoreEntity> stores = new ArrayList<>();
            for(Store store:product.getStores()){
                TStoreEntity storeEntity = storeAdapter.modelToEntity(store);
                stores.add(storeEntity);
            }
            productEntity.setStores(stores);

        }
        return productEntity;
    }
}

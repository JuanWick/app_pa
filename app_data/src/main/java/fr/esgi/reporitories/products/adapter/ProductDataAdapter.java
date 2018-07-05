package fr.esgi.reporitories.products.adapter;

import entities.Cart;
import entities.Category;
import entities.Product;
import entities.Store;
import fr.esgi.reporitories.carts.adapter.CartDataAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TCategoryEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.adapter.StoreDataAdapter;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.adapter.UserDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductDataAdapter {

    @Autowired
    UserDataAdapter userDataAdapter;

    @Autowired
    CartDataAdapter cartDataAdapter;

    @Autowired
    CategoryDataAdapter categoryDataAdapter;

    @Autowired
    StoreDataAdapter storeDataAdapter;

    public Product entityToModel(TProductEntity productEntity,boolean all){
        Product product = Product.builder()
                .id(productEntity.getProductId())
                .barreCode(productEntity.getBarreCode())
                .info(productEntity.getInfo())
                .name(productEntity.getName())
                .price(productEntity.getPrice()).build();

        if(all && null != productEntity.getCarts()){
            List<Cart> carts = new ArrayList<>();
            for(TCartEntity cartEntity:productEntity.getCarts()){
                Cart cart = cartDataAdapter.entityToModel(cartEntity, false);
                carts.add(cart);
            }
            product.setCarts(carts);
        }

        if(all && null != productEntity.getCategories()){
            List<Category> categories = new ArrayList<>();
            for(TCategoryEntity categoryEntity:productEntity.getCategories()){
                Category category = categoryDataAdapter.entityToModel(categoryEntity);
                categories.add(category);
            }
            product.setCategories(categories);
        }

        if(all && null != productEntity.getStores()){
            List<Store> stores = new ArrayList<>();
            for(TStoreEntity storeEntity:productEntity.getStores()){
                Store store = storeDataAdapter.entityToModel(storeEntity,false);
                stores.add(store);
            }
            product.setStores(stores);
        }

        return product;
    }

    public TProductEntity modelToEntity(Product product, boolean all){
        TProductEntity productEntity = new TProductEntity();
        if(null != product.getId()){
            productEntity.setProductId(product.getId());
        }
        productEntity.setBarreCode(product.getBarreCode());
        productEntity.setInfo(product.getInfo());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        if(all && null != product.getCarts()){
            ArrayList cartEntities = new ArrayList();
            for(Cart cart : product.getCarts()){
                TCartEntity cartEntity = cartDataAdapter.modelToEntity(cart, false);
                cartEntities.add(cartEntity);
            }
            productEntity.setCarts(cartEntities);
        }
        if(all && null != product.getCategories()){
            List<TCategoryEntity> categories = new ArrayList<>();
            for(Category category : product.getCategories()){
                TCategoryEntity categoryEntity = categoryDataAdapter.modelToEntity(category);
                categories.add(categoryEntity);
            }
            productEntity.setCategories(categories);
        }
        if(all && null != product.getStores()){
            List<TStoreEntity> stores = new ArrayList<>();
            for(Store store:product.getStores()){
                TStoreEntity storeEntity = storeDataAdapter.modelToEntity(store,false);
                stores.add(storeEntity);
            }
            productEntity.setStores(stores);

        }
        return productEntity;
    }


}

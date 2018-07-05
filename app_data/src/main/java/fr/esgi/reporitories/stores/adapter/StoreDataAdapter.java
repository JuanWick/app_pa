package fr.esgi.reporitories.stores.adapter;

import entities.Product;
import entities.Store;
import entities.User;
import fr.esgi.reporitories.products.adapter.ProductDataAdapter;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.products.services.IStoreProjection;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import fr.esgi.reporitories.users.adapter.UserDataAdapter;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreDataAdapter {
    private UserDataAdapter userDataAdapter;
    private ProductDataAdapter productDataAdapter;

    @Autowired
    public StoreDataAdapter(UserDataAdapter userDataAdapter,ProductDataAdapter productDataAdapter) {
        this.userDataAdapter = userDataAdapter;
        this.productDataAdapter = productDataAdapter;
    }

    public StoreDataAdapter() {

    }

    public Store entityToModel(TStoreEntity storeEntity, boolean all){
        Store store = Store.builder()
                .id(storeEntity.getId())
        .address(storeEntity.getAddress())
        .city(storeEntity.getCity())
        .country(storeEntity.getCountry())
        .name(storeEntity.getName())
        .zipcode(storeEntity.getZipcode())
                .latitude(storeEntity.getLatitude())
                .longitude(storeEntity.getLongitude())
        .user(null != storeEntity.getUser() ? userDataAdapter.entityToModel(storeEntity.getUser(), false):null)
                .build();

        if(all && null != storeEntity.getProducts()){
            List<Product> products = new ArrayList<>();

            for(TProductEntity productEntity:storeEntity.getProducts()){
                products.add(productDataAdapter.entityToModel(productEntity,false));
            }
            store.setProducts(products);
        }

        if(all && null != storeEntity.getLoyaltyUsers()){
            List<User> users = new ArrayList<>();

            for(TUserEntity userEntity:storeEntity.getLoyaltyUsers()){
                users.add(userDataAdapter.entityToModel(userEntity,false));
            }
            store.setUsers(users);
        }

        return store;
    }

    public TStoreEntity modelToEntity(Store store, boolean all){
        TStoreEntity storeEntity = new TStoreEntity();
        if(null != store.getId()){
            storeEntity.setId(store.getId());
        }
        storeEntity.setAddress(store.getAddress());
        storeEntity.setCity(store.getCity());
        storeEntity.setCountry(store.getCountry());
        storeEntity.setName(store.getName());
        storeEntity.setUser(userDataAdapter.modelToEntity(store.getUser(), false));
        storeEntity.setZipcode(store.getZipcode());
        storeEntity.setLatitude(store.getLatitude());
        storeEntity.setLongitude(store.getLongitude());

        if(all && null != store.getProducts()){
            List<TProductEntity> productsEntity = new ArrayList<>();

            for(Product product:store.getProducts()){
                productsEntity.add(productDataAdapter.modelToEntity(product,false));
            }
            storeEntity.setProducts(productsEntity);
        }

        if(all && null != store.getUsers()){
            List<TUserEntity> users = new ArrayList<>();

            for(User user:store.getUsers()){
                users.add(userDataAdapter.modelToEntity(user,false));
            }
            storeEntity.setLoyaltyUsers(users);
        }

        return storeEntity;
    }

    public List<Store> entitiesToModels(List<IStoreProjection> entities){
        System.out.println("entities : "+entities.size());
        List<Store> stores = new ArrayList<>();

        for(IStoreProjection entity : entities){
            TStoreEntity tStoreEntity = new TStoreEntity();
            tStoreEntity.setId(entity.getId());
            tStoreEntity.setLongitude(entity.getLongitude());
            tStoreEntity.setLatitude(entity.getLatitude());

            stores.add(entityToModel(tStoreEntity,false));
        }

        return stores;
    }
}

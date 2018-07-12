package fr.esgi.reporitories.products;

import entities.Store;
import fr.esgi.reporitories.products.dao.TCategoryEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.products.services.IStoreProjection;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<TProductEntity, Integer> {

    @Query(
            value = "SELECT store.id, store.latitude, store.longitude, pr.product_id \n" +
                    "FROM pa_data.t_store as store\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = store.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.product_id = rsp.product_id\n" +
                    "INNER JOIN pa_data.r_products_categories rpc ON rpc.product_id = pr.product_id\n" +
                    "INNER JOIN pa_data.t_category cat ON cat.id = rpc.category_id\n" +
                    "WHERE UPPER(cat.name) LIKE UPPER(:category)\n",nativeQuery = true)
    List<IStoreProjection> getStoresWithProductInCategory(@Param("category") String category);

    List<TProductEntity> getTProductEntityByName(String name);

    List<TProductEntity> getTProductEntityByBarreCode(String barreCode);

    List<TProductEntity> getTProductEntityByCategoriesContains(TCategoryEntity categoryEntity);

    @Query(
            value = "SELECT store.id, store.latitude, store.longitude, pr.product_id \n" +
                    "FROM pa_data.t_store as store\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = store.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.product_id = rsp.product_id\n" +
                    "WHERE UPPER(pr.name) LIKE UPPER(:searchValue) \n" +
                    "OR UPPER(pr.barre_code) LIKE UPPER(:searchValue) \n",nativeQuery = true)
    List<IStoreProjection> getStoresWithProductWithValue(@Param("searchValue") String searchValue);
}

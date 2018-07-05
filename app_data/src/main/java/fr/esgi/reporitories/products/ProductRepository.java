package fr.esgi.reporitories.products;

import entities.Store;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.products.services.IStoreProjection;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<TProductEntity, Integer> {

    @Query(
            value = "SELECT store.* \n" +
                    "FROM pa_data.t_store as store\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = store.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.id = rsp.product_id\n" +
                    "INNER JOIN pa_data.r_products_categories rpc ON rpc.product_id = pr.id\n" +
                    "INNER JOIN pa_data.t_category cat ON cat.id = rpc.category_id\n" +
                    "WHERE cat.name LIKE '%'+:categorie+'%'\n",nativeQuery = true)
    List<IStoreProjection> getStoresWithProductInCategory(@Param("categorie") String categorie);

    List<TProductEntity> getTProductEntityByName(String name);

    List<TProductEntity> getTProductEntityByBarreCode(String barreCode);

    @Query(
            value = "SELECT store.id, store.latitude, store.longitude \n" +
                    "FROM pa_data.t_store as store\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = store.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.id = rsp.product_id\n" +
                    "WHERE pr.name LIKE :searchValue \n" +
                    "OR pr.barre_code LIKE :searchValue \n",nativeQuery = true)
    List<IStoreProjection> getStoresWithProductWithValue(@Param("searchValue") String searchValue);
}

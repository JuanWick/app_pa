package fr.esgi.reporitories.products;

import entities.Store;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.stores.dao.TStoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<TProductEntity, Integer> {
    @Query(
            value = "SELECT * \n" +
                    "FROM Store s\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = s.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.id = rsp.product_id\n" +
                    "INNER JOIN pa_data.r_products_categories rpc ON rpc.product_id = pr.id\n" +
                    "INNER JOIN pa_data.t_category cat ON cat.id = rpc.category_id\n" +
                    "WHERE cat.name LIKE '%:categorie%'\n",nativeQuery = true)
    List<Store> getStoresCoordinatesWithProductCategory(@Param("categorie") String categorie);

    List<TProductEntity> getTProductEntityByName(String name);

    List<TProductEntity> getTProductEntityByBarreCode(String barreCode);

    @Query(
            value = "SELECT * \n" +
                    "FROM Store s\n" +
                    "INNER JOIN pa_data.r_stores_products rsp ON rsp.store_id = s.id\n" +
                    "INNER JOIN pa_data.t_product pr ON pr.id = rsp.product_id\n" +
                    "WHERE pr.name LIKE '%:searchValue%'\n" +
                    "OR pr.barreCode LIKE '%:searchValue%'\n",nativeQuery = true)
    List<Store> getStoresCoordiantesWithProductValue(@Param("searchValue") String searchValue);
}

package joinEntities;

import lombok.Data;
import entities.Product;
import entities.Store;

@Data
public class RStoreProduct {
    private Store store;
    private Product product;
    private Double priceTtc;
    private boolean available;
    private Double loyaltyPriceTtc;
    private int stock;
}

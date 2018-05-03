package joinEntities;

import entities.Cart;
import entities.Product;
import lombok.Data;
import entities.User;

@Data
public class RCartProduct {
    private Cart cart;
    private Product product;
}

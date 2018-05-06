package entities;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private String firstName;
    private List<Role> roles;
    private List<Cart> carts;
    private List<Store> stores;
    private List<Store> loyaltyStores;
    private List<Cart> sharedCarts;
}

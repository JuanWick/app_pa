package entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private boolean rgpdAccepted;
    private Date rgpdAcceptedDate;
    private String firstName;
    private List<Role> roles = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();
    private List<Store> loyaltyStores = new ArrayList<>();
    private List<Cart> sharedCarts = new ArrayList<>();
}

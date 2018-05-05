package entities;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private Integer id;
    private User user;
    private List<Product> products;
    private List<User> sharedUsers;
}

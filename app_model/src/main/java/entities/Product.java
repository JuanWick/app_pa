package entities;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer id;
    private String name;
    private String info;
    private String barreCode;
    private List<Cart> carts;
    private List<Category> categories;
    private List<Store> stores;
}

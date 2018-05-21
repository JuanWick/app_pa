package entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Product {
    private Integer id;
    private String name;
    private String info;
    private String barreCode;
    private List<Cart> carts;
    private List<Category> categories;
    private List<Store> stores;
}

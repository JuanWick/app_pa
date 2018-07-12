package entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Integer id;
    private String name;
    private List<Product> products;
}

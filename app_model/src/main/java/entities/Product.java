package entities;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String info;
    private String barreCode;
}

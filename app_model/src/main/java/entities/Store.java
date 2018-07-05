package entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class Store {
    private Integer id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private User user;
    private Double distance;
    private List<Product> products;
    private List<User> users;
}

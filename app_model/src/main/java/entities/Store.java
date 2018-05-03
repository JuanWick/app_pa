package entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Store {
    public Integer id;
    public String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private User user;
}

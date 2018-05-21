package fr.esgi.components.store.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StoreAddDto {
    private Integer id;
    @NotNull (message = "error.name.notnull")
    private String name;
    @NotNull (message = "error.address.notnull")
    @Size(min = 1, max = 255, message = "error.address.size")
    private String address;
    @NotNull (message = "error.zipcode.notnull")
    @Size(min = 1, max = 255, message = "error.zipcode.size")
    private String zipcode;
    @NotNull (message = "error.city.notnull")
    @Size(min = 1, max = 255, message = "error.city.size")
    private String city;
    @NotNull (message = "error.country.notnull")
    @Size(min = 1, max = 255, message = "error.country.size")
    private String country;
    private Double latitude;
    private Double longitude;
    @NotNull (message = "error.userId.notnull")
    private Integer userId;
}

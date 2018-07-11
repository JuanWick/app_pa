package fr.esgi.components.store.dto;

import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResultDto {
    private Integer id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private UserDto user;
    private Double distance;
    private ProductCompletDto product;
}

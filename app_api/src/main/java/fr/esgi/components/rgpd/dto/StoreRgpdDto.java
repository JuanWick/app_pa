package fr.esgi.components.rgpd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreRgpdDto {
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}

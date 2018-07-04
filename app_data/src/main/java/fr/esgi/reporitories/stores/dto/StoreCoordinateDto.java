package fr.esgi.reporitories.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreCoordinateDto {
    private Integer id;
    private Double latitude;
    private Double longitude;
}

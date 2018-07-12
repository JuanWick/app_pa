package fr.esgi.components.rgpd.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class RgpdInfosDto {
    private String name;
    private String firstName;
    private String login;
    private String email;
    private String role;
    private boolean rgpdAccepted;
    private Date rgpdAcceptedDate;
    private String latitude;
    private String longitude;
    private String password;
    private List<CartRgpdDto> ownedCarts = new ArrayList<>();
    private List<StoreRgpdDto> ownedStores = new ArrayList<>();
    private List<CartRgpdDto> sharedCarts = new ArrayList<>();
}

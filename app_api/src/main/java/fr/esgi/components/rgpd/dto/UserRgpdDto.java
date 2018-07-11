package fr.esgi.components.rgpd.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserRgpdDto {

    private String name;
    private String firstName;
}

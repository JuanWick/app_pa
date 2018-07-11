package fr.esgi.components.rgpd.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
public class UserRgpdDto {

    private String name;
    private String firstName;
    private boolean rgpdAccepted;
    private Date rgpdAcceptedDate;
}

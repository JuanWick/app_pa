package fr.esgi.components.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDetailsDto {
    private Integer id;
    private String name;
    private String firstName;
    private String mail;
    private String username;
}

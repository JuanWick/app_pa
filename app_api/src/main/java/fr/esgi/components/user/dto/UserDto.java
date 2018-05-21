package fr.esgi.components.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private Integer id;
    @NotNull(message = "error.name.notnull")
    @Size(min = 1, max = 255, message = "error.name.size")
    private String name;
    @NotNull (message = "error.firstName.notnull")
    @Size(min = 1, max = 255, message = "error.firstName.size")
    private String firstName;
}

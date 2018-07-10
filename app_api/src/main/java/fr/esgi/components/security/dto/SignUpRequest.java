package fr.esgi.components.security.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class SignUpRequest {
    @NotNull
    @Size(min = 1, max = 255 , message = "error.firstname.size")
    private String firstname;

    @NotNull
    @Size(min = 1, max = 255 , message = "error.name.size")
    private String name;

    @NotNull
    @Size(min = 3, max = 15 , message = "error.username.size")
    private String username;

    @NotNull
    @Size(max = 40  , message = "error.email.size")
    private String email;

    @NotNull
    @Size(min = 6, max = 20 , message = "error.password.size")
    private String password;

    @NotNull
    private Integer roleId;
}

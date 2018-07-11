package fr.esgi.components.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignInDto {
    @NotNull
    @Size(min = 3, max = 15 , message = "error.username.size")
    String userNameOrEmail;
    @NotNull
    @Size(min = 6, max = 20 , message = "error.password.size")
    String password;
}

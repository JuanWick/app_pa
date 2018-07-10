package fr.esgi.components.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInDto {
    String accessToken;
    String tokenType;
}

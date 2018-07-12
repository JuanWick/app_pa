package fr.esgi.components.security.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Integer id;

    public JwtAuthenticationResponse(String accessToken,Integer userId) {
        this.accessToken = accessToken;
        this.id = userId;
    }
}

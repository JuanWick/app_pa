package fr.esgi.components.security.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Integer id;
    private String role;

    public JwtAuthenticationResponse(String accessToken,Integer userId,String role) {
        this.accessToken = accessToken;
        this.id = userId;
        this.role = role;
    }
}

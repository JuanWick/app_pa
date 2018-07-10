package fr.esgi.components.security.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private Boolean success;
    private Integer userId;

    public RegisterResponse(Boolean success, Integer userId) {
        this.success = success;
        this.userId = userId;
    }
}

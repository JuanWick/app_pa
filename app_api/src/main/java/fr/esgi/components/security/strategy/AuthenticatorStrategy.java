package fr.esgi.components.security.strategy;

import fr.esgi.components.security.dto.UserPrincipal;

import java.util.Optional;

public interface AuthenticatorStrategy {

    void save(UserPrincipal userForAuth);
    Optional<String> login(String username, String password);
}

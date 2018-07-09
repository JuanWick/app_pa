package fr.esgi.components.security.strategy;

import fr.esgi.components.security.dto.UserPrincipal;

import java.util.Optional;

public class SpringAuthenticatorStrategy implements AuthenticatorStrategy {
    @Override
    public void save(UserPrincipal userForAuth) {

    }

    @Override
    public Optional<String> login(String username, String password) {
        return null;
    }
}

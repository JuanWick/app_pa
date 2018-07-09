package fr.esgi.exception;

public class AuthenticationException extends RuntimeException  {
    public AuthenticationException() {
        super("Erreur : authentification impossible");
    }
}

package fr.esgi.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("Erreur : Role inconnu");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}

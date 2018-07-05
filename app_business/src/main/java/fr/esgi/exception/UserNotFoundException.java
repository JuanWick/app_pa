package fr.esgi.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Erreur : Utilisateur inconnu");
    }
}

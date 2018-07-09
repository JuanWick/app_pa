package fr.esgi.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("Erreur : Nom d'utilisateur déjà existant");
    }

}

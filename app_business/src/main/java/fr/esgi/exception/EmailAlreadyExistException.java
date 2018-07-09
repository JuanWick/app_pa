package fr.esgi.exception;

public class EmailAlreadyExistException extends RuntimeException  {
    public EmailAlreadyExistException() {
        super("Erreur : Un compte possédant ce mail existe déjà impossible");
    }
}

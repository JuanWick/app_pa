package fr.esgi.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Erreur : La catégorie existe déjà");
    }
}
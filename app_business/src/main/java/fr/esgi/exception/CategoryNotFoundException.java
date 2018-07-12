package fr.esgi.exception;

public class CategoryNotFoundException extends RuntimeException  {
    public CategoryNotFoundException() {
        super("Erreur : categorie inconnu");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}

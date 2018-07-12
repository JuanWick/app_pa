package fr.esgi.exception;

public class CategoryUsedException extends RuntimeException  {
    public CategoryUsedException() {
        super("Erreur : la category est utilisée");
    }
}
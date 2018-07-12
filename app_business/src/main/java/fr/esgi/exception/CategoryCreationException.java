package fr.esgi.exception;

public class CategoryCreationException extends RuntimeException  {
    public CategoryCreationException() {
        super("Erreur : Création de la catégorie impossible");
    }
}

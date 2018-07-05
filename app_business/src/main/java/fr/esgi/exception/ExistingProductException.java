package fr.esgi.exception;

public class ExistingProductException extends RuntimeException {
    public ExistingProductException() {
        super("Erreur : produit similaire déjà existant");
    }
}

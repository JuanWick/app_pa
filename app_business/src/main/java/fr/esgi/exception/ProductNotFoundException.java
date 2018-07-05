package fr.esgi.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Erreur : Produit inconnu");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}

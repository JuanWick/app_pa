package fr.esgi.exception;

public class CartNotFoundException extends RuntimeException  {
    public CartNotFoundException() {
        super("Erreur : panier inconnu");
    }
}

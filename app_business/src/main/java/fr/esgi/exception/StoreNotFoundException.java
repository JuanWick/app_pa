package fr.esgi.exception;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException() {
        super("Erreur : Magasin inconnu");
    }
}

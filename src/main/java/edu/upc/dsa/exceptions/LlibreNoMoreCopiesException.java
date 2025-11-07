package edu.upc.dsa.exceptions;

public class LlibreNoMoreCopiesException extends RuntimeException {
    public LlibreNoMoreCopiesException(String id) {
        super("Libro con id " + id + " no tiene más copias disponibles");
    }
}

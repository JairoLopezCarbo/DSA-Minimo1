package edu.upc.dsa.exceptions;

public class AvionNotFoundException extends RuntimeException {
    public AvionNotFoundException(String id) {
        super("Avion con id " + id + " no encontrado");
    }
}

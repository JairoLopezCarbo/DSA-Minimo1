package edu.upc.dsa.exceptions;

public class VueloNotFoundException extends RuntimeException {
    public VueloNotFoundException(String id) {
        super("Vuelo con id " + id + " no encontrado");
    }
}

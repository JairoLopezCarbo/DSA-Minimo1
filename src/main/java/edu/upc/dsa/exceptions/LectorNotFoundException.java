package edu.upc.dsa.exceptions;

public class LectorNotFoundException extends RuntimeException {
    public LectorNotFoundException(String id) {
        super("Lector con id " + id + " no encontrado");
    }
}

package edu.upc.dsa.exceptions;

public class LlibreNotFoundException extends RuntimeException {
    public LlibreNotFoundException(String id) {
        super("Llibre amb id " + id + " no trobat");
    }
}

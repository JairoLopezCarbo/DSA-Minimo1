package edu.upc.dsa.exceptions;

public class VueloAlreadyExistException extends RuntimeException {
    public VueloAlreadyExistException(String id) {
        super("Vuelo con id " + id + " ya existe");
    }
}

package edu.upc.dsa.exceptions;

public class AvionAlreadyExistException extends RuntimeException {
    public AvionAlreadyExistException(String id) {
        super("Avion con id " + id + " ya existe");
    }
}

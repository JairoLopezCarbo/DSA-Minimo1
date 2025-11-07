package edu.upc.dsa.exceptions;

public class MagatzemEmptyErrorException extends RuntimeException {
    public MagatzemEmptyErrorException() {
        super("No quedan libros en el magatzem");
    }
}

package edu.upc.dsa.models;

public class Llibre {

    String id;
    int ISBN;
    String titol;
    String editorial;
    int anyPublicacio;
    int edicio;
    String autor;
    String tematica;

    public Llibre() {}

    public Llibre(String id, int ISBN, String titol, String editorial, int anyPublicacio, int edicio, String autor, String tematica) {
        this.id = id;
        this.ISBN = ISBN;
        this.titol = titol;
        this.editorial = editorial;
        this.anyPublicacio = anyPublicacio;
        this.edicio = edicio;
        this.autor = autor;
        this.tematica = tematica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(int anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public int getEdicio() {
        return edicio;
    }

    public void setEdicio(int edicio) {
        this.edicio = edicio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
}

package edu.upc.dsa.models;

public class Prestec {

    String id;
    String idLector;
    String idLlibre;
    String dataPrestec;
    String dataDevolucio;

    public Prestec() {}

    public Prestec(String id, String idLector, String idLlibre, String dataPrestec, String dataDevolucio) {
        this.id = id;
        this.idLector = idLector;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataDevolucio = dataDevolucio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLector() {
        return idLector;
    }

    public void setIdLector(String idLector) {
        this.idLector = idLector;
    }

    public String getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(String idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(String dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public String getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(String dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }
}

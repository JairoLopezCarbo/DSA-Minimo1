package edu.upc.dsa.models;

public class Avion {

    String id;
    String empresa;
    String modelo;

    public Avion() {}

    public Avion(String id, String empresa, String modelo) {
        this.id = id;
        this.empresa = empresa;
        this.modelo = modelo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}

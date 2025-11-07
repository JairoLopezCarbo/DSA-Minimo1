package edu.upc.dsa.models;

public class Lector {

    String id;
    String nom;
    String cognom;
    String dni;
    String naixement;
    String llocNaixement;
    String adreca;

    public Lector() {}

    public Lector(String id, String nom, String cognom, String dni, String naixement, String llocNaixement, String adreca) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
        this.naixement = naixement;
        this.llocNaixement = llocNaixement;
        this.adreca = adreca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNaixement() {
        return naixement;
    }

    public void setNaixement(String naixement) {
        this.naixement = naixement;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getLlocNaixement() {
        return llocNaixement;
    }

    public void setLlocNaixement(String llocNaixement) {
        this.llocNaixement = llocNaixement;
    }


}

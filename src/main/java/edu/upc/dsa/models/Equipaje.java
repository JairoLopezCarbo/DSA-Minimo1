package edu.upc.dsa.models;

public class Equipaje {
    String id;
    String owner; // nombre del usuario

    public Equipaje() {}

    public Equipaje(String id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

}

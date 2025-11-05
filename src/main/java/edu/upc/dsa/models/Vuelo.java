package edu.upc.dsa.models;

import java.util.Stack;

public class Vuelo {
    String idVuelo;
    String horaSalida;
    String horaLlegada;
    Avion avionAsignado;
    String origen;
    String destino;
    Stack<Equipaje> equipajes;

    public Vuelo() {}

    public Vuelo(String id, String horaSalida, String horaLlegada, Avion avionAsignado, String origen, String destino) {
        this.idVuelo = id;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.avionAsignado = avionAsignado;
        this.origen = origen;
        this.destino = destino;
        this.equipajes = new Stack<>();
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Avion getAvionAsignado() {
        return avionAsignado;
    }

    public void setAvionAsignado(Avion avionAsignado) {
        this.avionAsignado = avionAsignado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void addEquipaje(Equipaje equipaje) {
        this.equipajes.add(equipaje);
    }

    public Stack<Equipaje> getEquipajes() {
        return equipajes;
    }
}

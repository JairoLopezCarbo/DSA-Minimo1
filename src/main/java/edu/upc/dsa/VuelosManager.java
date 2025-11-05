package edu.upc.dsa;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Equipaje;
import edu.upc.dsa.models.Vuelo;

import java.util.List;

public interface VuelosManager {

    public void addAvion(String id, String empresa, String modelo);


    public void addVuelo(String id, String horaSalida, String horaLlegada, Avion avionAsignado, String origen, String destino);

    public void addVuelo(Vuelo vuelo);

    public void FacturarEquipaje(String idVuelo, Equipaje equipaje);

    public List<Equipaje> devolverEquipaje(String idVuelo);

    public void deleteVuelo(String idVuelo);

    public Vuelo updateVuelo(Vuelo v);
}

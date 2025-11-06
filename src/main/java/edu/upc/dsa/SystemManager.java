package edu.upc.dsa;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Equipaje;
import edu.upc.dsa.models.Vuelo;

import java.util.List;

public interface SystemManager {

    public int addAvion(Avion avion);

    public int addVuelo(Vuelo vuelo);

    public int facturarEquipaje(String idVuelo, Equipaje equipaje);

    public List<Equipaje> devolverEquipaje(String idVuelo);

}

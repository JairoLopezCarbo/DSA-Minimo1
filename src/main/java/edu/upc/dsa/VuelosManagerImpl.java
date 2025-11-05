package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Equipaje;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class VuelosManagerImpl implements VuelosManager {

    private static VuelosManagerImpl instance;
    private List<Vuelo> vuelos;
    private List<Avion> aviones;
    final static Logger logger = Logger.getLogger(VuelosManagerImpl.class);

    private VuelosManagerImpl() {
        vuelos = new ArrayList<Vuelo>();
        aviones = new ArrayList<Avion>();
    }

    public static VuelosManagerImpl getInstance() {
        if (instance == null) {
            instance = new VuelosManagerImpl();
        }
        return instance;
    }

    @Override
    public void addAvion(String id, String empresa, String modelo) {
        logger.info("Añadiendo avion con id: " + id + " empresa: " + empresa + " modelo: " + modelo);
        for(Avion av : aviones) {
            if(av.getId().equals(id)) {
                logger.warn("Warning: Avion con mismo id, no se puede añadir");
                logger.info("Cambiando propiedades del avion: " + av);
                av.setModelo(modelo);
                av.setEmpresa(empresa);

            }
        }
        aviones.add(new Avion(id, empresa, modelo));
    }

    @Override
    public void addVuelo(String id, String horaSalida, String horaLlegada, Avion avionAsignado, String origen, String destino) {
        logger.info("Añadiendo vuelos con id: " + id);
        if (aviones.contains(avionAsignado)) {
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getIdVuelo().equals(id)) {
                    vuelo.setHoraSalida(horaSalida);
                    vuelo.setHoraLlegada(horaLlegada);
                    vuelo.setAvionAsignado(avionAsignado);
                    vuelo.setOrigen(origen);
                    vuelo.setDestino(destino);
                    logger.info("Vuelo con ID " + id + " actualizado.");
                    return;
                }
            }
            this.addVuelo((new Vuelo(id, horaSalida, horaLlegada, avionAsignado, origen, destino)));
        }
        else{
            logger.error("Error: El avion asignado al vuelo no existe");
        }

    }

    @Override
    public void addVuelo(Vuelo vuelo) {
        logger.info("Añadiendo vuelo con id: " + vuelo.getIdVuelo());
        vuelos.add(vuelo);
    }

    @Override
    public void FacturarEquipaje(String idVuelo, Equipaje equipaje) {
        logger.info("Facturando equipaje con id: " + idVuelo);
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdVuelo().equals(idVuelo)) {
                vuelo.getEquipajes().push(equipaje);
                logger.info("Equipaje con ID " + equipaje.getId() + " facturado en el vuelo " + idVuelo);
                return;
            }
        }
        logger.error("Error: El vuelo no existe, no se puede facturar el equipaje");
    }

    @Override
    public List<Equipaje> devolverEquipaje(String idVuelo) {
        logger.info("Devolver equipaje con id: " + idVuelo);
        List<Equipaje> equipajeDescargado = new ArrayList<>();
        for(Vuelo vuelo : vuelos) {
            if(vuelo.getIdVuelo().equals(idVuelo)) {
                Stack<Equipaje> equipajes = new Stack<Equipaje>();
                equipajes = vuelo.getEquipajes();

                equipajeDescargado.addAll(equipajes);
                return equipajeDescargado;
            }
        }
        logger.error("Error: El vuelo con id " + idVuelo + " no se ha encontrado");
        return equipajeDescargado;
    }

    @Override
    public void deleteVuelo(String idVuelo) {
        logger.info("Eliminando vuelo con id: " + idVuelo);
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdVuelo().equals(idVuelo)) {
                vuelos.remove(vuelo);
            }
        }
    }

    @Override
    public Vuelo updateVuelo(Vuelo v) {
        logger.info("Actualizando vuelo: " + v);
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getIdVuelo().equals(v.getIdVuelo())) {
                vuelo.setHoraSalida(v.getHoraSalida());
                vuelo.setHoraLlegada(v.getHoraLlegada());
                vuelo.setOrigen(v.getOrigen());
                vuelo.setDestino(v.getDestino());
                vuelo.setAvionAsignado(v.getAvionAsignado());

                return vuelo;
            }
        }
        logger.warn("El vuelo " + v + " no existe");
        return null;
    }

    public Avion getAvion(String id) {
        logger.info("Obteniendo avion con id: " + id);
        for (Avion av : aviones) {
            if (av.getId().equals(id)) {
                return av;
            }
        }
        logger.error("Error: El avion con id " + id + " no existe");
        return null;
    }

    public Vuelo getVuelo(String id) {
        logger.info("Obteniendo vuelo con id: " + id);
        for (Vuelo vuelo : vuelos) {
            if(vuelo.getIdVuelo().equals(id)) {
                return vuelo;
            }
        }
        logger.error("Error: El vuelo con id " + id + " no existe");
        return null;
    }

    public int sizeVuelos() {
        return vuelos.size();
    }

    public List<Vuelo> getVuelos() {
        logger.info("Obteniendo todos las vuelos");
        return this.vuelos;
    }


}

package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.exceptions.*;
import org.apache.log4j.Logger;


import java.util.*;
//add(E e)
//get(int index)
//remove(int index)


//push(E item)
//pop()
//peek() devuelve objeto tope sin sacarlo
//empty() boolean
//search(Object o) devuelve posicion del objeto o -1 si no existe

//put(K key, V value)
//get(Object key)
//containsKey(Object key)
//remove(Object key)

//import java.util.LinkedList;
// add(E e)        -> agrega un elemento al final de la cola (lanza excepción si falla)
// remove()        -> elimina y devuelve el primer elemento (lanza excepción si la cola está vacía)
// poll()          -> elimina y devuelve el primer elemento (devuelve null si la cola está vacía)
// peek()          -> devuelve el primer elemento sin eliminarlo (devuelve null si está vacía)





public class SystemManagerImpl implements SystemManager {

    private static SystemManagerImpl instance;
    final static Logger logger = Logger.getLogger(SystemManagerImpl.class);

    private Map<String, Vuelo> vuelos;
    private Map<String, Avion> aviones;

    public static SystemManagerImpl getInstance() {
        if (instance == null) {
            instance = new SystemManagerImpl();
        }
        return instance;
    }

    private SystemManagerImpl() {
        vuelos = new HashMap<String, Vuelo>();
        aviones = new HashMap<String, Avion>();
    }

    @Override
    public int addAvion(Avion newAvion) {
        String id = newAvion.getId();
        try {
            if (aviones.containsKey(id)) throw new AvionAlreadyExistException(id);
        }
        catch (Exception e) {
            logger.error("Avion " + id + " ya existe " + e.getMessage());
            return -1;
        }
        aviones.put(id, newAvion);
        logger.info("Avion " + id + " añadido correctamente");
        return 1;
    }

    @Override
    public int addVuelo(Vuelo newVuelo) {
        String id = newVuelo.getIdVuelo();
        try {
            if (vuelos.containsKey(id)) throw new VueloAlreadyExistException(id);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }

        vuelos.put(id, newVuelo);
        logger.info("Vuelo " + id + " añadido correctamente");
        return 1;
    }

    @Override
    public int facturarEquipaje(String idVuelo, Equipaje equipaje) {
        try {
            if (!vuelos.containsKey(idVuelo)) throw new VueloNotFoundException(idVuelo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }
        vuelos.get(idVuelo).addEquipaje(equipaje);
        logger.info("Equipaje " + equipaje.getId() + " facturado correctamente en vuelo " + idVuelo);
        return 1;
    }

    @Override
    public List<Equipaje> devolverEquipaje(String idVuelo) {
        logger.info("Devolver equipaje con id: " + idVuelo);
        List<Equipaje> equipajeDescargado = new ArrayList<Equipaje>();
        try {
            if (!vuelos.containsKey(idVuelo)) throw new VueloNotFoundException(idVuelo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        Stack<Equipaje> equipajes = vuelos.get(idVuelo).getEquipajes();
        while (!equipajes.isEmpty()) {
            Equipaje equipaje = equipajes.pop();
            equipajeDescargado.add(equipaje);
            logger.info("Equipaje " + equipaje.getId() + " descargado del vuelo " + idVuelo);
        }
        return equipajeDescargado;
    }


    public Avion getAvion(String idAvion) {
        try {
            if (!aviones.containsKey(idAvion)) throw new AvionAlreadyExistException(idAvion);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        Avion avion = aviones.get(idAvion);
        logger.info("Obteniendo avion con id: " + idAvion);

        return avion;
    }

    public Vuelo getVuelo(String idVuelo) {
        try {
            if (!vuelos.containsKey(idVuelo)) throw new VueloNotFoundException(idVuelo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        Vuelo vuelo = vuelos.get(idVuelo);
        logger.info("Obteniendo avion con id: " + idVuelo);

        return vuelo;

    }

    public int sizeVuelos() {
        return vuelos.size();
    }

    public void clear() {
        logger.info("Limpiando la lista de vuelos y aviones");
        this.vuelos = new HashMap<String, Vuelo>();
        this.aviones = new HashMap<String, Avion>();
    }


}

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





public class BibliotecaManagerImpl implements BibliotecaManager {

    private static BibliotecaManagerImpl instance;
    final static Logger logger = Logger.getLogger(BibliotecaManagerImpl.class);

    private Map<String, Lector> lectors;
    private Stack<Llibre> pilaActual;
    private LinkedList<Stack<Llibre>> emmagetzamentLlibres;
    private Map<String, Integer> catalegLlibres;
    private Map<String, List<Prestec>> prestecs;

    public static BibliotecaManagerImpl getInstance() {
        if (instance == null) {
            instance = new BibliotecaManagerImpl();
        }
        return instance;
    }

    private BibliotecaManagerImpl() {
        this.lectors = new HashMap<String, Lector>();
        this.emmagetzamentLlibres = new LinkedList<Stack<Llibre>>();
        this.pilaActual = new Stack<Llibre>();
        this.emmagetzamentLlibres.add(this.pilaActual);
        this.catalegLlibres = new HashMap<String, Integer>();
        this.prestecs = new HashMap<String, List<Prestec>>();
    }

    @Override
    public int afegirLector(Lector lector) {
        String id = lector.getId();
        boolean alreadyExists = this.lectors.containsKey(id);
        lectors.put(id, lector);

        if (alreadyExists) {
            logger.info("Lector " + id + " acualizado correctamente");
            return 0;
        }
        logger.info("Lector " + id + " añadido correctamente");
        return 1;
    }

    @Override
    public int emmagatzemarLlibre(Llibre llibre) {
        int returnValue = 0;
        //Check if pilaActual is full, if so create a new one and add it to emmagetzamentLlibres return 1
        if (this.pilaActual.size() == 10) {
            this.pilaActual = new Stack<Llibre>();
            logger.info("Pila actual llena, creando nueva pila de libros");
            this.emmagetzamentLlibres.add(this.pilaActual);
            returnValue = 1;
        }
        this.pilaActual.push(llibre);
        logger.info("Libro " + llibre.getId() + " guardado correctamente");
        return returnValue;
    }

    @Override
    public Llibre catalogarLlibre() {
        Stack<Llibre> pilaMagatzem = this.emmagetzamentLlibres.peek();

        if (pilaMagatzem == null || pilaMagatzem.isEmpty()) {
            logger.error("No hay libros en el almacén para catalogar");
            return null;
        }

        Llibre llibre = pilaMagatzem.pop();
        if (!this.catalegLlibres.containsKey(llibre.getId())) {
            this.catalegLlibres.put(llibre.getId(), 1);
        } else {
            int count = this.catalegLlibres.get(llibre.getId());
            this.catalegLlibres.put(llibre.getId(), count + 1);
        }
        logger.info("Libro " + llibre.getId() + " catalogado correctamente");

        //Si esta vacia y no es la unica pila (si es la ultima esta siendo usada para pilaActual), eliminarla
        if (pilaMagatzem.isEmpty() && this.emmagetzamentLlibres.size() > 1) {
            this.emmagetzamentLlibres.pop();
            logger.info("Pila de libros vacía eliminada del almacén");
        }
        return llibre;
    }

    @Override
    public int prestarLlibre(Prestec prestec) {

        try {
            if (!this.catalegLlibres.containsKey(prestec.getIdLlibre())) throw new LlibreNotFoundException(prestec.getIdLlibre());
            if (!this.lectors.containsKey(prestec.getIdLector())) throw new LectorNotFoundException(prestec.getIdLector());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }

        int count = this.catalegLlibres.get(prestec.getIdLlibre());
        try{
            if (count <= 0) {
                throw new LlibreNotFoundException(prestec.getIdLlibre());
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return -2;
        }
        this.catalegLlibres.put(prestec.getIdLlibre(), count - 1);

        if (!this.prestecs.containsKey(prestec.getIdLector())) {
            List<Prestec> llistaPrestecs = new ArrayList<Prestec>();
            llistaPrestecs.add(prestec);
            this.prestecs.put(prestec.getIdLector(), llistaPrestecs);
        } else {
            List<Prestec> llistaPrestecs = this.prestecs.get(prestec.getIdLector());
            llistaPrestecs.add(prestec);
        }
        logger.info("Libro " + prestec.getIdLlibre() + " prestado correctamente al lector " + prestec.getIdLector());
        return 1;
    }

    @Override
    public List<Prestec> consutarPrestecs(String idLector) {
        try {
            if (!this.lectors.containsKey(idLector)) throw new LectorNotFoundException(idLector);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        List<Prestec> llistaPrestecs = this.prestecs.get(idLector);
        int size = 0;
        if (llistaPrestecs == null){
            logger.info("Un total de "+ 0 +" prestamos tiene el lector con id: " + idLector);
            return new ArrayList<Prestec>();
        }
;
        logger.info("Un total de "+ llistaPrestecs.size() +" prestamos tiene el lector con id: " + idLector);
        return llistaPrestecs;
    }

    public void clear() {
        logger.info("Limpiando Biblioteca");
        this.lectors.clear();
        this.emmagetzamentLlibres.clear();
        this.pilaActual = new Stack<Llibre>();
        this.emmagetzamentLlibres.add(this.pilaActual);
        this.catalegLlibres.clear();
        this.prestecs.clear();
    }


}

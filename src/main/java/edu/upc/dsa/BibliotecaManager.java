package edu.upc.dsa;

import edu.upc.dsa.models.*;
import java.util.List;

public interface BibliotecaManager {

    public int afegirLector(Lector lector);
    //actualizar si mismo id

    public int emmagatzemarLlibre(Llibre llibre);

    public Llibre catalogarLlibre();
    //error si no quedan

    public int prestarLlibre(Prestec prestec);
    //error si no existeix el llibre o el lector

    public List<Prestec> consutarPrestecs(String idLector);
}

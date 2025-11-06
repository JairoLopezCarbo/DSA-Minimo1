package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.models.*;

import java.util.*;

public class SystemManagerTest {
    SystemManagerImpl manager;

    @Before
    public void setUp() {
        manager = SystemManagerImpl.getInstance();
        Avion avion1 = new Avion("AAA1", "Vueling", "Boeing 737");
        Avion avion2 = new Avion("AAA2", "Ryanair", "Boeing 747");
        Avion avion3 = new Avion("AAA3", "EasyJet", "Boeing 767");

        manager.addAvion(avion1);
        manager.addAvion(avion2);
        manager.addAvion(avion3);

        Vuelo vuelo1 = new Vuelo("VUELO1", "10:00", "12:00", avion1, "Barcelona", "Madrid");
        Vuelo vuelo2 = new Vuelo("VUELO2", "13:00", "23:00", avion2, "China", "Francia");
        Vuelo vuelo3 = new Vuelo("VUELO3", "16:00", "00:00", avion3, "Senegal", "Australia");

        manager.addVuelo(vuelo1);
        manager.addVuelo(vuelo2);
        manager.addVuelo(vuelo3);
    }

    @After
    public void tearDown() {
        this.manager.clear();
    }

    @Test
    public void testAddAvion() {
        Assert.assertEquals("Vueling", manager.getAvion("AAA1").getEmpresa());
        Assert.assertEquals("Boeing 747", manager.getAvion("AAA2").getModelo());

        //add avion that already exists
        Avion avion4 = new Avion("AAA1", "Iberia", "Airbus A320");
        Assert.assertEquals(-1, manager.addAvion(avion4));

        //get avion that does not exist
        Assert.assertNull(manager.getAvion("AAA4"));
    }

    @Test
    public void testAddVuelo() {
        Assert.assertEquals("10:00", manager.getVuelo("VUELO1").getHoraSalida());
        Assert.assertEquals("Francia", manager.getVuelo("VUELO2").getDestino());

        //add vuelo that already exists
        Avion avion4 = new Avion("AAA4", "Iberia", "Airbus A320");
        Vuelo vuelo4 = new Vuelo("VUELO1", "18:00", "20:00", avion4, "Valencia", "Sevilla");
        Assert.assertEquals(-1, manager.addVuelo(vuelo4));

        //get vuelo that does not exist
        Assert.assertNull(manager.getVuelo("VUELO4"));

    }

    @Test
    public void FacturarEquipaje() {

        Equipaje equipaje1 = new Equipaje("EQUIP1", "Pepe");
        Equipaje equipaje2 = new Equipaje("EQUIP2", "Juan");


        Vuelo vuelo1 = manager.getVuelo("VUELO1");

        manager.facturarEquipaje("VUELO1", equipaje1);
        manager.facturarEquipaje("VUELO1", equipaje2);

        Assert.assertEquals(2, vuelo1.getEquipajes().size());
    }

    @Test
    public void DevolverEquipaje() {

        Equipaje equip3 = new Equipaje("EQUIP3", "Nacho");
        Equipaje equip4 = new Equipaje("EQUIP4", "Verga");

        // Cogemos otro vuelo porque el vuelo 1 ya esta usado
        manager.facturarEquipaje("VUELO2", equip3);
        manager.facturarEquipaje("VUELO2", equip4);


        Vuelo vuelo1 = manager.getVuelo("VUELO2");
        Assert.assertEquals(2, vuelo1.getEquipajes().size());

        List<Equipaje> equipajeDevuelto = manager.devolverEquipaje("VUELO2");

        Assert.assertEquals("EQUIP4", equipajeDevuelto.get(0).getId());
        Assert.assertEquals("EQUIP3", equipajeDevuelto.get(1).getId());
    }
}

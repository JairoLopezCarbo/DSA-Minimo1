package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.models.Equipaje;
import edu.upc.dsa.models.Vuelo;

import java.util.List;

public class VuelosManagerTest {
    VuelosManagerImpl manager;

    @Before
    public void setUp() {
        manager = VuelosManagerImpl.getInstance();
        manager.addAvion("AAA1", "Vueling", "Boeing 737");
        manager.addAvion("AAA2", "Ryanair", "Boeing 747");
        manager.addAvion("AAA3", "EasyJet", "Boeing 767");

        manager.addVuelo("VUELO1", "10:00", "12:00",manager.getAvion("AAA1"), "Barcelona", "Madrid");
        manager.addVuelo("VUELO2", "13:00", "23:00",manager.getAvion("AAA2"), "China", "Francia");
        manager.addVuelo("VUELO3", "16:00", "00:00",manager.getAvion("AAA3"), "Senegal", "Australia");
    }

    @After
    public void tearDown() {
        this.manager = null;
    }

    @Test
    public void testAddAvion() {
        Assert.assertEquals("Vueling", manager.getAvion("AAA1").getEmpresa());
        Assert.assertEquals("Ryanair", manager.getAvion("AAA2").getEmpresa());
        Assert.assertEquals("EasyJet", manager.getAvion("AAA3").getEmpresa());

        Assert.assertEquals("Boeing 737", manager.getAvion("AAA1").getModelo());
        Assert.assertEquals("Boeing 747", manager.getAvion("AAA2").getModelo());
        Assert.assertEquals("Boeing 767", manager.getAvion("AAA3").getModelo());

        // Comprobamos tambien si el cambio de datos se hace
        manager.addAvion("AAA1", "Fly Emirates", "Boeing 787");
        Assert.assertEquals("Boeing 787", manager.getAvion("AAA1").getModelo());
        Assert.assertEquals("Fly Emirates", manager.getAvion("AAA1").getEmpresa());
    }

    @Test
    public void addVuelo() {

        Vuelo vuelo1 = manager.getVuelo("VUELO1");
        Assert.assertNotNull(vuelo1);
        Assert.assertEquals("VUELO1", vuelo1.getIdVuelo());
        Assert.assertEquals("10:00", vuelo1.getHoraSalida());
        Assert.assertEquals("12:00", vuelo1.getHoraLlegada());
        Assert.assertEquals("Barcelona", vuelo1.getOrigen());
        Assert.assertEquals("Madrid", vuelo1.getDestino());
        Assert.assertEquals("Vueling", vuelo1.getAvionAsignado().getEmpresa());


        Vuelo vuelo2 = manager.getVuelo("VUELO2");
        Assert.assertNotNull(vuelo2);
        Assert.assertEquals("VUELO2", vuelo2.getIdVuelo());
        Assert.assertEquals("13:00", vuelo2.getHoraSalida());
        Assert.assertEquals("23:00", vuelo2.getHoraLlegada());
        Assert.assertEquals("China", vuelo2.getOrigen());
        Assert.assertEquals("Francia", vuelo2.getDestino());
        Assert.assertEquals("Ryanair", vuelo2.getAvionAsignado().getEmpresa());


        Vuelo vuelo3 = manager.getVuelo("VUELO3");
        Assert.assertNotNull(vuelo3);
        Assert.assertEquals("VUELO3", vuelo3.getIdVuelo());
        Assert.assertEquals("16:00", vuelo3.getHoraSalida());
        Assert.assertEquals("00:00", vuelo3.getHoraLlegada());
        Assert.assertEquals("Senegal", vuelo3.getOrigen());
        Assert.assertEquals("Australia", vuelo3.getDestino());
        Assert.assertEquals("EasyJet", vuelo3.getAvionAsignado().getEmpresa());

    }

    @Test
    public void FacturarEquipaje() {

        Equipaje equipaje1 = new Equipaje("EQUIP1", "Pepe");
        Equipaje equipaje2 = new Equipaje("EQUIP2", "Juan");


        Vuelo vuelo1 = manager.getVuelo("VUELO1");

        manager.FacturarEquipaje("VUELO1", equipaje1);
        manager.FacturarEquipaje("VUELO1", equipaje2);

        Assert.assertEquals(2, vuelo1.getEquipajes().size());
    }

    @Test
    public void DevolverEquipaje() {

        Equipaje equip3 = new Equipaje("EQUIP3", "Nacho");
        Equipaje equip4 = new Equipaje("EQUIP4", "Verga");

        // Cogemos otro vuelo porque el vuelo 1 ya esta usado
        manager.FacturarEquipaje("VUELO2", equip3);
        manager.FacturarEquipaje("VUELO2", equip4);


        Vuelo vuelo1 = manager.getVuelo("VUELO2");
        Assert.assertEquals(2, vuelo1.getEquipajes().size());

        List<Equipaje> equipajeDevuelto = manager.devolverEquipaje("VUELO2");

        Assert.assertEquals(2, equipajeDevuelto.size());
    }
}

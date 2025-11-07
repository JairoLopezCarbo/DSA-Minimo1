package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.models.*;

import java.util.*;

public class BibliotecaManagerTest {
    BibliotecaManagerImpl manager;

    @Before
    public void setUp() {
        manager = BibliotecaManagerImpl.getInstance();
        Lector lector1 = new Lector("L1", "Juan", "Perez", "12345678A", "01/01/1990", "Madrid", "Calle Falsa 123");
        Lector lector2 = new Lector("L2", "Ana", "Garcia", "87654321B", "02/02/1985", "Barcelona", "Avenida Siempre Viva 456");
        manager.afegirLector(lector1);
        manager.afegirLector(lector2);

        Llibre llibre1 = new Llibre("ISBN1", 11111, "El Quijote", "Editorial1", 1605, 1,"Miguel de Cervantes", "Novela");
        Llibre llibre2 = new Llibre("ISBN2", 22222, "Cien Años de Soledad", "Editorial2", 1234, 2,"Gabriel Garcia Marquez", "asdasdasf");
        Llibre llibre3 = new Llibre("ISBN3", 33333, "1984", "Editorial3", 5678, 3,"George Orwell", "asdasd");
        Llibre llibre4 = new Llibre("ISBN4", 44444, "To Kill a Mockingbird", "Editorial4", 9101, 4,"Harper Lee", "kasdasd");
        Llibre llibre5 = new Llibre("ISBN5", 55555, "The Great Gatsby", "Editorial5", 1121, 5,"F. Scott Fitzgerald", "asdasdasd");
        Llibre llibre6 = new Llibre("ISBN6", 66666, "Moby Dick", "Editorial6", 3141, 6,"Herman Melville", "asdasd");
        Llibre llibre7 = new Llibre("ISBN7", 77777, "War and Peace", "Editorial7", 5161, 7,"Leo Tolstoy", "asdasd");
        Llibre llibre8 = new Llibre("ISBN8", 88888, "The Odyssey", "Editorial8", 7181, 8,"Homer", "dasdas");
        Llibre llibre9 = new Llibre("ISBN9", 99999, "Pride and Prejudice", "Editorial9", 9202, 9,"Jane Austen", "asdsad");
        List<Llibre> llibres = Arrays.asList(llibre1, llibre2, llibre3, llibre4, llibre5, llibre6, llibre7, llibre8, llibre9);
        for (Llibre llibre : llibres) {
            manager.emmagatzemarLlibre(llibre);
        }

    }

    @After
    public void tearDown() {
        this.manager.clear();
    }

    @Test
    public void testEmmagatzemarLlibres() {
        Llibre llibre10 = new Llibre("ISBN10", 10101, "The Catcher in the Rye", "Editorial10", 1223, 10,"J.D. Salinger", "asdasd");
        int result = manager.emmagatzemarLlibre(llibre10);
        //The 10 book should be stored in the actual stack, so result should be 0
        Assert.assertEquals(0, result);

        Llibre llibre11 = new Llibre("ISBN11", 11112, "The Lord of the Rings", "Editorial11", 1324, 11,"J.R.R. Tolkien", "asdasd");
        result = manager.emmagatzemarLlibre(llibre11);
        //The 11 book should create a new stack, so result should be 1
        Assert.assertEquals(1, result);

    }

    @Test
    public void testCatalogarLlibres() {
        Llibre llibre10 = new Llibre("ISBN10", 10101, "The Catcher in the Rye", "Editorial10", 1223, 10,"J.D. Salinger", "asdasd");
        manager.emmagatzemarLlibre(llibre10);
        Llibre llibre11 = new Llibre("ISBN11", 11112, "The Lord of the Rings", "Editorial11", 1324, 11,"J.R.R. Tolkien", "asdasd");
        manager.emmagatzemarLlibre(llibre11);

        String expectedTitles[] = {
            "ISBN10", "ISBN9", "ISBN8", "ISBN7", "ISBN6", "ISBN5", "ISBN4", "ISBN3", "ISBN2", "ISBN1",
            "ISBN11"
        };

        String result;
        for (int i = 1; i <= 11; i++) {
            result = manager.catalogarLlibre().getId();
            Assert.assertEquals(expectedTitles[i-1], result);
        }
        //Now there are no more books to catalog, so the result should be null
        Llibre llibre = manager.catalogarLlibre();
        Assert.assertNull(llibre);

        //Check if still works when empied
        Llibre llibre12 = new Llibre("ISBN12", 12121, "Brave New World", "Editorial12", 1425, 12,"Aldous Huxley", "asdasd");
        manager.emmagatzemarLlibre(llibre12);
        result = manager.catalogarLlibre().getId();
        Assert.assertEquals("ISBN12", result);
    }

    @Test
    public void testPrestarLlibre() {
        for (int i = 1; i <= 8; i++) {
            String result = manager.catalogarLlibre().getId();
        }

        Prestec P1 = new Prestec("P1", "L1", "ISBN9", "01/03/2024", "15/03/2024");
        int result = manager.prestarLlibre(P1);
        Assert.assertEquals(1, result);
        //Should be successful

        //Try to lend a book that does isnt catalogated yet
        Prestec P2 = new Prestec("P2", "L1", "ISBN1", "01/03/2024", "15/03/2024");
        result = manager.prestarLlibre(P2);
        Assert.assertEquals(-1, result);
        manager.catalogarLlibre();
        result = manager.prestarLlibre(P2);
        Assert.assertEquals(1, result);

        //Try to lend a book to a reader that does not exist
        Prestec P3 = new Prestec("P3", "L4", "L999", "01/03/2024", "15/03/2024");
        result = manager.prestarLlibre(P3);
        Assert.assertEquals(-1, result);

        //Lend a book without more copies
        Prestec P4 = new Prestec("P4", "L1", "ISBN1", "01/03/2024", "15/03/2024");
        result = manager.prestarLlibre(P4);
        Assert.assertEquals(-2, result);
    }

    @Test
    public void testConsultarPrestecs() {
        for (int i = 1; i <= 5; i++) {
            String result = manager.catalogarLlibre().getId();
        }

        Prestec P1 = new Prestec("P1", "L1", "ISBN6", "01/03/2024", "15/03/2024");
        manager.prestarLlibre(P1);
        Prestec P2 = new Prestec("P2", "L1", "ISBN5", "02/03/2024", "16/03/2024");
        manager.prestarLlibre(P2);
        Prestec P3 = new Prestec("P3", "L2", "ISBN4", "03/03/2024", "17/03/2024");
        manager.prestarLlibre(P3);

        List<Prestec> prestecsL1 = manager.consutarPrestecs("L1");
        Assert.assertEquals(2, prestecsL1.size());
        Assert.assertEquals("P1", prestecsL1.get(0).getId());
        Assert.assertEquals("P2", prestecsL1.get(1).getId());

        List<Prestec> prestecsL2 = manager.consutarPrestecs("L2");
        Assert.assertEquals(0, prestecsL2.size());

    }


}

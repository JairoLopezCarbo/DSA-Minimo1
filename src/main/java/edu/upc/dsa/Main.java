package edu.upc.dsa;

import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Llibre;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/dsaApp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.services");

        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();

        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/dsaApp");
        beanConfig.setContact("support@example.com");
        beanConfig.setDescription("REST API for Products Manager");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setResourcePackage("edu.upc.dsa.services");
        beanConfig.setTermsOfServiceUrl("http://www.example.com/resources/eula");
        beanConfig.setTitle("REST API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setScan(true);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        BibliotecaManagerImpl manager = BibliotecaManagerImpl.getInstance();

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

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();
    }
}


package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jersey.listing.ApiListingResourceJSON;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

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

        SystemManagerImpl manager = SystemManagerImpl.getInstance();
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

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();
    }
}


package edu.upc.dsa.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import edu.upc.dsa.BibliotecaManagerImpl;
import edu.upc.dsa.models.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/biblioteca", description = "Gestion Biblioteca")
@Path("/biblioteca")
public class BibliotecaService {

    private BibliotecaManagerImpl manager;

    public BibliotecaService() {
        this.manager = BibliotecaManagerImpl.getInstance();

    }
//    GenericEntity<List<Vuelo>> entity = new GenericEntity<List<Vuelo>>(vuelos) {};

    //añadir lector
    @POST
    @ApiOperation(value = "Añadir un lector", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Lector.class),
            @ApiResponse(code = 500, message = "Lector actualizado")
    })
    @Path("/lector")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLector(Lector lector) {
        int result = this.manager.afegirLector(lector);
        if (result == -1) return Response.status(500).entity("Lector actualizado").build();
        else return Response.status(201).entity(lector).build();
    }

    @POST
    @ApiOperation(value = "Almacenar un libro", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Llibre.class),
            @ApiResponse(code = 500, message = "Error al almacenar el libro")
        })
    @Path("/llibre")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response emmagatzemarLlibre(Llibre llibre) {
        int result = this.manager.emmagatzemarLlibre(llibre);
        if (result == -1) return Response.status(500).entity("Error al almacenar el libro").build();
        else return Response.status(201).entity(llibre).build();
    }

    @GET
    @ApiOperation(value = "Catalogar un libro", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = String.class),
            @ApiResponse(code = 500, message = "No quedan libros para catalogar")
    })
    @Path("/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLlibre() {
        Llibre llibre = this.manager.catalogarLlibre();
        if (llibre == null) {
            return Response.status(500).entity("No quedan libros para catalogar").build();
        }
        return Response.status(201).entity(llibre).build();
    }

    //prestar libro
    @POST
    @ApiOperation(value = "Prestar un libro a un lector", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Prestec.class),
            @ApiResponse(code = 404, message = "Lector o libro no encontrado"),
            @ApiResponse(code = 500, message = "No hay copias disponibles del libro")
    })
    @Path("/prestar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response prestarLlibre(Prestec prestec) {
        int result = this.manager.prestarLlibre(prestec);
        if (result == -1) return Response.status(404).entity("Lector o libro no encontrado").build();
        else if (result == -2) return Response.status(500).entity("No hay copias disponibles del libro").build();
        else return Response.status(201).entity(prestec).build();
    }

    @GET
    @ApiOperation(value = "Consultar prestamos de un lector", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Prestec.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Lector no encontrado")
    })
    @Path("/prestecs/{idLector}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPrestecs(@PathParam("idLector") String idLector)
    {
        java.util.List<Prestec> prestecs = this.manager.consutarPrestecs(idLector);

        if (prestecs == null) {
            return Response.status(404).entity("Lector no encontrado").build();
        }

        javax.ws.rs.core.GenericEntity<java.util.List<Prestec>> entity = new javax.ws.rs.core.GenericEntity<java.util.List<Prestec>>(prestecs) {};

        return Response.status(201).entity(entity).build();
    }

}

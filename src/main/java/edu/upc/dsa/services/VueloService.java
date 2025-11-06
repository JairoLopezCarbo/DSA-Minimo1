package edu.upc.dsa.services;

import edu.upc.dsa.SystemManagerImpl;
import edu.upc.dsa.models.Vuelo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Api(value = "/vuelo", description = "Endpoint to vuelo Service")
@Path("vuelo")
public class VueloService {
    SystemManagerImpl manager;
    public VueloService() {
        this.manager = SystemManagerImpl.getInstance();
    }


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Vuelo.class),
            @ApiResponse(code = 404, message = "Vuelo no encontrado")
    })
    public Response getVuelo(@PathParam("id") String idVuelo) {
        Vuelo a = this.manager.getVuelo(idVuelo);
        if (a == null) return Response.status(404).build();
        else  return Response.status(200).entity(a).build();
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vuelo.class),
            @ApiResponse(code = 500, message = "Vuelo ya existente")
    })
    public Response addVuelo(Vuelo a) {
        int result = this.manager.addVuelo(a);
        if (result == -1) return Response.status(500).build();
        else return Response.status(201).entity(a).build();
    }
}

package edu.upc.dsa.services;

import edu.upc.dsa.SystemManagerImpl;
import edu.upc.dsa.models.Avion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.upc.dsa.models.*;
/**
 * Root resource (exposed at "myresource" path)
 */
@Api(value = "/avion", description = "Endpoint to avion service")
@Path("avion")
public class AvionService {
    SystemManagerImpl manager;
    public AvionService() {
        this.manager = SystemManagerImpl.getInstance();
    }


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Avion.class),
            @ApiResponse(code = 404, message = "Avion no encontrado")
    })
    public Response getAvion(@PathParam("id") String idAvion) {
        Avion a = this.manager.getAvion(idAvion);
        if (a == null) return Response.status(404).build();
        else  return Response.status(200).entity(a).build();
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Avion.class),
            @ApiResponse(code = 500, message = "Vuelo ya existente")
    })
    public Response addAvion(Avion a) {
        int result = this.manager.addAvion(a);
        if (result == -1) return Response.status(500).build();
        else return Response.status(201).entity(a).build();
    }
}

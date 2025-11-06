package edu.upc.dsa.services;

import edu.upc.dsa.models.Avion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import edu.upc.dsa.SystemManagerImpl;
import edu.upc.dsa.models.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/equipaje", description = "Operaciones sobre vuelos")
@Path("/equipaje")
public class EquipajeService {

    private SystemManagerImpl manager;

    public EquipajeService() {
        this.manager = SystemManagerImpl.getInstance();

    }
//    GenericEntity<List<Vuelo>> entity = new GenericEntity<List<Vuelo>>(vuelos) {};

    @POST
    @ApiOperation(value = "Facturar Equipaje en un Vuelo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Equipaje.class),
            @ApiResponse(code = 404, message = "Vuelo no encontrado")
    })
    @Path("/facturar/{idVuelo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response facturarEquipaje(@PathParam("idVuelo") String id, Equipaje equipaje) {
        int result = this.manager.facturarEquipaje(id, equipaje);
        if (result == -1) return Response.status(404).entity("Vuelo no encontrado").build();
        else return Response.status(201).entity(equipaje).build();
    }


    @GET
    @ApiOperation(value = "Devolver el equipaje facturado de un vuelo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Equipaje.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Vuelo no encontrado")
    })
    @Path("/devolver/{idVuelo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response devolverEquipaje(@PathParam("idVuelo") String idVuelo) {
        List<Equipaje> equipaje = this.manager.devolverEquipaje(idVuelo);

        if (equipaje == null) {
            return Response.status(404).entity("Vuelo no encontrado").build();
        }

        GenericEntity<List<Equipaje>> entity = new GenericEntity<List<Equipaje>>(equipaje) {};

        return Response.status(201).entity(entity).build();
    }


//    @DELETE
//    @ApiOperation(value = "eliminar un Vuelo", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "Vuelo no encontrado")
//    })
//    @Path("/{id}")
//    public Response deleteVuelo(@PathParam("id") String id) {
//        Vuelo t = this.manager.getVuelo(id);
//        if (t == null) return Response.status(404).build();
//        else this.manager.deleteVuelo(id);
//        return Response.status(201).build();
//    }
//
//    @PUT
//    @ApiOperation(value = "actualizar un Vuelo", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "Vuelo no encontrado")
//    })
//    @Path("/")
//    public Response updateTrack(Vuelo vuelo) {
//
//        Vuelo t = this.vm.updateVuelo(vuelo);
//
//        if (t == null) return Response.status(404).build();
//
//        return Response.status(201).build();
//    }


}

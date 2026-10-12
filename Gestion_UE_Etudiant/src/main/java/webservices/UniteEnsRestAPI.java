package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response ;
@Path("/ue")
public class UniteEnsRestAPI {
   UniteEnseignementBusiness helpers = new UniteEnseignementBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUE() {
        return Response
                .status(200)
                .entity(helpers.getListeUE())
                .build();
    }
        @Path("/new")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.TEXT_PLAIN)
        public Response createUe(UniteEnseignement unite){
            if (helpers.addUniteEnseignement(unite)){
                return Response.status(201).entity("ok").build();

            }
            else {
                return Response.status(400).entity("error").build();
        }
    }
    //getuebycode
    @Path("/inputcode")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getUeByCode(@PathParam("inputcode") int code){
        return Response.status(200)
                .entity("ok")
                .build();
    }
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getUeBySemestre(@QueryParam("semestre")int semestre){
        return Response.ok(semestre).build();
    }
    @Path("/delete")
    @DELETE
    public Response deleteUe(@QueryParam("id") int id){
        if  (helpers.deleteUniteEnseignement(id)){
            return Response.status(204).build();
        }
        else {
            return Response.status(404).build();
        }
    }

}

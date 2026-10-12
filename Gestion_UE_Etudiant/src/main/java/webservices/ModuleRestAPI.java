package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mod")
public class ModuleRestAPI {

    ModuleBusiness mb = new ModuleBusiness();
    UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response.status(200).entity(mb.getAllModules()).build();
    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createModule(Module module) {
        if (module == null || module.getUniteEnseignement() == null) {
            return Response.status(400).entity("error").build();
        }
        if (mb.addModule(module)) {
            return Response.status(201).entity("ok").build();
        }
        return Response.status(400).entity("error").build();
    }

    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@QueryParam("type") String type) {
        try {
            Module.TypeModule t = Module.TypeModule.valueOf(type.toUpperCase());
            return Response.status(200).entity(mb.getModulesByType(t)).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return Response.status(400).entity("invalid type").build();
        }
    }

    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if (module == null) {
            return Response.status(400).entity("error").build();
        }
        if (mb.updateModule(matricule, module)) {
            return Response.status(200).entity("ok").build();
        }
        return Response.status(404).entity("not found").build();
    }
    @Path("/delete")
    @DELETE
    public Response deleteModule(@QueryParam("matricule") String matricule) {
        if (mb.deleteModule(matricule)) {
            return Response.status(204).build();
        }
        return Response.status(404).build();
    }
    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module m = mb.getModuleByMatricule(matricule);
        if (m != null) {
            return Response.status(200).entity(m).build();
        }
        return Response.status(404).build();
    }
    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@QueryParam("type") String type) {
        try {
            Module.TypeModule t = Module.TypeModule.valueOf(type.toUpperCase());
            return Response.status(200).entity(mb.getModulesByType(t)).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return Response.status(400).entity("invalid type").build();
        }
    }
    }


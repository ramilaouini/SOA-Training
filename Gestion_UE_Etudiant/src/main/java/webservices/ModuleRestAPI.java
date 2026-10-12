package webservices;
import metiers.ModuleBusiness;
import entities.Module;
import metiers.UniteEnseignementBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response ;
@Path("/mod")
public class ModuleRestAPI {
    ModuleBusiness mb = new ModuleBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response
                .status(200)
                .entity(mb.getAllModules())
                .build();
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

}

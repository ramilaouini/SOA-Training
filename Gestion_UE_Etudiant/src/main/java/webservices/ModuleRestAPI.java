package webservices;
import metiers.ModuleBusiness;
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

}

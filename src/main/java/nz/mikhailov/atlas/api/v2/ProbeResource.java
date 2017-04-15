package nz.mikhailov.atlas.api.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/v2/probes")
public interface ProbeResource {

  @GET
  @Path("/{probeId}")
  @Produces(APPLICATION_JSON)
  Response get(@PathParam("probeId") int probeId);

}

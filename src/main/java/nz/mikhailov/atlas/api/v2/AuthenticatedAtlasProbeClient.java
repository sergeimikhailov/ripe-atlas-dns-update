package nz.mikhailov.atlas.api.v2;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import nz.mikhailov.atlas.config.Configuration;

import javax.ws.rs.core.Response;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;

public class AuthenticatedAtlasProbeClient
    implements ProbeResource {

  private final LambdaLogger logger;

  private final ProbeResource proxy;

  public AuthenticatedAtlasProbeClient(Configuration configuration, LambdaLogger logger) {

    this.logger = logger;
    this.proxy = newResource(
        ProbeResource.class,
        newClient()
            .target(configuration.getAtlasApiBaseUrl())
            .queryParam("key", configuration.getAtlasApiKey()));
  }

  @Override
  public Response get(int probeId) {

    logger.log("Requesting probe with id: " + probeId);
    return proxy.get(probeId);
  }
}

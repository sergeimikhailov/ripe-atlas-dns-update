package nz.mikhailov.atlas.api.v2;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;

public class ProbeResourceIntegrationTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Test
  public void shouldMapKnownFieldsCorrectlyToResponseObject() throws Exception {

    Response response = client().get(12345);
    Probe probe = response.readEntity(Probe.class);
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(probe).isEqualToComparingFieldByField(new Probe("12345", "122.56.60.0"));
  }

  private ProbeResource client() {

    return newResource(
        ProbeResource.class,
        newClient().target("http://localhost:" + wireMockRule.port()));
  }
}
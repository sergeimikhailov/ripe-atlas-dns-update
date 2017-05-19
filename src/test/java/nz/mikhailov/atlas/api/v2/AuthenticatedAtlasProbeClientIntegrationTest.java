package nz.mikhailov.atlas.api.v2;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import nz.mikhailov.atlas.config.Configuration;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AuthenticatedAtlasProbeClientIntegrationTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private Configuration configuration;

  @Mock
  private LambdaLogger logger;

  @Test
  public void shouldFormRequestWithValidKey() throws Exception {

    ProbeResource client = authenticatedClientWithKey("valid-api-key");
    Response response = client.get(12345);
    assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
  }

  @Test
  public void shouldFormRequestWithInvalidKey() throws Exception {

    ProbeResource client = authenticatedClientWithKey("invalid-api-key");
    Response response = client.get(12345);
    assertThat(response.getStatus()).isEqualTo(FORBIDDEN.getStatusCode());
  }

  private ProbeResource authenticatedClientWithKey(String key) {

    when(configuration.getAtlasApiBaseUrl()).thenReturn("http://localhost:" + wireMockRule.port());
    when(configuration.getAtlasApiKey()).thenReturn(key);
    return new AuthenticatedAtlasProbeClient(configuration, logger);
  }
}
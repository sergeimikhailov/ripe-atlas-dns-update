package nz.mikhailov.atlas;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaHandlerTest {

  private LambdaHandler handler = new LambdaHandler();

  @Test
  public void testSomeLibraryMethod() {

    String result = handler.handleRequest(null, null);
    assertThat(result).isEmpty();
  }

}

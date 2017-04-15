package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler
    implements RequestHandler<Void, String> {

  @Override
  public String handleRequest(Void input, Context context) {

    return "";
  }

}

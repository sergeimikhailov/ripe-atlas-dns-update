package nz.mikhailov.atlas.api.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Probe {

  private final String id;
  private final String addressV4;

  @JsonCreator
  public Probe(@JsonProperty("id") String id, @JsonProperty("address_v4") String addressV4) {
    this.id = id;
    this.addressV4 = addressV4;
  }

  public String getId() {
    return id;
  }

  public String getAddressV4() {
    return addressV4;
  }
}

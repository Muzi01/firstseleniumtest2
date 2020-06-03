package bindings.commons.automation.aio.rest.v2;

import java.util.Optional;

public class AIORestEvent {
  public final Optional<String> traceId;
  public final String endpoint;

  public AIORestEvent(final Optional<String> traceId, final String endpoint) {
    this.traceId = traceId;
    this.endpoint = endpoint;
  }
}

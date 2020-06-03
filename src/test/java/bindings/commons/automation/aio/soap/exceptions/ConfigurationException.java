package bindings.commons.automation.aio.soap.exceptions;

public class ConfigurationException extends RuntimeException {

  public ConfigurationException(final String message, final Throwable e) {
    super(message, e);
  }
}

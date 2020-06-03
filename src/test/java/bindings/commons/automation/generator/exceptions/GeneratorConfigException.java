package bindings.commons.automation.generator.exceptions;

/**
 * Indicates that some configuration is incorrect, such as missing db/tool access data.
 */
public class GeneratorConfigException extends RuntimeException {

  public GeneratorConfigException(final String message) {
    super(message);
  }

  public GeneratorConfigException(final String messageTemplate, final Object... params) {
    super(String.format(messageTemplate, params));
  }

  public GeneratorConfigException(final String message, final Throwable t) {
    super(message, t);
  }
}

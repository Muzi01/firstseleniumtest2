package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.generator.exceptions.TimeoutException;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Utils {

  private static final Logger LOGGER = LogManager.getLogger(Utils.class);

  // todo move to properties @AT-1157
  private static final long DEFAULT_TIMEOUT = 120000;
  private static final long DEFAULT_DELAY = 5000;

  private Utils() {
    throw new IllegalStateException("Utils class, do not instantiate this!");
  }

  public static Date subtractDaysFromDate(final Date date, final long days) {
    final Instant result = date.toInstant().minus(Duration.ofDays(days));
    return Date.from(result);
  }

  public static String toStringWithoutNullValues(final Object object, final ToStringStyle style) {
    final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(object, style) {
      @Override
      protected boolean accept(final Field field) {
        try {
          return super.accept(field) && field.get(object) != null;
        } catch (final IllegalAccessException e) {
          return super.accept(field);
        }
      }
    };
    return builder.toString();
  }

  public static void safeWait(final long millis) {
    try {
      LOGGER.debug("Waiting {} ms...", millis);
      TimeUnit.MILLISECONDS.sleep(millis);

    } catch (final InterruptedException e) {
      LOGGER.warn("Waiting interrupted!", e);
      Thread.currentThread().interrupt();
    }
  }

  public static void safeWait(final long millis, final String reason) {
    try {
      LOGGER.debug("Waiting {} ms for {}...", millis, reason);
      TimeUnit.MILLISECONDS.sleep(millis);

    } catch (final InterruptedException e) {
      LOGGER.warn("Waiting interrupted!", e);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Waits until a given condition is met (evaluated to true). Checks the condition every
   * {@link #DEFAULT_DELAY} ms. Throws an exception if the condition is not met in
   * {@link #DEFAULT_TIMEOUT} ms.
   *
   * @param condition the condition, e.g. <code>() -> counter > 100</code>
   * @see #waitUntil(BooleanSupplier, long)
   * @see #waitUntil(BooleanSupplier, long, long)
   */
  public static void waitUntil(final BooleanSupplier condition) {
    waitUntil(condition, DEFAULT_DELAY, DEFAULT_TIMEOUT);
  }

  /**
   * Waits until a given condition is met (evaluated to true). Checks the condition every
   * {@link #DEFAULT_DELAY} ms.
   *
   * @param condition the condition, e.g. <code>() -> counter > 100</code>
   * @param timeout timeout (in ms) after which an exception will be thrown if condition isn't met
   */
  public static void waitUntil(final BooleanSupplier condition, final long timeout) {
    waitUntil(condition, DEFAULT_DELAY, timeout);
  }

  /**
   * Waits until a given condition is met (evaluated to true)
   *
   * @param condition the condition, e.g. <code>() -> counter > 100</code>
   * @param delay delay (in ms) between condition evaluations
   * @param timeout timeout (in ms) after which an exception will be thrown if condition isn't met
   */
  public static void waitUntil(final BooleanSupplier condition, final long delay,
      final long timeout) {
    if (!isConditionMet(condition, delay, timeout)) {
      throw new TimeoutException(String.format("Condition not met in %d seconds!", timeout / 1000));
    }

  }

  public static void waitUntil(final BooleanSupplier condition, final long delay,
      final long timeout,
      final String errorMsg) {
    if (!isConditionMet(condition, delay, timeout)) {
      throw new TimeoutException(
          String.format("Condition not met in %d seconds! <br> %s", timeout / 1000, errorMsg));
    }

  }

  private static boolean isConditionMet(
      final BooleanSupplier condition, final long delay, final long timeout) {
    final long start = System.currentTimeMillis();
    final double seconds = delay / 1000.0;
    boolean conditionMet;
    while (!(conditionMet = condition.getAsBoolean())
        && System.currentTimeMillis() - start < timeout) {
      LOGGER.debug("Condition not satisfied, retrying in {} seconds...", seconds);
      safeWait(delay);
    }
    return conditionMet;
  }

  public static <T> T waitFor(final Supplier<T> supplier) {
    return waitFor(supplier, DEFAULT_DELAY, DEFAULT_TIMEOUT);
  }

  public static <T> T waitFor(final Supplier<T> supplier, final long timeout) {
    return waitFor(supplier, DEFAULT_DELAY, timeout);
  }

  public static <T> T waitFor(final Supplier<T> supplier, final long delay, final long timeout) {
    final long start = System.currentTimeMillis();
    final double seconds = delay / 1000.0;
    T expected;
    while ((expected = supplier.get()) == null && System.currentTimeMillis() - start < timeout) {
      LOGGER.debug("Expected object not found, retrying in {} seconds...", seconds);
      safeWait(delay);
    }
    if (expected == null) {
      throw new TimeoutException(String.format("Condition not met in %d seconds!", timeout / 1000));
    }

    return expected;
  }

}

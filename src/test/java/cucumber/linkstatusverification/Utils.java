package cucumber.linkstatusverification;


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
    private static final Logger LOGGER = LogManager.getLogger (Utils.class);


    private Utils () {
        throw new IllegalStateException ("Utils class, do not instantiate this!");
    }

    public static Date subtractDaysFromDate (Date date, long days) {
        Instant result = date.toInstant ().minus (Duration.ofDays (days));
        return Date.from (result);
    }

    public static String toStringWithoutNullValues (final Object object, ToStringStyle style) {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder (object, style) {
            protected boolean accept (Field field) {
                try {
                    return super.accept (field) && field.get (object) != null;
                } catch (IllegalAccessException var3) {
                    return super.accept (field);
                }
            }
        };
        return builder.toString ();
    }

    public static void safeWait (long millis) {
        try {
            LOGGER.debug ("Waiting {} ms...", millis);
            TimeUnit.MILLISECONDS.sleep (millis);
        } catch (InterruptedException var3) {
            LOGGER.warn ("Waiting interrupted!", var3);
            Thread.currentThread ().interrupt ();
        }

    }

    public static void safeWait (long millis, String reason) {
        try {
            LOGGER.debug ("Waiting {} ms for {}...", millis, reason);
            TimeUnit.MILLISECONDS.sleep (millis);
        } catch (InterruptedException var4) {
            LOGGER.warn ("Waiting interrupted!", var4);
            Thread.currentThread ().interrupt ();
        }

    }

    public static void waitUntil (BooleanSupplier condition) {
        waitUntil (condition, 5000L, 120000L);
    }

    public static void waitUntil (BooleanSupplier condition, long timeout) {
        waitUntil (condition, 5000L, timeout);
    }

    public static void waitUntil (BooleanSupplier condition, long delay, long timeout) {
        if (!isConditionMet (condition, delay, timeout)) {

        }
    }

    public static void waitUntil (BooleanSupplier condition, long delay, long timeout, String errorMsg) {
        if (!isConditionMet (condition, delay, timeout)) {
        }
    }

    private static boolean isConditionMet (BooleanSupplier condition, long delay, long timeout) {
        long start = System.currentTimeMillis ();
        double seconds = (double) delay / 1000.0D;

        boolean conditionMet;
        while (!(conditionMet = condition.getAsBoolean ()) && System.currentTimeMillis () - start < timeout) {
            LOGGER.debug ("Condition not satisfied, retrying in {} seconds...", seconds);
            safeWait (delay);
        }

        return conditionMet;
    }

    public static <T> T waitFor (Supplier<T> supplier) {
        return waitFor (supplier, 5000L, 120000L);
    }

    public static <T> T waitFor (Supplier<T> supplier, long timeout) {
        return waitFor (supplier, 5000L, timeout);
    }

    public static <T> T waitFor (Supplier<T> supplier, long delay, long timeout) {
        long start = System.currentTimeMillis ();
        double seconds = (double) delay / 1000.0D;

        Object expected;
        while ((expected = supplier.get ()) == null && System.currentTimeMillis () - start < timeout) {
            LOGGER.debug ("Expected object not found, retrying in {} seconds...", seconds);
            safeWait (delay);
        }

        if (expected == null) {

        } else return (T) expected;

        return null;
    }
}

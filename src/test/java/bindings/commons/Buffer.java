package bindings.commons;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import bindings.commons.NoSuchKeyException;
import bindings.commons.SingleValueHolder;
import bindings.commons.LegacySingleValueHolderProvider;


/**
 * @deprecated Please stop using Buffer. Instead of having one storage space with a String key,
 *             create your own Holder as a bean with the "cucumber-glue" scope as a value storage.
 */
@Deprecated
@Component
public class Buffer implements ApplicationContextAware {
    private static final Logger LOGGER = LogManager.getLogger(Buffer.class);

    private static final Pattern BUFFER_KEY_WITH_DOT = Pattern.compile("\\w+[\\.]\\w+");
    private static final Comparator<SingleValueHolder> COMPLEX_KEY_COMPARATOR =
            Comparator.comparingInt(
                    holder -> BUFFER_KEY_WITH_DOT.matcher(holder.handledKey()).matches() ? 0 : 1);
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static ApplicationContext applicationContext;

    private final List<SingleValueHolder> singleValueHolderList;
    private final LegacySingleValueHolderProvider legacySingleValueHolderProvider;

    public Buffer(
            final List<SingleValueHolder> singleValueHolderList,
            final LegacySingleValueHolderProvider legacySingleValueHolderProvider) {

        this.singleValueHolderList = singleValueHolderList;
        this.legacySingleValueHolderProvider = legacySingleValueHolderProvider;
    }

    /**
     * Checks if the buffer contains any entries with the given key.
     *
     * @param key key to be checked.
     * @return whether the buffer contains a value for given key
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static boolean containsKey(final String key) {

        return getInstance().innerContainsKey(key);
    }


    private boolean innerContainsKey(final String key) {

        return obtainHolder(key).getValue().isDefined();
    }

    /**
     * Retrieves an object from the buffer. If no object is set under given key, returns the key.
     *
     * @param key key to retrieve by
     * @return value for the given key, or key if no value is found
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static Object get(final String key) {

        return getInstance().innerGet(key);
    }

    private Object innerGet(final String key) {

        return obtainHolder(key).getValue()
                .peek(value -> LOGGER.info(String.format("key: %s value is : %s", key, value)))
                .getOrElse(key);
    }

    /**
     * Returns a String value for the given key.
     *
     * @param key key to retrieve from
     * @return a String value of given key
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static String getStringValue(final String key) {

        return ObjectUtils.getStringValueOfObject(get(key));
    }

    /**
     * Returns a Long value from a given key.
     *
     * @param key key to retrieve from
     * @return a Long value from a given key
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static Long getLongValue(final String key) {

        return Long.parseLong(getStringValue(key));
    }

    /**
     * Returns an Integer value from a given key.
     *
     * @param key key to retrieve from
     * @return an Integer value from a given key
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static Integer getIntValue(final String key) {

        return Integer.valueOf(getStringValue(key));
    }

    /**
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static Double getDoubleValue(final String key) {

        return Double.parseDouble(getStringValue(key));
    }

    /**
     * Returns a Boolean value from a given key.
     *
     * @param key key to retrieve from
     * @return a Boolean value from a given key
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static Boolean getBooleanValue(final String key) {

        return Boolean.parseBoolean(getStringValue(key));
    }

    /**
     * Returns contents of the buffer.
     *
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static String entriesToString() {

        return getInstance().innerEntriesToString();
    }

    private String innerEntriesToString() {

        return allPossibleHolderStream()
                .map(SingleValueHolder::toString)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private Stream<SingleValueHolder> allPossibleHolderStream() {

        return Stream.concat(singleValueHolderList.stream(),
                legacySingleValueHolderProvider.getHolders().stream());
    }

    /**
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static void update(final String key, final Object value) {

        getInstance().innerUpdate(key, value);
    }

    private void innerUpdate(final String key, final Object newValue) {

        final SingleValueHolder<Object> holder = obtainHolder(key);
        holder.getValue()
                .peek(oldValue -> holder.setValue(newValue))
                .getOrElseThrow(() -> new NoSuchKeyException(
                        String.format("No such key: %s. Add a key before update!", key)));
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        // NOSONAR - this is a hack to combine static calls with a Singleton Bean.
        // Whole class will be deleted anyway
        Buffer.applicationContext = applicationContext;
    }

    /**
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public <T> void setValue(final String key, final T value) {

        LOGGER.info("Buffer - set: {} = {} ({})", key, value, getStringValue(key));
        obtainHolder(key).setValue(value);
    }

    private <T> SingleValueHolder<T> obtainHolder(final String key) {

        return singleValueHolderList.stream()
                .filter(holder -> holder.handledKey().equals(key))
                .reduce((a, b) -> {
                    throw new IllegalStateException("More than one holder found for key: " + key);
                })
                .orElse(legacySingleValueHolderProvider.get(key));
    }

    private static Buffer getInstance() {

        return applicationContext.getBean(Buffer.class);
    }

    /**
     * Stores a value object in the buffer.
     *
     * @param key key to store under
     * @param value object to be stored
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static <T> void set(final String key, final T value) {

        getInstance().setValue(key, value);
    }

    /**
     * @deprecated - please stop using this class. Create your own holder.
     */
    @Deprecated
    public static String replaceKeysWithValues(final String text) {

        return getInstance().innerReplaceKeysWithValues(text);
    }

    private String innerReplaceKeysWithValues(final String text) {

        if (StringUtils.isEmpty(text)) {
            return text;
        }

        return allPossibleHolderStream()
                .filter(holder -> holder.getValue().isDefined())
                .sorted(COMPLEX_KEY_COMPARATOR)
                .map(this::replacingFunction)
                .reduce(Function::andThen)
                .orElse(Function.identity())
                .apply(text);
    }

    private Function<String, String> replacingFunction(final SingleValueHolder holder) {

        return input -> input.replaceAll("\\b" + holder.handledKey() + "\\b",
                ObjectUtils.getStringValueOfObject(get(holder.handledKey())));
    }

}

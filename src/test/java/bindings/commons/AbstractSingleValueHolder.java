package bindings.commons;


import io.vavr.control.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import bindings.commons.ObjectUtils;


public abstract class AbstractSingleValueHolder<T> implements SingleValueHolder<T> {

    protected final Logger logger = LogManager.getLogger(getClass());

    private final String key;

    protected Option<T> value = Option.none();

    protected AbstractSingleValueHolder(final String key) {

        this.key = key;
    }


    @Override
    public Option<T> getValue() {

        this.logger.debug("Getting value: {}", this.value);
        return this.value;
    }

    @Override
    public void setValue(final T value) {

        this.logger.info("Setting value: {}", value);
        this.value = Option.of(value);
    }

    @Override
    public void clear() {

        this.logger.info("Clearing value");
        this.value = Option.none();
    }

    @Override
    public String handledKey() {

        return this.key;
    }

    @Override
    public String toString() {

        return String.format("Holder[%s: %s]", handledKey(), valueToString());
    }

    private String valueToString() {

        return getValue()
                .map(ObjectUtils::getStringValueOfObject)
                .getOrElse("<undefined>");
    }
}

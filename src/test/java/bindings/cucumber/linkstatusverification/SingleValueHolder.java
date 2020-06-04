package bindings.cucumber.linkstatusverification;

import io.vavr.control.Option;

public interface SingleValueHolder<T> {
    Option<T> getValue();

    void setValue(T amount);

    void clear();

    String handledKey();
}

package bindings.cucumber.linkstatusverification;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class URLContext {
    private final List<URLStrategy> strategies;

    public URLContext(final List<URLStrategy> strategies) {
        this.strategies = strategies;
    }

    public URLStrategy getStrategy(final String protocol) {
        for (final URLStrategy urlStrategy : this.strategies) {
            if (urlStrategy.accept(protocol)) {
                return urlStrategy;
            }
        }
        throw new IllegalArgumentException("Not found strategy for protocol {}" + protocol);
    }
}

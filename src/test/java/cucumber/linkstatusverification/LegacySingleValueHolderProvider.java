package cucumber.linkstatusverification;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.linkstatusverification.StoryProxyComponent;

@StoryProxyComponent
public class LegacySingleValueHolderProvider {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final Map<String, LegacySingleValueHolder<?>> legacyHolders = new HashMap<>();

    public <T> LegacySingleValueHolder<T> get(final String key) {

        if (!this.legacyHolders.containsKey(key)) {
            logger.warn("There is no custom ValueHolder. Used buffer with custom key: " + key);
            this.legacyHolders.put(key, new LegacySingleValueHolder<T>(key));
        }
        return (LegacySingleValueHolder<T>) this.legacyHolders.get(key);
    }

    public Collection<LegacySingleValueHolder<?>> getHolders() {

        return this.legacyHolders.values();
    }
}

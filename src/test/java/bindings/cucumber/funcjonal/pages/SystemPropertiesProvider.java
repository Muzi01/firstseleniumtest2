package bindings.cucumber.funcjonal.pages;
import bindings.commons.automation.generator.utils.Environment;

public class SystemPropertiesProvider {
    private static final String PROFILE_ACTIVE_PARAMETER = "spring.profiles.active";
    private static final String PROFILE_DEFAULT_PARAMETER = "spring.profiles.default";
    private static final String DEFAULT_PROFILE_TEST = "contextTest";
    public static final String ACTIVE_ENVIRONMENT_STRING;
    public static final Environment ACTIVE_ENVIRONMENT;

    static {
        MandatoryParametersHandler.handleTestParameters();
        final String defaultEnv = System.getProperty(PROFILE_DEFAULT_PARAMETER);

        String env = System.getProperty(PROFILE_ACTIVE_PARAMETER, defaultEnv);
        if (env == null || env.equalsIgnoreCase(DEFAULT_PROFILE_TEST)) {
            env = Environment.SIT.name().toLowerCase();
        }

        ACTIVE_ENVIRONMENT_STRING = env.toLowerCase();
        ACTIVE_ENVIRONMENT = Environment.valueOf(env.toUpperCase());
    }

    private SystemPropertiesProvider() {

        throw new IllegalStateException("SystemPropertiesProvider should not be initialised.");
    }

}

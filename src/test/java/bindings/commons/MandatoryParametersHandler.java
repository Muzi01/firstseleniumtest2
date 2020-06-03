package bindings.commons;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

class MandatoryParametersHandler {
    private static final Logger LOGGER =
            LogManager.getLogger(MandatoryParametersHandler.class);
    private static final String CONFIG_SERVER_USERNAME_PARAMETER = "config.server.username";
    private static final String CONFIG_SERVER_USERNAME_PASS_PARAMETER = "config.server.username.pass";
    private static final String CONFIG_SERVER_USERNAME_VALUE = "testing";
    private static final String CONFIG_SERVER_PASSWORD_VALUE = "QQZ2XArGj64r";
    private static final String CUSTOMER_CREATOR_GENERATOR_DB_LOCATION = "generatorDbLocation";
    private static final String CUSTOMER_CREATOR_GENERATOR_LOCATION = "generatorLocation";
    private static final String CUSTOMER_CREATOR_PRODUCTION_DB = "prod";

    private MandatoryParametersHandler() {
        throw new IllegalStateException("MandatoryParametersHandler should not be initialised.");
    }

    static void handleTestParameters() {
        setParameterIfMissing(CONFIG_SERVER_USERNAME_PARAMETER,
                CONFIG_SERVER_USERNAME_VALUE);
        setParameterIfMissing(CONFIG_SERVER_USERNAME_PASS_PARAMETER,
                CONFIG_SERVER_PASSWORD_VALUE);
        setParameterIfMissing(CUSTOMER_CREATOR_GENERATOR_DB_LOCATION,
                CUSTOMER_CREATOR_PRODUCTION_DB);
        setParameterIfMissing(CUSTOMER_CREATOR_GENERATOR_LOCATION,
                CUSTOMER_CREATOR_PRODUCTION_DB);
    }

    private static void setParameterIfMissing(final String propertyName, final String propertyValue) {
        if (Strings.isBlank(System.getProperty(propertyName))) {
            LOGGER.warn(String.format(
                    "%n"
                            + "************************************************** %s PARAMETER WAS NOT SET **********************************************"
                            + "%n"
                            + "************************************************** SETTING %s TO DEFAULT VALUE **********************************************",
                    propertyName, propertyName));
            setMissingParameter(propertyName, propertyValue);
        }
    }

    private static void setMissingParameter(final String propertyName, final String propertyValue) {
        System.setProperty(propertyName, propertyValue);
    }

}

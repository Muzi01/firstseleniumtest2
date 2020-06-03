package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.config.PropertyProvider;
import com.ipfdigital.automation.config.PropertyProviderFactory;

class MulePropertyProvider {

  private final PropertyProvider propertyProvider;

  public MulePropertyProvider(final String env) {
    propertyProvider = PropertyProviderFactory.propertyProvider(env);
  }

  String getMuleInternalUrlProperty() {
    return propertyProvider.getProperty("mule.internal");
  }

  String getMulePublicUrlProperty() {
    return propertyProvider.getProperty("mule.public");
  }

  String getDataPartnerRestUser() {
    return propertyProvider.getProperty("mule.data.partner.rest.user");
  }

  String getDataPartnerRestPassword() {
    return propertyProvider
        .getProperty("mule.data.partner.rest.password");
  }

  String getDefaultBrokerName() {
    return propertyProvider.getProperty("mule.brokerUserName");
  }

  String getDefaultBrokerPassword() {
    return propertyProvider.getProperty("mule.brokerPassword");
  }

  String getDefaultKeyStoreLocation() {
    return propertyProvider
        .getProperty("mule.keyStoreLocation");
  }

  String getDefaultKeyStorePassword() {
    return propertyProvider
        .getProperty("mule.keyStorePassword");
  }

  String getDefaultKeyStoreAlias() {
    return propertyProvider.getProperty("mule.keyStoreAlias");
  }

  String getDefaultSfRestUser() {
    return propertyProvider.getProperty("mule.sf.rest.user");
  }

  String getDefaultSfRestPassword() {
    return propertyProvider
        .getProperty("mule.sf.rest.password");
  }
}

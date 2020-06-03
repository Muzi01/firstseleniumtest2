package bindings.commons.automation.aio.rest.dto.gdpr;

public enum ConsentVersion {
  // Poland
  PL_IPFD_CHANNELS("PL_PL_HAPI_BlendedMarketingIPFDAndChannels_20180201"),
  PL_DATA_TRANSFER("PL_PL_HAPI_marketingDataTransfer_20180201"),
  PL_EMAIL_SMS("PL_PL_HAPI_BlendedMarketingEmailAndSMS_20180201"),
  PL_PHONE_ROBO("PL_PL_HAPI_BlendedMarketingPhoneAndRobo_20180201"),

  // Spain
  ES_IPFD_CHANNELS("ES_ES_Creditea_BlendedMarketingIPFDAndChannels_20180207"),
  ES_DATA_TRANSFER("ES_ES_Creditea_marketingDataTransfery_20180208"),
  ES_3RD_PARTY("ES_ES_Creditea_marketing3rdParty_20180207"),

  // Estonia
  EE_IPFD_CHANNELS("EE_EE_C24_BlendedMarketingIPFDAndChannels_20180126"),
  EE_3RD_PARTY("EE_EE_C24_marketing3rdParty_20180126"),
  EE_NA("N_A"),

  // Finland
  FI_IPFD_CHANNELS("FI_FI_C24_BlendedMarketingIPFDAndChannels_20180125"),
  FI_3RD_PARTY("FI_FI_C24_marketing3rdParty_20180125"),
  FI_DATAPOOL("FI_FI_C24_DPDDatapool_20180725"),

  // Latvia
  LV_IPFD_CHANNELS("LV_LV_C24_BlendedMarketingIPFDAndChannels_20180206"),
  LV_3RD_PARTY("LV_LV_C24_marketing3rdParty_20180206"),

  // Lithuania
  LT_IPFD_CHANNELS("LT_LT_C24_BlendedMarketingIPFDAndChannels_20180201"),

  // Mexico
  MX_CONSENT("MX_consent"),

  // Czech
  CZ_CONSENT("CZ_consent");

  private final String version;

  ConsentVersion(final String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return this.version;
  }
}

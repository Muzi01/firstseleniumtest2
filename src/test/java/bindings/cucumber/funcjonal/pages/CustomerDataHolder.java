package bindings.cucumber.funcjonal.pages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import bindings.commons.automation.customer.GeneratedCustomerDTO;
import cucumber.api.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import bindings.commons.automation.api.customer.DebitCardGenerator;
import bindings.commons.automation.api.customer.SsnGenerator;
import bindings.commons.automation.customer.Bank;
import bindings.commons.automation.customer.Country;
import bindings.commons.automation.customer.Gender;
import bindings.commons.automation.customer.ssn.GenerateSsnParamsDTO;
import bindings.commons.automation.customer.ssn.SsnType;
import bindings.commons.automation.customer.GenerateCustomerDTO;
import bindings.commons.automation.generator.model.aio.Customer;
import bindings.commons.automation.generator.model.aio.dao.CustomerDAO;
import bindings.commons.automation.generator.model.aio.dao.ProvidentDataDAO;
import bindings.commons.automation.generators.RandomUtils;
import bindings.cucumber.funcjonal.pages.orange.StoryProxyComponent;
import bindings.commons.automation.generators.RandomUtils.randomEnum;




import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.customer.ssn.GenerateSsnParamsDTO;
import com.ipfdigital.cfg.SystemPropertiesProvider;
import bindings.commons.Buffer;
import bindings.commons.CucumberAdditionalInfoService;
import bindings.commons.automation.BankScrapingAU;
import bindig
import static com.ipfdigital.automation.generators.RandomUtils.randomEnum;


@StoryProxyComponent
public class CustomerDataHolder implements CustomerDataProvider {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDataHolder.class);
    private static final String ENVIRONMENT = SystemPropertiesProvider.ACTIVE_ENVIRONMENT_STRING;
    private static final String REGISTERED_FIRST_NAME = "REGISTERED.firstName";
    private static final String REGISTERED_LAST_NAME = "REGISTERED.lastName";
    private static final String REGISTERED_SECOND_LAST_NAME = "REGISTERED.secondLastName";
    private static final String PASS_TO_SET =
            "329fd69d9e296dda6f5b5d858392f6c73e83734d4878e9002d80f1b8af132c38"; // FIXME
    public static final String REGISTERED_SSN = "REGISTERED.ssn";
    public static final String REGISTERED_MSISDN = "REGISTERED.msisdn";
    public static final String REGISTERED_EMAIL = "REGISTERED.email";
    public static final String REGISTERED_ID = "REGISTERED.id";
    private static final String REGISTERED_PASSWORD = "REGISTERED.password";
    private static final String REGISTERED_BANK_ACCOUNT = "REGISTERED.bankAccount";
    public static final String REGISTERED_NO_PREFIX_PHONE = "REGISTERED_NOPREFIX_PHONE";
    public static final String CUSTOMER_RECOVERY_PASSWORD = "12345678";
    public static final String CUSTOMER_NEW_PASSWORD = "newQwerty123";
    public static final String GENERATED_DOCUMENT_ID = "GENERATED_DOCUMENT_ID";
    public static final String GENERATED_POSTCODE = "GENERATED_POSTCODE";
    public static final String GENERATED_CITY = "GENERATED_CITY";
    public static final String GENERATED_DOOR_NUMBER = "GENERATED_DOOR_NUMBER";
    public static final String GENERATED_HOME_NUMBER = "GENERATED_HOME_NUMBER";
    public static final String GENERATED_STREET = "GENERATED_STREET";
    private static final String GENERATED_BIRTHDAY_DAY = "GENERATED_BIRTHDAY_DAY";
    private static final String GENERATED_BIRTHDAY_MONTH = "GENERATED_BIRTHDAY_MONTH";
    private static final String GENERATED_BIRTHDAY_YEAR = "GENERATED_BIRTHDAY_YEAR";
    public static final String GENERATED_NOPREFIX_PHONE = "GENERATED_NOPREFIX_PHONE";
    private static final String GENERATED_PHONE_2 = "GENERATED_PHONE2";
    public static final String GENERATED_PHONE = "GENERATED_PHONE";
    public static final String GENERATED_LAST_NAME = "GENERATED_LAST_NAME";
    public static final String GENERATED_SECOND_LAST_NAME = "GENERATED_SECOND_LAST_NAME";
    public static final String GENERATED_SECOND_FIRST_NAME = "GENERATED_SECOND_FIRST_NAME";
    public static final String GENERATED_FIRST_NAME = "GENERATED_FIRST_NAME";
    public static final String GENERATED_EMAIL = "GENERATED_EMAIL";
    public static final String GENERATED_SSN = "GENERATED_SSN";
    public static final String GENERATED_DEBIT_CARD = "GENERATED_DEBIT_CARD";
    public static final String RANDOM_COMPANY_NAME = "RANDOM_COMPANY_NAME";
    public static final String GENERATED_IBAN = "GENERATED_IBAN";
    private static final String GENERATED_PASSPORT = "GENERATED_PASSPORT";
    public static final String DEFAULT_CUSTOMER_PASSWORD_VALUE = "Qwerty123";

    public static final String DEFAULT_OTP_VALUE = "1234";
    private Customer registeredCustomer;
    private final Map<String, String> defaultValues = new HashMap<>();
    private final CucumberAdditionalInfoService cucumberAdditionalInfoService;
    private final CustomerDAO customerDAO;
    private final DebitCardGenerator debitCardGenerator;
    private final SsnGenerator ssnGenerator;
    private final ProvidentDataDAO providentDataDAO;
    private Optional<String> postAddressAsResidence = Optional.empty();
    private Optional<String> additionalPostCode = Optional.empty();
    private Optional<String> additionalCity = Optional.empty();
    private Optional<String> additionalStreet = Optional.empty();
    private Optional<String> additionalHouseNumber = Optional.empty();
    private boolean willCustomerUseDueDateService = false;
    private boolean willCustomerUseDebitCard = false;
    private boolean willCustomerSelectILProduct = false;
    private Optional<String> additionalDoorNumber = Optional.empty();
    private boolean willLoanBeTakenByMarriage = false;

    public CustomerDataHolder(
            final CucumberAdditionalInfoService cucumberAdditionalInfoService,
            final CustomerDAO customerDAO, final DebitCardGenerator debitCardGenerator,
            final SsnGenerator ssnGenerator, final ProvidentDataDAO providentDataDAO) {
        this.cucumberAdditionalInfoService = cucumberAdditionalInfoService;
        this.customerDAO = customerDAO;
        this.debitCardGenerator = debitCardGenerator;
        this.ssnGenerator = ssnGenerator;
        this.providentDataDAO = providentDataDAO;
    }

    @Override
    public boolean getWillCustomerUseDebitCard() {
        return willCustomerUseDebitCard;
    }

    public void setWillCustomerUseDebitCard(final boolean willCustomerUseDebitCard) {
        this.willCustomerUseDebitCard = willCustomerUseDebitCard;
    }

    @Override
    public boolean getWillCustomerUseDueDateService() {
        return willCustomerUseDueDateService;
    }

    @Override
    public Optional<String> getPostAddressAsResidence() {
        return postAddressAsResidence;
    }

    public void setPostAddressAsResidence(final String postAddressAsResidence) {
        this.postAddressAsResidence = Optional.ofNullable(postAddressAsResidence);
    }

    @Override
    public Optional<String> getAdditionalPostCode() {
        return additionalPostCode;
    }

    public void setAdditionalPostCode(final String additionalPostCode) {
        this.additionalPostCode = Optional.ofNullable(additionalPostCode);
    }

    @Override
    public Optional<String> getAdditionalCity() {
        return additionalCity;
    }

    public void setAdditionalCity(final String additionalCity) {
        this.additionalCity = Optional.ofNullable(additionalCity);
    }

    @Override
    public Optional<String> getAdditionalStreet() {
        return additionalStreet;
    }

    public void setAdditionalStreet(final String additionalStreet) {
        this.additionalStreet = Optional.ofNullable(additionalStreet);
    }

    @Override
    public Optional<String> getAdditionalHouseNumber() {
        return additionalHouseNumber;
    }

    public void setAdditionalHouseNumber(final String additionalHouseNumber) {
        this.additionalHouseNumber = Optional.ofNullable(additionalHouseNumber);
    }

    @Override
    public Optional<String> getAdditionalDoorNumber() {
        return additionalDoorNumber;
    }

    @Override
    public String getCustomerSsn() {
        if (defaultValues.containsKey(GENERATED_SSN)) {
            return getTestData(GENERATED_SSN);
        }
        return getTestData(REGISTERED_SSN);
    }

    @Override
    public Long provideCustomerId() {
        if (defaultValues.containsKey(REGISTERED_ID)) {
            return Long.valueOf(getTestData(REGISTERED_ID));
        }
        return customerDAO.getCustomerBySsn(ENVIRONMENT, getCustomerSsn()).id;
    }

    @Override
    public String provideCustomerBankAccount() {
        if (defaultValues.containsKey(GENERATED_IBAN)) {
            return getTestData(GENERATED_IBAN);
        }
        return getTestData(REGISTERED_BANK_ACCOUNT);
    }

    @Override
    public String getNoPrefixPhone() {
        if (defaultValues.containsKey(GENERATED_NOPREFIX_PHONE)) {
            return getTestData(GENERATED_NOPREFIX_PHONE);
        }
        return getTestData(REGISTERED_NO_PREFIX_PHONE);
    }

    public void setAdditionalDoorNumber(final String additionalDoorNumber) {
        this.additionalDoorNumber = Optional.ofNullable(additionalDoorNumber);
    }

    private String fillCustomerDataWithFoundResults(final String country,
                                                    final List<String> resultList) {

        final int randomIndex = ThreadLocalRandom.current().nextInt(resultList.size());
        final Customer dbCustomer =
                customerDAO.getCustomerBySsn(ENVIRONMENT, resultList.get(randomIndex));
        final int prefixLength = Country.valueOf(country).getPrefix().length();
        dbCustomer.msisdn2 = dbCustomer.msisdn.substring(prefixLength);

        setRegisteredTestData(dbCustomer);
        return dbCustomer.id.toString();
    }

    public void updateRegisteredCustomer() {
        final Customer dbCustomer =
                customerDAO.getCustomerBySsn(ENVIRONMENT, getTestData(GENERATED_SSN));
        setRegisteredCustomer(dbCustomer);
    }

    public void setRegisteredTestData(final Customer customer) {
        setRegisteredTestDataAddress(customer);
        setRegisteredTestDataPersonal(customer);
        setRegisteredCustomer(customer);
        logTestData("REGISTERED");
    }

    private void setRegisteredTestDataPersonal(final Customer customer) {

        defaultValues.put("REGISTERED_ID", Long.toString(customer.id));
        defaultValues.put("REGISTERED_DOCUMENT_ID", customer.personalDocumentNumber);
        defaultValues.put("REGISTERED_EMAIL", customer.email);
        defaultValues.put("REGISTERED_FIRST_NAME", customer.firstName);
        defaultValues.put("REGISTERED_SECOND_FIRST_NAME", customer.secondFirstName);
        defaultValues.put("REGISTERED_IBAN", customer.bankAccount);
        defaultValues.put("REGISTERED_LAST_NAME", customer.lastName);
        defaultValues.put("REGISTERED_PHONE", customer.msisdn);
        defaultValues.put("REGISTERED_NOPREFIX_PHONE", getNonPrefixMsisdn(customer));
        defaultValues.put("REGISTERED_SECOND_LAST_NAME", customer.secondLastName);
        defaultValues.put("REGISTERED_SSN", customer.identifier);
    }

    private String getNonPrefixMsisdn(final Customer customer) {
        return customer.msisdn.startsWith("+")
                ? customer.msisdn.replace(customer.country.getPrefix(), "")
                : customer.msisdn;
    }

    private void logTestData(final String dataType) {

        for (final Map.Entry<String, String> map : defaultValues.entrySet()) {
            if (map.getKey().contains(dataType)) {
                LOGGER.info("Used test data. Key: {} value: {}", map.getKey(), map.getValue());
            }
        }
    }

    public Customer getRegisteredCustomer() {

        Assert.assertNotNull("Register a customer first!", registeredCustomer);
        return registeredCustomer;
    }

    public boolean isCustomerRegistered() {
        return registeredCustomer != null;
    }

    void setRegisteredCustomer(final Customer customer) {

        registeredCustomer = customer;
        LOGGER.info("Using Test Customer - ssn: {}, ID: {}", customer.identifier,
                customer.id);

        // todo figure out a better way to pass test values, might need to rewrite steps
        Buffer.set(REGISTERED_MSISDN, customer.msisdn);
        Buffer.set(REGISTERED_PASSWORD, customer.password);
        Buffer.set(REGISTERED_SSN, customer.identifier);
        Buffer.set(REGISTERED_ID, customer.id);
        Buffer.set(REGISTERED_EMAIL, customer.email);
        Buffer.set(REGISTERED_BANK_ACCOUNT, customer.bankAccount);
        Buffer.set(REGISTERED_FIRST_NAME, customer.firstName);
        Buffer.set(REGISTERED_LAST_NAME, customer.lastName);
        Buffer.set(REGISTERED_SECOND_LAST_NAME,
                customer.secondLastName);
    }

    private void setRegisteredTestDataAddress(final Customer customer) {

        defaultValues.put("REGISTERED_STREET", customer.street);
        defaultValues.put("REGISTERED_DOOR_NUMBER", customer.door);
        defaultValues.put("REGISTERED_POSTCODE", customer.postcode);
        defaultValues.put("REGISTERED_CITY", customer.city);
    }

    void setGeneratedTestData(final GeneratedCustomerDTO customer) {

        setGeneratedTestDataAddress(customer);
        setGeneratedTestDataPersonal(customer);
        setGeneratedTestDataPhone(customer);
        setGeneratedTestDataBirthday(customer);
        setGeneratedTestDataOther(customer);
        defaultValues.put(GENERATED_SSN, customer.getIdentifier());
        defaultValues.put(GENERATED_DEBIT_CARD, customer.getDebitCardNumber());
        defaultValues.put(GENERATED_PASSPORT, customer.getPersonalDocumentNumber());

        logTestData("GENERATED");

        Buffer.set(GENERATED_SSN, customer.getIdentifier());
        Buffer.set(GENERATED_DEBIT_CARD, customer.getDebitCardNumber());
        Buffer.set(GENERATED_PASSPORT, customer.getPersonalDocumentNumber());
    }

    private static String checkDateForFeDropDownInput(final String dateValue) {
        return dateValue.startsWith("0") ? dateValue.replaceAll("0", "") : dateValue;
    }

    private void setGeneratedTestDataOther(final GeneratedCustomerDTO customer) {
        if (customer.getCountry() != Country.AU) {
            defaultValues.put(GENERATED_IBAN, customer.getBankAccount().getNumber());
        }
        defaultValues.put(RANDOM_COMPANY_NAME, customer.getCompanyName());
        defaultValues.put(GENERATED_DEBIT_CARD, customer.getDebitCardNumber());
    }

    private void setGeneratedTestDataPersonal(final GeneratedCustomerDTO customerDTO) {

        defaultValues.put(GENERATED_DOCUMENT_ID, customerDTO.getPersonalDocumentNumber());
        defaultValues.put(GENERATED_SSN, customerDTO.getIdentifier());
        defaultValues.put(GENERATED_EMAIL, customerDTO.getEmail());
        defaultValues.put(GENERATED_FIRST_NAME, customerDTO.getFirstName());
        defaultValues.put(GENERATED_SECOND_FIRST_NAME, customerDTO.getSecondFirstName());
        defaultValues.put(GENERATED_LAST_NAME, customerDTO.getLastName());
        defaultValues.put(GENERATED_SECOND_LAST_NAME, customerDTO.getSecondLastName());
    }

    private void setGeneratedTestDataPhone(final GeneratedCustomerDTO customerDTO) {

        defaultValues.put(GENERATED_PHONE, customerDTO.getPrefixedMsisdn());
        defaultValues.put(GENERATED_PHONE_2, customerDTO.getPrefixedMsisdn());
        defaultValues.put(GENERATED_NOPREFIX_PHONE, customerDTO.getMsisdn());
    }

    private void setGeneratedTestDataBirthday(final GeneratedCustomerDTO customerDTO) {
        if (customerDTO.getCountry() != Country.ES) {
            defaultValues.put(GENERATED_BIRTHDAY_YEAR,
                    customerDTO.getDateOfBirth().toString().split("-")[0]);
            defaultValues.put(GENERATED_BIRTHDAY_MONTH,
                    checkDateForFeDropDownInput(customerDTO.getDateOfBirth().toString().split("-")[1]));
            defaultValues.put(GENERATED_BIRTHDAY_DAY,
                    checkDateForFeDropDownInput(customerDTO.getDateOfBirth().toString().split("-")[2]));
        }
    }

    private void setGeneratedTestDataAddress(final GeneratedCustomerDTO customerDTO) {

        defaultValues.put(GENERATED_STREET, customerDTO.getAddress().getStreet());
        defaultValues.put(GENERATED_HOME_NUMBER, customerDTO.getAddress().getHome());
        defaultValues.put(GENERATED_DOOR_NUMBER, customerDTO.getAddress().getDoor());
        defaultValues.put(GENERATED_POSTCODE, customerDTO.getAddress().getPostcode());
        defaultValues.put(GENERATED_CITY, customerDTO.getAddress().getCity());
    }

    @Override
    public String getTestData(String key) {
        if (defaultValues.containsKey(key)) {
            LOGGER.info("key: {} value is : {}", key, defaultValues.get(key));
            return defaultValues.get(key);

        } else if (Buffer.containsKey(key)) {
            return String.valueOf(Buffer.get(key));

        } else {
            return key;
        }
    }

    public void setThatLoanWillBeTakenByMarriage(boolean b) {
        willLoanBeTakenByMarriage = b;
    }

    public void attachAdditionalValuesInfo(final Scenario scenario) {

        cucumberAdditionalInfoService.logDefaultValuesAdditionalInformation(scenario,
                defaultValues);
    }

    public String prepareDefaultValuesForSerenityReport() {
        return cucumberAdditionalInfoService.prepareLogMessage(defaultValues);
    }

    void putTestDataIntoBuffer(final Country country, final GeneratedCustomerDTO customerDTO) {

        putSsnForCountryIntoBuffer(country, customerDTO);
        putContactInfoForCountryIntoBuffer(country, customerDTO);
        putPersonalInfoForCountryIntoBuffer(country, customerDTO);
        putAddressInfoForCountryIntoBuffer(country, customerDTO);
        putBankInfoForCountryIntoBuffer(country, customerDTO);
        if (country.equals(Country.AU)) {
            putMedicareInfoForCountryIntoBuffer(country, customerDTO);
            putDriverLicenseInfoForCountryIntoBuffer(country, customerDTO);
            putEmploymentInfoForCountryIntoBuffer(country, customerDTO);
            putLoanInfoForCountryIntoBuffer(country, customerDTO);
        }
    }

    private void putSsnForCountryIntoBuffer(final Country country,
                                            final GeneratedCustomerDTO testData) {
        Buffer.set(country + "_SSN", testData.getIdentifier());
        Buffer.set(country + "_UNDERAGE_SSN", testData.getUnderageSSN());
        Buffer.set(country + "_OVERAGE_SSN", testData.getOverageSSN());
        Buffer.set(country + "_OPTIMAL_SSN", testData.getOptimalSSN());
        if (country == Country.PL) {
            putOptimalPlSsnIntoBuffer();
        }
        if (country == Country.ES) {
            final GenerateSsnParamsDTO paramsDTO = new GenerateSsnParamsDTO(testData.getGender(),
                    testData.getDateOfBirth(), SsnType.NIE, Country.ES);
            Buffer.set(country + "_NIE", ssnGenerator.generate(paramsDTO));
            paramsDTO.setSsnType(SsnType.DNI);
            Buffer.set(country + "_DNI", ssnGenerator.generate(paramsDTO));
        }
    }

    private void putOptimalPlSsnIntoBuffer() {

        String optimalSsn = Buffer.getStringValue("PL_OPTIMAL_SSN");
        while (doesCustomerExists(optimalSsn)
                || providentDataDAO.countByPin(SystemPropertiesProvider.ACTIVE_ENVIRONMENT,
                optimalSsn) > 0) {
            LOGGER.warn(
                    "SSN {} is already used or present in ProvidentData table! Generating a new one...",
                    optimalSsn);
            final GenerateSsnParamsDTO ssnParamsDTO = new GenerateSsnParamsDTO(
                    RandomUtils.randomEnum(Gender.class), RandomUtils.randomBirthday(55, 55),
                    RandomUtils.randomEnum(SsnType.class), Country.PL);

            optimalSsn = ssnGenerator.generate(ssnParamsDTO);
            Buffer.set("PL_OPTIMAL_SSN", optimalSsn);
        }
    }

    private boolean doesCustomerExists(final String ssn) {
        try {
            customerDAO.getCustomerBySsn(ENVIRONMENT, ssn);
            return true;
        } catch (final IllegalStateException e) {
            return false;
        }
    }

    private void putContactInfoForCountryIntoBuffer(final Country country,
                                                    final GeneratedCustomerDTO customerDTO) {
        Buffer.set(country + "_EMAIL", customerDTO.getEmail());
        Buffer.set(country + "_MOBILE_NO", customerDTO.getMsisdn());
        Buffer.set(country + "_MOBILE_NO2", customerDTO.getMsisdn2());
        Buffer.set(country + "_PREFIXED_MOBILE_NO", customerDTO.getPrefixedMsisdn());
    }

    private void putPersonalInfoForCountryIntoBuffer(final Country country,
                                                     final GeneratedCustomerDTO customerDTO) {
        Buffer.set(country + "_DOCUMENT_ID", customerDTO.getPersonalDocumentNumber());
        Buffer.set(country + "_FIRST_NAME", customerDTO.getFirstName());
        Buffer.set(country + "_SECOND_FIRST_NAME", customerDTO.getSecondFirstName());
        Buffer.set(country + "_LAST_NAME", customerDTO.getLastName());
        Buffer.set(country + "_SECOND_LAST_NAME", customerDTO.getSecondLastName());
        Buffer.set(country + "_GENDER", customerDTO.getGender());
    }

    private void putAddressInfoForCountryIntoBuffer(final Country country,
                                                    final GeneratedCustomerDTO customerDTO) {
        Buffer.set(country + "_CITY", customerDTO.getAddress().getCity());
        Buffer.set(country + "_POSTCODE", customerDTO.getAddress().getPostcode());
        Buffer.set(country + "_STREET", customerDTO.getAddress().getStreet());
        Buffer.set(country + "_HOME_NUMBER", customerDTO.getAddress().getHome());
        Buffer.set(country + "_DOOR_NUMBER", customerDTO.getAddress().getDoor());
    }

    private void putBankInfoForCountryIntoBuffer(final Country country,
                                                 final GeneratedCustomerDTO customerDTO) {
        // till fixed generator for AU
        if (country != Country.AU) {
            Buffer.set(country + "_IBAN", customerDTO.getBankAccount().getNumber());
        }
        if (country.equals(Country.MX)) {
            Buffer.set(country + "_RFC", customerDTO.getIdentifier().substring(0, 10));
            Buffer.set(country + "_SHORT_IBAN", customerDTO.getBankAccount().getShortNumber());
            Buffer.set(country + "_DEBIT_CARD", debitCardGenerator.generate());
            Buffer.set(country + "_BANK_CODE", randomEnum(Bank.class).bankCode);
        }
    }

    private void putMedicareInfoForCountryIntoBuffer(final Country country,
                                                     final GeneratedCustomerDTO generatedCustomerDTO) {
        Buffer.set(country + "_MEDICARE_IRN", generatedCustomerDTO.getMedicare().getIrn());
        Buffer.set(country + "_MEDICARE_CARDNUMBER",
                generatedCustomerDTO.getMedicare().getCardNumber());
        Buffer.set(country + "_MEDICARE_EXPIRYDATEYEAR",
                generatedCustomerDTO.getMedicare().getExpiryDate().split(",")[0]);
        Buffer.set(country + "_MEDICARE_EXPIRYDATEMONTH",
                generatedCustomerDTO.getMedicare().getExpiryDate().split(",")[1]);
        Buffer.set(country + "_MEDICARE_EXPIRYDATEDAY",
                generatedCustomerDTO.getMedicare().getExpiryDate().split(",")[2]);
    }

    private void putDriverLicenseInfoForCountryIntoBuffer(final Country country,
                                                          final GeneratedCustomerDTO generatedCustomerDTO) {
        Buffer.set(country + "_DRIVERSLICENSE_STATE",
                generatedCustomerDTO.getDriversLicence().getState());
        Buffer.set(country + "_DRIVERSLICENSE_EXPIRYDATEYEAR",
                generatedCustomerDTO.getDriversLicence().getExpiryDate().split(",")[0]);
        Buffer.set(country + "_DRIVERSLICENSE_EXPIRYDATEMONTH",
                generatedCustomerDTO.getDriversLicence().getExpiryDate().split(",")[1]);
        Buffer.set(country + "_DRIVERSLICENSE_EXPIRYDATEDAY",
                generatedCustomerDTO.getDriversLicence().getExpiryDate().split(",")[2]);
        Buffer.set(country + "_DRIVERSLICENSE_LICENSENUMBER",
                generatedCustomerDTO.getDriversLicence().getLicenceNumber());
    }

    private void putEmploymentInfoForCountryIntoBuffer(final Country country,
                                                       final GeneratedCustomerDTO customerDTO) {
        Buffer.set(country + "_EMPLOYMENTHISTORY_STUDIESLEVEL",
                customerDTO.getEmploymentHistory().getStudiesLevel());
        Buffer.set(country + "_EMPLOYMENTHISTORY_UNEMPLOYED",
                customerDTO.getEmploymentHistory().getUnemployed());
        Buffer.set(country + "_EMPLOYMENTHISTORY_STUDIESLEVEL",
                customerDTO.getEmploymentHistory().getStudiesLevel());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERFIRST_NAME",
                customerDTO.getEmploymentHistory().getFirst().getEmployerName());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERFIRST_JOBTITLE",
                customerDTO.getEmploymentHistory().getFirst().getJobTitle());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERFIRST_EMPLOYMENTYPE",
                customerDTO.getEmploymentHistory().getFirst().getEmploymentType());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERFIRST_JOBDURATION",
                customerDTO.getEmploymentHistory().getFirst().getJobDuration());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERSECOND_NAME",
                customerDTO.getEmploymentHistory().getSecond().getEmployerName());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERSECOND_JOBTITLE",
                customerDTO.getEmploymentHistory().getSecond().getJobTitle());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERSECOND_EMPLOYMENTYPE",
                customerDTO.getEmploymentHistory().getSecond().getEmploymentType());
        Buffer.set(country + "_EMPLOYMENTHISTORY_EMPLOYERSECOND_JOBDURATION",
                customerDTO.getEmploymentHistory().getSecond().getJobDuration());
    }

    private void putLoanInfoForCountryIntoBuffer(final Country country,
                                                 final GeneratedCustomerDTO customerDTO) {
        Buffer.set(country + "_LOANPURPOSE", customerDTO.getLoanPurpose());
        Buffer.set(country + "_BIRTHDAYYEAR", customerDTO.getDateOfBirth().toString().split("-")[0]);
        Buffer.set(country + "_BIRTHDAYMONTH", customerDTO.getDateOfBirth().toString().split("-")[1]);
        Buffer.set(country + "_BIRTHDAYDAY", customerDTO.getDateOfBirth().toString().split("-")[2]);
        Buffer.set(country + "_STATE", customerDTO.getState());
        Buffer.set(country + "_MARTIALSTATUS", customerDTO.getMaritalStatus());
        Buffer.set(country + "_RESIDENCE", customerDTO.getResidence());
        Buffer.set(country + "_SUBURB", customerDTO.getAddress().getSuburb());
        Buffer.set(country + "_PASSPORTNUMBER", customerDTO.getPassport());
        Buffer.set(country + "_STATENAME", customerDTO.getState().stateName);
        Buffer.set(country + "_BANKSCRAPING", BankScrapingAU.AU_BANKSCRAPINGRESPONSE);
        Buffer.set(country + "_BANKSCRAPING_GAMBLING", BankScrapingAU.AU_BANKSCRAPINGRESPONSE_GAMBLING);
    }

    public void fillCustomerData(final String country, final String errorMsg,
                                 final List<String> resultList) {

        if (!resultList.isEmpty()) {
            final String customerID =
                    fillCustomerDataWithFoundResults(country, resultList);
            customerDAO.updatePassword(SystemPropertiesProvider.ACTIVE_ENVIRONMENT, PASS_TO_SET,
                    customerID);
        } else {
            logAndThrowError(errorMsg);
        }
    }

    private void logAndThrowError(final String errorMessage) {

        LOGGER.error(errorMessage);
        throw new IllegalStateException(errorMessage);
    }

    public void setWillCustomerUseDueDateService(final boolean willCustomerUseDueDateService) {
        this.willCustomerUseDueDateService = willCustomerUseDueDateService;
    }

    public boolean willCustomerSelectILProduct() {
        return willCustomerSelectILProduct;
    }

    public void setWillCustomerSelectILProduct(final boolean willSelectILProduct) {
        willCustomerSelectILProduct = willSelectILProduct;
    }

    @Override
    public boolean willLoanBeTakenByMarriage() {
        return willLoanBeTakenByMarriage;
    }
}

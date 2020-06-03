package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.model.aio.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements ResultMapper<Customer> {

  @Override
  public Customer mapByResultSet(final ResultSet resultSet) throws SQLException {
    final Customer customer = new Customer();
    customer.id = resultSet.getLong("ID");
    customer.personalDocumentNumber = resultSet.getString("personalDocumentNumber");
    customer.identifier = resultSet.getString("identifier");
    customer.firstName = resultSet.getString("firstName");
    customer.secondFirstName = resultSet.getString("secondFirstName");
    customer.lastName = resultSet.getString("lastName");
    customer.secondLastName = resultSet.getString("secondLastName");
    customer.bankAccount = resultSet.getString("bankAccount");
    customer.authBank = resultSet.getString("authBank");
    customer.country = Country.valueOf(resultSet.getString("country"));
    customer.city = resultSet.getString("city");
    customer.postcode = resultSet.getString("postcode");
    customer.street = resultSet.getString("street");
    customer.door = resultSet.getString("door");
    customer.email = resultSet.getString("notNullEmail");
    customer.msisdn = resultSet.getString("msisdn");
    customer.msisdn2 = resultSet.getString("msisdn2");
    customer.addressCountry = resultSet.getString("addressCountry");
    customer.referrerChannel = resultSet.getString("referrerChannel");
    customer.manualAddress = resultSet.getBoolean("manualAddress");
    customer.shortBankAccount = resultSet.getString("shortBankAccount");
    customer.marketingPermission = resultSet.getBoolean("marketingPermission");
    customer.marketingPermissionPaper = resultSet.getBoolean("marketingPermissionPaper");
    customer.marketingPermissionEmailOrSms =
        resultSet.getBoolean("marketingPermissionEmailOrSms");
    customer.marketingPermissionPhone = resultSet.getBoolean("marketingPermissionPhone");
    customer.consentForAgentContact = resultSet.getBoolean("consentForAgentContact");
    customer.facebookToken = resultSet.getString("facebookToken");
    customer.fraudAMLRisk = resultSet.getString("fraudAMLRisk");
    customer.fraudAMLRiskReason = resultSet.getString("fraudAMLRisk");
    customer.disableDraw = resultSet.getBoolean("disableDraw");
    customer.disableLateFee = resultSet.getBoolean("disableLateFee");
    customer.directDebitCustomerReference = resultSet.getString("directDebitCustomerReference");
    customer.disableLogin = resultSet.getBoolean("disableLogin");
    customer.disableSms = resultSet.getBoolean("disableSms");
    customer.employee = resultSet.getBoolean("employee");
    customer.cityOfBirth = resultSet.getString("cityOfBirth");
    customer.statementOfAccountDeliveryMethod =
        resultSet.getString("statementOfAccountDeliveryMethod");
    customer.uuid = resultSet.getString("uuid");
    customer.suspendAnonymization = resultSet.getBoolean("suspend_anonymization");
    customer.onHoldReason = resultSet.getString("onHoldReason");
    customer.residentialStatus = resultSet.getString("residentialStatus");
    customer.identifierType = resultSet.getString("identifierType");
    customer.thirdPartiesMarketingRejection =
        resultSet.getBoolean("thirdPartiesMarketingRejection");
    customer.created = resultSet.getTimestamp("created");
    customer.countryStateOfBirth = resultSet.getString("countryStateOfBirth");
    customer.taxIdentificationNumber = resultSet.getString("taxIdentificationNumber");
    customer.dateOfBirth = resultSet.getDate("dateOfBirth");
    customer.tempIdentifier = resultSet.getBoolean("tempIdentifier");
    customer.registered = resultSet.getBoolean("registered");
    customer.pin = resultSet.getString("pin");
    customer.deviceToken = resultSet.getString("deviceToken");
    customer.preDueDateReminderSMS = resultSet.getBoolean("preDueDateReminderSMS");
    customer.preferredLanguage = resultSet.getString("preferredLanguage");
    customer.gender = resultSet.getString("gender");
    customer.onHold = resultSet.getBoolean("onHold");
    customer.updated = resultSet.getTimestamp("updated");
    customer.referenceNumber = resultSet.getString("referenceNumber");
    customer.legacyCustomerNumber = resultSet.getString("legacyCustomerNumber");
    customer.invoiceDeliveryMethod = resultSet.getString("invoiceDeliveryMethod");
    customer.contractDeliveryMethod = resultSet.getString("contractDeliveryMethod");
    customer.bankAccountVerified = resultSet.getBoolean("bankAccountVerified");
    customer.floor = resultSet.getString("floor");
    customer.countryOfBirth = resultSet.getString("countryOfBirth");
    customer.province = resultSet.getString("province");
    customer.entityVersion = resultSet.getLong("entityVersion");

    return customer;
  }

}

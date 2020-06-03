package bindings.cucumber.funcjonal.pages;

import java.util.Optional;

public interface CustomerDataProvider {

    boolean getWillCustomerUseDebitCard();

    boolean getWillCustomerUseDueDateService();

    Optional<String> getPostAddressAsResidence();

    Optional<String> getAdditionalPostCode();

    Optional<String> getAdditionalCity();

    Optional<String> getAdditionalStreet();

    Optional<String> getAdditionalHouseNumber();

    Optional<String> getAdditionalDoorNumber();

    String getCustomerSsn();

    String getNoPrefixPhone();

    String getTestData(String key);

    Long provideCustomerId();

    String provideCustomerBankAccount();

    boolean willLoanBeTakenByMarriage();
}

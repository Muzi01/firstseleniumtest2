package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.CustomerBankAccountDTO;

import java.util.List;

final class CustomerBankAccountGeneratorService implements CustomerBankAccountGenerator {

  private final List<GenerateIbanStrategy> ibanStrategyInterfaceList;

  public CustomerBankAccountGeneratorService(
      final List<GenerateIbanStrategy> ibanStrategyInterfaceList) {
    this.ibanStrategyInterfaceList = ibanStrategyInterfaceList;
  }

  @Override
  public CustomerBankAccountDTO generate(final Country country) {
    return ibanStrategyInterfaceList
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one customer bandk account generator strategy for country "
                  + country.name());
        })
        .map(usedStrategy -> {
          String iban = usedStrategy.generate();
          if (country == Country.MX) {
            return new CustomerBankAccountDTO(iban, iban.substring(6));
          } else {
            return new CustomerBankAccountDTO(iban, null);
          }
        })
        .orElseThrow(() -> new IllegalStateException(
            "Not found iban strategy for county " + country.name()));
  }
}

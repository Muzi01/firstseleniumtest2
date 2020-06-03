package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.customer.Country;

import java.util.List;

final class MsisdnGeneratorService implements MsisdnGenerator {
  private final List<GenerateMsisdnStrategy> generateMsisdnStrategyInterfaceList;

  public MsisdnGeneratorService(
      final List<GenerateMsisdnStrategy> generateMsisdnStrategyInterfaceList) {
    this.generateMsisdnStrategyInterfaceList = generateMsisdnStrategyInterfaceList;
  }

  @Override
  public String generate(final Country country) {
    return generateMsisdnStrategyInterfaceList
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one msisdn generate strategy for country " + country.name());
        })
        .map(GenerateMsisdnStrategy::generate)
        .orElseThrow(
            () -> new IllegalStateException(
                "Not found msisdn generator for country" + country.name()));

  }
}

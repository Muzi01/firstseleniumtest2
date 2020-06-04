package bindings.cucumber.linkstatusverification;


import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;

import static bindings.cucumber.linkstatusverification.Country.*;


@Service
public class CustomerBrandProvider {
    private final CustomerCountryProvider customerCountryProvider;
    private final List<Country> hapiCountriesList = ImmutableList.of(PL, ES, MX, CZ);

    public CustomerBrandProvider(
            final CustomerCountryProvider customerCountryProvider) {
        this.customerCountryProvider = customerCountryProvider;
    }

    public Brand chooseBrand() {
        return hapiCountriesList.contains(customerCountryProvider.chooseCountry())
                ? Brand.HAPI
                : Brand.CREDIT24;
    }
}

package bindings.cucumber.linkstatusverification;

import org.springframework.stereotype.Service;



@Service
public class CustomerCountryProvider {
    private static final String COUNTRY = "Country";
    private static final String GET_CUSTOMER_COUNTRY_CODE =
            "SELECT addressCountry FROM Customer WHERE ID = ?";


    public Country chooseCountry() {
        final String country = Buffer.getStringValue(COUNTRY);
        return Country.valueOf(country);
    }
    }
package bindings.cucumber.linkstatusverification;
import bindings.cucumber.funcjonal.pages.StoryProxyComponent;
@StoryProxyComponent
public class RestClientAddressProvider {


    private final CustomerCountryProvider customerCountryProvider;
    private final CustomerBrandProvider customerBrandProvider;

    public RestClientAddressProvider(

            final CustomerCountryProvider customerCountryProvider,
            final CustomerBrandProvider customerBrandProvider) {

        this.customerCountryProvider = customerCountryProvider;
        this.customerBrandProvider = customerBrandProvider;
    }

    public String provideRestEndpointAddress() {
        if (this.customerBrandProvider.chooseBrand( ) == null) {
            throw new IllegalStateException("Brand for Customer must be set");
        }

        final String key =
                String.format("endpoint.%s.%s.rest",
                        this.customerBrandProvider.chooseBrand( ).toString( ).toLowerCase( ),
                        this.customerCountryProvider.chooseCountry( ).toString( ).toLowerCase( ));
        return null;
    }
}

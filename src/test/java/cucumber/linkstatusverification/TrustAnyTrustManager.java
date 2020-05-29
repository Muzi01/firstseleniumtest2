package cucumber.linkstatusverification;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class TrustAnyTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
        // Empty to skip all certification exceptions
    }

    @Override
    public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
        // Empty to skip all certification exceptions
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] {};
    }
}

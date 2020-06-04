package bindings.cucumber.linkstatusverification;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface URLStrategy {
    int getUrlResponseCode(URL url)
            throws NoSuchAlgorithmException, IOException, KeyManagementException;

    boolean accept(String protocol);
}

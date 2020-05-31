package tests.Tauron.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.springframework.stereotype.Service;
@Service
public class BaseTauron extends tests.Tauron.Pages.LoginPage {
    private static final Logger LOGGER = LogManager.getLogger(BaseTauron.class);
    public Object LoginPage;

    @Test

    public void TauronLogowanie() {


    }


}

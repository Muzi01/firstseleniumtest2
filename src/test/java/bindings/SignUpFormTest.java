package bindings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SignUpFormTest  {
    private WebDriver driver;
    @Test
    public void signUp(){
        driver.get("http://www.kimschiller.com/page-object-pattern-tutorial/index.html");

        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.isInitialized());

        signUpPage.enterName("First", "Last");
        signUpPage.enterAddress("123 Street", "12345");

      //  ReceiptPage receiptPage = signUpPage.submit();
     //   assertTrue(receiptPage.isInitialized());

       // assertEquals("Thank you", receiptPage.confirmationHeader());
    }}
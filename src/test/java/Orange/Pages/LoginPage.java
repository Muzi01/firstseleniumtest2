package Orange.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@StoryProxyComponent
public class LoginPage  {

    @FindBy (linkText ="login-field"  )
    public WebElement email;

    @FindBy (linkText ="Dalej")
    public   WebElement  Dalej;


    public void email() {
        email.sendKeys("piotr.kramkowski@gmail.com");
    }

    public void Dalej(){
        Dalej.click();
    }
}

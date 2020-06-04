package bindings.cucumber.funcjonal.steps;

import cucumber.api.java.en.Given;

public class NavigationSteps {

    private final PageNavigator pageNavigator;
    public NavigationSteps(final PageNavigator pageNavigator) {
        this.pageNavigator = pageNavigator;
    }

    @Given ("^Customer is on application page$")
    public void navigateToApplicationPage() {
        pageNavigator.navigateToApplicationPage();
    }


    @Given ("^User is on linkedin login page$")
    public void userIsOnLinkedinLoginPage () {
        pageNavigator.navigatetoLinkedin ();
    }
}

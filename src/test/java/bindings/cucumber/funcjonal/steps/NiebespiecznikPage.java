package bindings.cucumber.funcjonal.steps;

import bindings.cucumber.funcjonal.pages.niebespiecznik.Niebespiecznik;
import io.cucumber.java.en.And;


public class NiebespiecznikPage {
    @And("^I click niebespiecznik$")
    public void iClickNiebespiecznik (Niebespiecznik niebespiecznik) {
        niebespiecznik.clickNiebespiecznik ();

    }
}

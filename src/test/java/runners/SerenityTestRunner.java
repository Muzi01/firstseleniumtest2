package runners;


import StepDefsSerenity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class SerenityTestRunner {


    @Steps
    private MRCircuitUser RESTUser;
    @Test
    @Title("REST Assured Test")
    public void searchSerinityInGoogle() throws Exception {
        // GIVEN
        RESTUser.LoginIntoTheWebServiceSendsRequest ();

        // WHEN
        RESTUser.GetStatusCode200 ();
        // THEN
        RESTUser.Validate20CircuitsReturned ();



        }


    }


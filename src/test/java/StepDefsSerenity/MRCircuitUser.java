package StepDefsSerenity;

import static org.hamcrest.Matchers.hasSize;

import org.mockito.BDDMockito.Then;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class MRCircuitUser {
    private Response response;

    public void Validate20CircuitsReturned() {
        // TODO Auto-generated method stub
        response.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

    }



    public void LoginIntoTheWebServiceSendsRequest() {
        // TODO Auto-generated method stub
        response = SerenityRest.get("http://ergast.com/api/f1/2017/circuits.json");
    }



    public void GetStatusCode200() {
        // TODO Auto-generated method stub
        response.then().statusCode(200);
    }



}
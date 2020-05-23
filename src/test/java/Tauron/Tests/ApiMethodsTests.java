package Tauron.Tests;

import com.jayway.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiMethodsTests {
    @Test
    void getUserDetailsTest(){
        RestAssured.baseURI ="https://reqers.in/api/users/";
        RequestSpecification httpRequest = (RequestSpecification) RestAssured.given ();
        Response response = httpRequest.request (Method.GET, "2");
        int statusCode = response.getStatusCode ();
        Assert.assertEquals (statusCode,200);

    }
}
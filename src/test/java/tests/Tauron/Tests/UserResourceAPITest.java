package tests.Tauron.Tests;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class UserResourceAPITest {

    @Test
    public void testGetUserDetails() {
        when().
                get("/user/{userId}", 1).
                then().
                statusCode(200).
                body("userId", equalTo(1),
                        "firstName", equalTo("Joe"),
                        "secondName", equalTo("Bloggs"),
                        "email", equalTo("me@email.com"),
                        "address.firstLineAddress", equalTo("My First Line Address"),
                        "address.secondLineAddress", equalTo("My Second Line Address"),
                        "address.postCode", equalTo("MU3 28P"),
                        "address.city", equalTo("Paris"),
                        "address.country", equalTo("UK"));
    }

}

package tests.legacy;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;

public class RESTAssuredJSONTests {

    final static String ROOT_URI = "http://dummy.restapiexample.com/api/v1/employees";

    @Test
    public void simple_get_test() {
        Response response = get(ROOT_URI + "/list");
        System.out.println(response.asString());

        response.then().body("id", hasItems(1, 2));
        response.then().body("name", hasItems("Pankaj"));
    }

    @Test(dataProvider = "dpGetWithParam")
    public void get_with_param(int id, String name) {
        get(ROOT_URI + "/get/" + id).then().body("name", Matchers.is(name));
    }

    @DataProvider
    public Object[][] dpGetWithParam() {
        Object[][] testDatas = new Object[][] {
                new Object[] { 1, "Pankaj" },
                new Object[] { 2, "David" } };
        return testDatas;
    }
}
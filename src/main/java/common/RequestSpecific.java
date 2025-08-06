package common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class RequestSpecific {
    protected RequestSpecification reqSpec;

    @BeforeClass
    public static RequestSpecification setupSpec() {
        return RestAssured.given()
                .baseUri("https://rahulshettyacademy.com")
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json");

    }
}

package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import common.ConvertToJSON;
import common.GenericUtil;
import common.PropertyConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
public class CreateLocationTest {
    // REST Assured worked on given, when and then principle
    // given() ---> Input details should go under given()
    // when() ----> Resources and http methods
    // then() ----> Validationsj
    @Test
    public void createLocation() throws IOException {
        GenericUtil.genericUtilInstance().getLog().info("Log is started!!");
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Create location
        String response = given().queryParam("key", "qaclick123")
                .log().all()
                .header("Content-Type", "application/json")
                //.body(CreateLocationPayLoad.getLocationPayLoad())
                .body(GenericUtil.genericUtilInstance().getJsonData(PropertyConfig.JSON_FILE_PATH.toString()))
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
        System.out.println("Response : " + response);

        JsonPath jsonPath = new JsonPath(response);
        String placeID = jsonPath.getString("place_id");
        System.out.println(placeID);

        // Update location

        String newAddress = "Africa 2038-234";

        given().log().all()
                .header("Content-Type", "application/json")
                .queryParam("key", "qaclick123")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // Validate modified Location
        String response2 = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then()
                .log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath1 = ConvertToJSON.getRawToJSON(response2);
        String newAddressName = jsonPath1.getString("address");
        Assert.assertEquals(newAddressName, newAddress);
        GenericUtil.genericUtilInstance().stopLog();
    }}

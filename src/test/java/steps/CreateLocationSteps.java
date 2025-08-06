package steps;

import common.GenericUtil;
import common.PropertyConfig;
import common.RequestSpecific;
import io.cucumber.java.en.*;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class CreateLocationSteps {

    RequestSpecification response;


    @Given("Get the required payload")
    public void get_the_required_payload() throws IOException {
        response = RequestSpecific.setupSpec()
                .body(GenericUtil.genericUtilInstance().getJsonData(PropertyConfig.JSON_FILE_PATH.toString()));
    }

    @When("User calls the resource {string} with http post request")
    public void user_calls_the_resource_with_http_post_request(String resource) {
        response.when()
                .post(resource);
    }

    @Then("the API call status should get statusCode {int}")
    public void the_api_call_status_should_get_status_code(Integer int1) {
        response.then().statusCode(int1);
    }

    @Then("the Status {string} in respond body is OK")
    public void the_status_in_respond_body_is_ok(String status) {
        response.then().statusLine(status);
    }
}

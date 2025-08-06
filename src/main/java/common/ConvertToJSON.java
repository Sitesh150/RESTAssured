package common;

import io.restassured.path.json.JsonPath;

public class ConvertToJSON {

    public static JsonPath getRawToJSON(String jsonResponse){
        return new JsonPath(jsonResponse);
    }
}

package test;

import common.ConvertToJSON;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payLoad.CreateLocationPayLoad;

public class PathFinderTest {

    @Test
    public void pathFindTest() {
        JsonPath response = ConvertToJSON.getRawToJSON(CreateLocationPayLoad.getDummyResponse());
        int coursesCount = response.getInt("courses.size()");
        System.out.println("Courses size is : " + coursesCount);
        Assert.assertEquals(coursesCount, 3);

        int purchaseAmount = response.getInt("dashboard.purchaseAmount");
        System.out.println("PurchaseAmount size is : " + purchaseAmount);
        Assert.assertEquals(purchaseAmount, 910);

        String firstCourseTitle = response.getString("courses[0].title");
        System.out.println("FirstCourseTitle size is : " + firstCourseTitle);
        Assert.assertEquals(firstCourseTitle, "Selenium Python");

        for (int i = 0; i < coursesCount; i++) {
            System.out.println(response.get("courses[" + i + "].title").toString() + ":" + response.get("courses[" + i + "].price").toString());
            if ((response.get(("courses[" + i + "].title")).equals("RPA"))) {
                System.out.println(response.get("courses[" + i + "].copies").toString());
                break;
            }
        }
        int totalCoursesPrice = 0;
        for (int i = 0; i < coursesCount; i++) {
            int coursePrice = response.getInt("courses[" + i + "].price");
            int courseCopies = response.getInt("courses[" + i + "].copies");

            totalCoursesPrice = totalCoursesPrice + coursePrice * courseCopies;
        }
        System.out.println("Total Price is : " + totalCoursesPrice);
        Assert.assertEquals(totalCoursesPrice, response.getInt("dashboard.purchaseAmount"));
        System.out.println("Validated Successfully!!");
    }
}

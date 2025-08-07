package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = {"src/test/resources/Features"},
                     glue = {"steps"},
                    plugin = {"pretty", "html:test-output/HTMLCucumber-Reports/RESTAssured-Report/Report.html",
                            "json:test-output/JSONReports/RESTAssured-Report/Report.json",
                            "junit:test-output/XMLReports/RESTAssured-Report/Report.xml",
                            "rerun:test-output/MMAFailed/RESTAssured.txt"},
               monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeClass
    public void getChanges(){
        System.out.println("From Test Runner");
        System.out.println("From Test Runner-1");
    }
}

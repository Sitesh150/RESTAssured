package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features"},
                     glue = {"steps"},
                    plugin = {"pretty", "html:test-output/HTMLCucumber-Reports/RESTAssured-Report/Report.html",
                            "json:test-output/JSONReports/RESTAssured-Report/Report.json",
                            "junit:test-output/XMLReports/RESTAssured-Report/Report.xml",
                            "rerun:test-output/MMAFailed/RESTAssured.txt"},
               monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {}

package id.co.juaracoding.cucumber.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features/api", glue = "id.co.juaracoding.cucumber.api", plugin = {"pretty"})
public class CucumberApiRunner extends AbstractTestNGCucumberTests {
}

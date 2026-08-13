package id.co.juaracoding.cucumber.web;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features/web",
        glue = "id.co.juaracoding.cucumber.web",
        plugin = {"pretty"}
)
public class CucumberWebRunner extends AbstractTestNGCucumberTests {
}

package com.cucumber.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = { "src/test/java/features" }, glue = { "com.cucumber.stepdefs" }, plugin = { "pretty",
		"json:target/cucumber/cucumber.json" })

public class TestNGRunCucumberTest {

	DesiredCapabilities capabilities = new DesiredCapabilities();

	String username = " bharadwajpendyala";
	String accesskey = " qYlLn1IzVrC2U41zM4kyjv35EvpHxR2tyMB4aEBlkNMmvpnQ5A";
	protected static RemoteWebDriver driver = null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	protected String baseURL = "https://www.google.com";
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass
	@Parameters(value = { "browser", "version" })
	public void beforeClass(String browser, float version) {
		capabilities.setCapability("build", "Cucumber_Project_Google");
		capabilities.setCapability("name", "Querying in google engine");
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
		testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
	}

	/**
	 * @return returns two dimensional array of {@link CucumberFeatureWrapper}
	 *         objects.
	 */
	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		testNGCucumberRunner.finish();
	}
}

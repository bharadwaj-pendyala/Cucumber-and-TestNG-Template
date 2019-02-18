package com.cucumber.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.cucumber.tests.TestNGRunCucumberTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDefinitions extends TestNGRunCucumberTest {

	@Given("^user launches Google webapp$")
	public void user_launches_Google_webapp() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver.get(baseURL);
	}

	@When("^user searches for a \"([^\"]*)\"$")
	public void user_searches_for_a(String arg1) throws Throwable {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(arg1);
	}

	@And("^click on search button$")
	public void click_on_search_button() throws Throwable {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.submit();
	}

	@Then("^results retreived should contain the \"([^\"]*)\" used$")
	public void results_retreived_should_contain_the_used(String resultString) throws Throwable {
		WebElement result = driver.findElement(By.xpath("//*[@id='rhscol'][1]"));
		if (result.isDisplayed()) {
			System.out.println("Resutls are retreived");
		} else {
			System.out.println("Resutls are not retreived");
		}
	}

}
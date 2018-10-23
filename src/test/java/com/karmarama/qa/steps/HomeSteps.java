package com.karmarama.qa.steps;

import com.karmarama.qa.steplib.HomeUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class HomeSteps {

    @Steps
    HomeUser homeUser;

    @Given("^I verify the app$")
    public void verify_the_app() {
        homeUser.verifyScreen();
    }

    @When("^I tap on button one$")
    public void tap_on_button_one() {
        homeUser.clickButton1();
    }

    @Then("^I verify the button one pressed text$")
    public void verify_the_button_one_pressed_text() {
        homeUser.verifyButton1Pressed();
    }

    @When("^I tap on button two$")
    public void tap_on_button_two() {
        homeUser.clickButton2();
    }

    @Then("^I verify the button two pressed text$")
    public void verify_the_button_two_pressed_text() {
        homeUser.verifyButton2Pressed();
    }
}

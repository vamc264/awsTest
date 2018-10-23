package com.karmarama.qa.steplib;

import com.karmarama.qa.screens.HomeScreen;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class HomeUser extends ScenarioSteps {

    HomeScreen homeScreen;

    public HomeUser(Pages pages) {
        homeScreen = new HomeScreen(pages.getDriver());
    }

    @Step
    public void verifyScreen(){
        homeScreen.verifyHomeScreen();
    }

    @Step
    public void clickButton1(){
        homeScreen.tapButton1();
    }

    @Step
    public void verifyButton1Pressed(){
        homeScreen.assertButton1Pressed();
    }

    @Step
    public void clickButton2(){
        homeScreen.tapButton2();
    }

    @Step
    public void verifyButton2Pressed(){
        homeScreen.assertButton2Pressed();
    }

}

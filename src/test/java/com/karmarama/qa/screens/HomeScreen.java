package com.karmarama.qa.screens;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomeScreen extends PageObject {

    @FindBy(id = "Button 1")
    private WebElementFacade button1;

    @FindBy(id = "Button 2")
    private WebElementFacade button2;

    @FindBy(id = "Label")
    private WebElementFacade label;

    @FindBy(id = "Button 1 was pressed")
    private WebElementFacade button1Pressed;

    @FindBy(id = "Button 2 was pressed")
    private WebElementFacade button2Pressed;

    public HomeScreen(WebDriver driver){
        super(driver);
    }


    public void verifyHomeScreen(){
        button1.isVisible();
        button2.isVisible();
        label.isVisible();
    }

    public void tapButton1(){
        button1.click();
    }

    public void assertButton1Pressed(){
        Assert.assertEquals(button1Pressed.getText(),"Button 1 was pressed");
        System.out.println(button1Pressed.getText());
    }

    public void tapButton2(){
        button2.click();
    }

    public void assertButton2Pressed(){
        Assert.assertEquals(button2Pressed.getText(),"Button 2 was pressed");
        System.out.println(button2Pressed.getText());
    }
}

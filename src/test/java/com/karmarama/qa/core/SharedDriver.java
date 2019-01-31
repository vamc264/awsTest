package com.karmarama.qa.core;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.thucydides.core.webdriver.DriverSource;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

import java.net.MalformedURLException;
import java.net.URL;

import static com.karmarama.qa.core.Properties.*;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.IOSMobileCapabilityType.BUNDLE_ID;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.lang.System.getenv;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static org.openqa.selenium.remote.CapabilityType.TAKES_SCREENSHOT;
import static org.testobject.rest.api.appium.common.TestObjectCapabilities.TESTOBJECT_API_KEY;

public class SharedDriver implements DriverSource {

    @Rule
    private SauceLabsWatcher sauceLabsWatcher = new SauceLabsWatcher();

    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

    private static Scenario scenario;
    private static String platform = Properties.getPlatformName();


    @Before
    public void setup(Scenario scenario) {
        SharedDriver.scenario = scenario;
    }

    @After
    public void teardown() {
        if (isWebDriverProvided()) {
            sauceLabsWatcher.setJobStatus(!scenario.isFailed());
            sauceLabsWatcher.setJobTags(scenario.getSourceTagNames());
            if (getDriver() != null) {
                getDriver().quit();
            }
        }
    }

    @Override
    public WebDriver newDriver() {
        if (isWebDriverProvided()) {
            sauceLabsWatcher = new SauceLabsWatcher();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        setCapabilities(capabilities);

        System.out.println(capabilities);

        return isPlatformIos() ? new IOSDriver<>(getRemoteUrl(), capabilities) : new AndroidDriver<>(getRemoteUrl(), capabilities);
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    private void setCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability(APPIUM_VERSION, getAppiumVersion());
        capabilities.setCapability(PLATFORM_NAME, platform);
        capabilities.setCapability(PLATFORM_VERSION, getPlatformVersion());
        capabilities.setCapability(TAKES_SCREENSHOT, true);
        capabilities.setCapability(DEVICE_NAME, getDeviceName());
        capabilities.setCapability(APP, getApp());
        capabilities.setCapability(APP_ACTIVITY, getAppActivity());
        capabilities.setCapability(AUTOMATION_NAME, isPlatformIos() ? "XCUITest" : "UiAutomator2");
        capabilities.setCapability(isPlatformIos() ? BUNDLE_ID : APP_PACKAGE, getBundleId());
        capabilities.setCapability("commandTimeout", 450);
        capabilities.setCapability(FULL_RESET,getFullReset());
        capabilities.setCapability(NO_RESET,getNoReset());


        capabilities.setCapability(TESTOBJECT_API_KEY, getenv("TESTOBJECT_API_KEY"));
        capabilities.setCapability("testobject_test_name", scenario.getName());
    }

    private URL getRemoteUrl() {
        try {
            return new URL("https://us1.appium.testobject.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new UnreachableBrowserException(e.getMessage());
        }
    }

    public static boolean isPlatformIos() {
        return platform.equalsIgnoreCase("iOS");
    }

    private boolean isWebDriverProvided() {
        return getWebDriverProvided().equalsIgnoreCase("provided");
    }
}

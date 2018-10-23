package com.karmarama.qa.core;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Map;

public class Properties {

    private static Map<String, String> sys = System.getenv();
    static final EnvironmentVariables env = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();

    static String getSauceUsername() {
        return getSysProperty("SAUCE_USERNAME");
    }

    static String getSauceAccessKey() {
        return getSysProperty("SAUCE_ACCESS_KEY_2");
    }

    static String getPlatformName() {
        return getProperty("platform.name");
    }

    static String getPlatformVersion() {
        return getProperty("platform.version");
    }

    static String getDeviceName() {
        return getProperty("device.name");
    }

    static String getAutomationName() {
        return getProperty("automation.name");
    }

    static String getBundleId() {
        return getProperty("bundle.id");
    }

    static String getAppActivity() {
        return getProperty("appActivity");
    }

    static String getFullReset() {
        return getProperty("full.reset");
    }

    static String getNoReset() {
        return getProperty("no.reset");
    }

    static String getWebDriverProvided() {
        return getProperty("webdriver.driver");
    }

    private static String getSysProperty(String property) {
        if (property.isEmpty())
            throw new RuntimeException(property + "must be provided");

        return sys.get(property);
    }

    private static String getProperty(String property) {
        if (property.isEmpty())
            throw new RuntimeException(property + "must be provided");

        return env.getProperty(property);
    }

    public static String getApp() {
        return getProperty("app");
    }


    public static String getAppiumVersion() {
        return getProperty("appium.version");
    }
}
